package org.jbpm.pvm.internal.stream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * �ַ�������ת��������
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:48:23
 *@Team �з�����
 */
public class StringStreamInput extends StreamInput {

  private String string;

  public StringStreamInput(String string) {
    this.name = "string";
    this.string = string;
  }

  public InputStream openStream() {
    try {
      byte[] bytes = string.getBytes("UTF-8");
      return new ByteArrayInputStream(bytes);
    }
    catch (UnsupportedEncodingException e) {
      throw new AssertionError(e);
    }
  }
}
