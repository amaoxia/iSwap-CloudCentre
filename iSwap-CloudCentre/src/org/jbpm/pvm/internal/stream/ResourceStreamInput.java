package org.jbpm.pvm.internal.stream;

import java.io.InputStream;

import org.jbpm.api.JbpmException;

/**
 * ͨ�����·����ȡ����
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:45:01
 *@Team �з�����
 */
public class ResourceStreamInput extends StreamInput {
  
  protected ClassLoader classLoader;
  protected String resource;
  
  /** @throws JbpmException if resource is null */
  public ResourceStreamInput(String resource) {
    this(resource, null);
  }

  /** @throws JbpmException if resource is null */
  public ResourceStreamInput(String resource, ClassLoader classLoader) {
    if (resource==null) {
      throw new JbpmException("resource is null");
    }
    this.name = "resource://"+resource;
    this.resource = resource;
    this.classLoader = classLoader;
  }

  public InputStream openStream() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream(resource);

    if (stream == null) {
      stream = ResourceStreamInput.class.getClassLoader().getResourceAsStream(resource);
    }

    if (stream==null) {
      throw new JbpmException("resource "+resource+" does not exist");
    }
    return stream;
  }
}
