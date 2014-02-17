package org.jbpm.pvm.internal.stream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import org.jbpm.api.JbpmException;

/**
 *���ֽڵ���ʽ����
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:39:27
 *@Team �з�����
 */
public class ByteArrayStreamInput extends StreamInput implements Serializable {
  
  private static final long serialVersionUID = 1L;

  protected byte[] bytes;
  
  /** @throws JbpmException if bytes is null */
  public ByteArrayStreamInput(byte[] bytes) {
    if (bytes==null) {
      throw new JbpmException("bytes is null");
    }
    this.name = "byte-array";
    this.bytes = bytes;
  }

  public InputStream openStream() {
    return new ByteArrayInputStream(bytes);
  }

}
