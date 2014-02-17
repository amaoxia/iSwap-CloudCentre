/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jbpm.pvm.internal.wire.binding;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.pvm.internal.util.XmlUtil;
import org.jbpm.pvm.internal.wire.Descriptor;
import org.jbpm.pvm.internal.wire.descriptor.PropertiesDescriptor;
import org.jbpm.pvm.internal.wire.descriptor.StringDescriptor;
import org.jbpm.pvm.internal.xml.Parse;
import org.jbpm.pvm.internal.xml.Parser;
import org.w3c.dom.Element;

/** parses a descriptor for creating a java.util.Properties.
 * 
 * See schema docs for more details.
 * 
 * @author Tom Baeyens
 */
public class PropertiesBinding extends WireDescriptorBinding {
  
  public PropertiesBinding() {
    super("properties");
  }

  public Object parse(Element element, Parse parse, Parser parser) {
    return parseDescriptor(element, parse, parser);
  }

  public static PropertiesDescriptor parseDescriptor(Element element, Parse parse, Parser parser) {
    PropertiesDescriptor descriptor = new PropertiesDescriptor();
    
    if (element.hasAttribute("file")) {
      descriptor.setFile(element.getAttribute("file"));
    }
    
    if (element.hasAttribute("resource")) {
      descriptor.setResource(element.getAttribute("resource"));
    }
    
    if (element.hasAttribute("url")) {
      descriptor.setUrl(element.getAttribute("url"));
    }
    
    Boolean isXml = XmlUtil.attributeBoolean(element, "is-xml", parse);
    if (isXml!=null) {
      descriptor.setXml(isXml);
    }

    List<Descriptor> keyDescriptors = new ArrayList<Descriptor>();
    List<Descriptor> valueDescriptors = new ArrayList<Descriptor>();

    List<Element> elements = XmlUtil.elements(element);
    for (Element propertyElement: elements) {
      if ("property".equals(propertyElement.getLocalName())) {
        // key
        String name = XmlUtil.attribute(propertyElement, "name");
        // value
        String value = XmlUtil.attribute(propertyElement, "value");

        if ( (name!=null)
             && (value!=null)
           ) {
          keyDescriptors.add(new StringDescriptor(name));
          valueDescriptors.add(new StringDescriptor(value));
        } else {
          parse.addProblem("property must have name and value attributes: "+XmlUtil.toString(propertyElement), element);
        }
      } else {
        parse.addProblem("properties can only contain property elements: "+XmlUtil.toString(propertyElement), element);
      }
    }

    descriptor.setKeyDescriptors(keyDescriptors);
    descriptor.setValueDescriptors(valueDescriptors);

    return descriptor;
  }
}
