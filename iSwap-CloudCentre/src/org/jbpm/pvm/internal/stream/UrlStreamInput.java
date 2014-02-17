package org.jbpm.pvm.internal.stream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jbpm.api.JbpmException;

/**
 * 
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:51:27
 *@Team �з�����
 */
public class UrlStreamInput extends StreamInput {
  
  protected URL url;

  /** @throws JbpmException if url is null */
  public UrlStreamInput(URL url) {
    if (url==null) {
      throw new JbpmException("url is null");
    }
    this.name = url.toString();
    this.url = url;
  }

  public InputStream openStream() {
    InputStream stream = null;
    try {
      stream = url.openStream();
    } catch (IOException e) {
      throw new JbpmException("couldn't open URL stream", e);
    }
    return stream;
  }
}
