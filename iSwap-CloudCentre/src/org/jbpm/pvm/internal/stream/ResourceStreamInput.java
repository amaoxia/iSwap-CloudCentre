package org.jbpm.pvm.internal.stream;

import java.io.InputStream;

import org.jbpm.api.JbpmException;

/**
 * 通过相对路径读取数据
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午04:45:01
 *@Team 研发中心
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
