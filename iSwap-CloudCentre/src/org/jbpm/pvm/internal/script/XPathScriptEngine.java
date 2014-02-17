/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved. 
 * Use is subject to license terms.
 *
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met: Redistributions of source code 
 * must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of 
 * conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution. Neither the name of the Sun Microsystems nor the names of 
 * is contributors may be used to endorse or promote products derived from this software 
 * without specific prior written permission. 

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * XPathScriptEngine.java
 * @author A. Sundararajan
 */

package org.jbpm.pvm.internal.script;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XPathScriptEngine extends AbstractScriptEngine implements Compilable {

  // my factory, may be null
  private ScriptEngineFactory factory;
  private XPathFactory xpathFactory;

  // special context variables for XPath result type and input
  public static final String XPATH_RESULT_TYPE = "com.sun.script.xpath.resultType";
  public static final String XPATH_INPUT_SRC = "com.sun.script.xpath.inputSource";

  // XML namespace prefixes and URIs.
  public static final String XMLNS_COLON = "xmlns:";
  public static final String XPATH_CONTEXT_PREFIX = "context";
  public static final String XPATH_CONTEXT_URI = "http://www.sun.com/java/jsr223/xpath/context";

  private Document objectData;

  public XPathScriptEngine() {
    xpathFactory = XPathFactory.newInstance();
  }

  // my implementation for CompiledScript
  private class XPathCompiledScript extends CompiledScript {

    private XPathExpression expr;

    XPathCompiledScript(XPathExpression expr) {
      this.expr = expr;
    }

    public ScriptEngine getEngine() {
      return XPathScriptEngine.this;
    }

    public Object eval(ScriptContext ctx) throws ScriptException {
      return evalXPath(expr, ctx);
    }
  }

  public CompiledScript compile(String script) throws ScriptException {
    XPathExpression expr = compileXPath(script, context);
    return new XPathCompiledScript(expr);
  }

  public CompiledScript compile(Reader reader) throws ScriptException {
    return compile(readFully(reader));
  }

  public Object eval(String str, ScriptContext ctx) throws ScriptException {
    XPathExpression expr = compileXPath(str, ctx);
    return evalXPath(expr, ctx);
  }

  public Object eval(Reader reader, ScriptContext ctx) throws ScriptException {
    return eval(readFully(reader), ctx);
  }

  public ScriptEngineFactory getFactory() {
    synchronized (this) {
      if (factory == null) {
        factory = new XPathScriptEngineFactory();
      }
    }
    return factory;
  }

  public Bindings createBindings() {
    return new SimpleBindings();
  }

  void setFactory(ScriptEngineFactory factory) {
    this.factory = factory;
  }

  // Internals only below this point

  // find a variable of given qname in given context
  static Object findVariable(QName qname, ScriptContext ctx) {
    String name;
    int scope;

    if (XPATH_CONTEXT_URI.equals(qname.getNamespaceURI())) {
      name = qname.getLocalPart();
      synchronized (ctx) {
        scope = ctx.getAttributesScope(name);
        if (scope != -1) {
          return ctx.getAttribute(name, scope);
        } // else fallthru
      }
    }
    if (qname.getPrefix() == null || "".equals(qname.getPrefix())) {
      name = qname.getLocalPart();
    } else {
      name = qname.getPrefix() + ":" + qname.getLocalPart();
    }
    synchronized (ctx) {
      scope = ctx.getAttributesScope(name);
      if (scope != -1) {
        return ctx.getAttribute(name, scope);
      } // else fallthru
    }
    return null;
  }

  private static NamespaceContext makeNamespaceContext(ScriptContext ctx) {
    // namespace prefix-to-URI mappings
    final Map<String, String> namespaces = new HashMap<String, String>();
    for (int scope : ctx.getScopes()) {
      Bindings bind = ctx.getBindings(scope);
      if (bind != null) {
        // TODO: See what needs to be done....
        // collectNamespaces(namespaces, bind);
      }
    }

    // look for mapping for default XML namespace
    Object def = ctx.getAttribute(XMLConstants.XMLNS_ATTRIBUTE);
    if (def instanceof String) {
      namespaces.put(XMLConstants.DEFAULT_NS_PREFIX, (String) def);
    }

    // standard required mappings by XPath standard
    namespaces.put(XMLConstants.XML_NS_PREFIX, XMLConstants.XML_NS_URI);
    namespaces.put(XMLConstants.XMLNS_ATTRIBUTE, XMLConstants.XMLNS_ATTRIBUTE_NS_URI);

    // add prefix mapping for XPATH_CONTEXT_PREFIX
    namespaces.put(XPATH_CONTEXT_PREFIX, XPATH_CONTEXT_URI);

    return new NamespaceContext() {

      public String getNamespaceURI(String prefix) {
        if (prefix == null) {
          throw new IllegalArgumentException();
        }
        String uri = namespaces.get(prefix);
        if (uri == null) {
          return XMLConstants.NULL_NS_URI;
        } else {
          return uri;
        }
      }

      public String getPrefix(String namespaceURI) {
        if (namespaceURI == null) {
          throw new IllegalArgumentException();
        }
        for (String prefix : namespaces.keySet()) {
          String uri = namespaces.get(prefix);
          if (namespaceURI.equals(uri)) {
            return prefix;
          }
        }
        return null;
      }

      public Iterator<String> getPrefixes(String namespaceURI) {
        if (namespaceURI == null) {
          throw new IllegalArgumentException();
        }
        List<String> list = new ArrayList<String>();
        for (String prefix : namespaces.keySet()) {
          String uri = namespaces.get(prefix);
          if (namespaceURI.equals(uri)) {
            list.add(prefix);
          }
        }
        return Collections.unmodifiableList(list).iterator();
      }
    };
  }

  private static XPathFunction makeXPathFunction(final Constructor<?> ctr, int arity) {
    if (ctr.getParameterTypes().length != arity) {
      return null;
    }
    return new XPathFunction() {
      @SuppressWarnings("unchecked")
      public Object evaluate(List args) {
        try {
          return ctr.newInstance(args.toArray());
        } catch (Exception exp) {
          throw new RuntimeException(exp);
        }
      }
    };
  }

  private static XPathFunction makeXPathFunction(final Method method, int arity) {
    int modifiers = method.getModifiers();
    int numArgs = method.getParameterTypes().length;
    if (Modifier.isStatic(modifiers) && numArgs == arity) {
      // static method. expose "as is".
      return new XPathFunction() {
        @SuppressWarnings("unchecked")
        public Object evaluate(List args) {
          try {
            return method.invoke(null, args.toArray());
          } catch (Exception exp) {
            throw new RuntimeException(exp);
          }
        }
      };
    } else if ((numArgs + 1) == arity) {
      // instance method. treat the first arg as 'this'
      return new XPathFunction() {
        @SuppressWarnings("unchecked")
        public Object evaluate(List args) {
          List<?> tmp = args.subList(1, args.size());
          try {
            return method.invoke(args.get(0), tmp.toArray());
          } catch (Exception exp) {
            throw new RuntimeException(exp);
          }
        }
      };
    } else {
      return null;
    }
  }

  private static XPathFunction makeXPathFunction(final String funcName, final Invocable invocable) {
    return new XPathFunction() {
      @SuppressWarnings("unchecked")
      public Object evaluate(List args) {
        try {
          return invocable.invokeFunction(funcName, args.toArray());
        } catch (Exception exp) {
          throw new RuntimeException(exp);
        }
      }
    };
  }

  // make a XPathFunction from given object
  static XPathFunction makeXPathFunction(QName qname, Object obj, int arity) {
    if (obj == null) {
      return null;
    } else if (obj instanceof XPathFunction) {
      // already XPathFunction - just return
      return (XPathFunction) obj;
    } else if (obj instanceof Method) {
      // a Method object. wrap as XPathFunction
      return makeXPathFunction((Method) obj, arity);
    } else if (obj instanceof Constructor<?>) {
      // a Constructor object. wrap as XPathFunction
      return makeXPathFunction((Constructor<?>) obj, arity);
    } else if (obj instanceof Invocable) {
      // wrap a script function as XPathFunction. Using this,
      // scripts from other languages (for eg. JavaScript) can use
      // this engine and pass script functions as XPathFunction extensions

      return makeXPathFunction(qname.getLocalPart(), (Invocable) obj);
    } else {
      // can't map the object as XPathFunction.
      return null;
    }
  }

  // Internals only below this point
  private XPathExpression compileXPath(String str, final ScriptContext ctx) throws ScriptException {
    // JSR-223 requirement
    ctx.setAttribute("context", ctx, ScriptContext.ENGINE_SCOPE);
    try {
      XPath xpath = xpathFactory.newXPath();
      xpath.setXPathVariableResolver(new XPathVariableResolver() {

        public Object resolveVariable(QName qname) {
          return findVariable(qname, ctx);
        }
      });

      xpath.setXPathFunctionResolver(new XPathFunctionResolver() {

        public XPathFunction resolveFunction(QName qname, int arity) {
          Object obj = findVariable(qname, ctx);
          return makeXPathFunction(qname, obj, arity);
        }
      });
      xpath.setNamespaceContext(makeNamespaceContext(ctx));
      // xpath.setNamespaceContext(new BpmnFunctionResolver());
      // xpath.setXPathFunctionResolver(new BpmnFunctionResolver());
      int begin = str.indexOf("getObjectData") > -1 ? 14 : 0;
      if (begin > 0) {
        String objectDataRef = str.substring(begin + 1, str.indexOf(")") - 1);

        objectData = (Document) ctx.getAttribute(objectDataRef);
        // ctx.setAttribute(XPATH_INPUT_SRC, objectData , 100);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Source xmlSource = new DOMSource(objectData);
        Result outputTarget = new StreamResult(outputStream);
        TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
        InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
        ctx.setReader(new InputStreamReader(is));

        str = str.substring(str.indexOf(")") + 1);
      }
      XPathExpression xpe = xpath.compile(str);
      return xpe;
    } catch (Exception exp) {
      throw new ScriptException(exp);
    }
  }

  private Object getVariable(ScriptContext ctx, String name) {
    synchronized (ctx) {
      int scope = ctx.getAttributesScope(name);
      if (scope != -1) {
        return ctx.getAttribute(name, scope);
      }
    }
    return null;
  }
  Object evalXPath(XPathExpression expr, final ScriptContext ctx) throws ScriptException {

    try {
      Object resultType = getVariable(ctx, XPATH_RESULT_TYPE);
      Object input = getVariable(ctx, XPATH_INPUT_SRC);

      InputSource src;
      if (input == null) {
        // no input specified, use context reader
        src = new InputSource(ctx.getReader());
      } else {
        // explicit InputSource specified
        if (input instanceof InputSource) {
          src = (InputSource) input;
        } else if (input instanceof String) {
          src = new InputSource((String) input);
        } else if (input instanceof Reader) {
          src = new InputSource((Reader) input);
        } else if (input instanceof InputStream) {
          src = new InputSource((InputStream) input);
        } else {
          // some other object input type specified (Node/NodeList)
          src = null;
        }
      }

      resultType = XPathConstants.BOOLEAN;

      if (resultType instanceof QName) {
        return (src != null) ? expr.evaluate(src, (QName) resultType) : expr.evaluate(input, (QName) resultType);
      } else {
        return (src != null) ? expr.evaluate(src) : expr.evaluate(input);
      }
    } catch (Exception exp) {
      throw new ScriptException(exp);
    }
  }

  private String readFully(Reader reader) throws ScriptException {
    char[] arr = new char[8 * 1024]; // 8K at a time
    StringBuilder buf = new StringBuilder();
    int numChars;
    try {
      while ((numChars = reader.read(arr, 0, arr.length)) > 0) {
        buf.append(arr, 0, numChars);
      }
    } catch (IOException exp) {
      throw new ScriptException(exp);
    }
    return buf.toString();
  }
}
