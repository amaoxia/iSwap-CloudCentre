package org.jbpm.pvm.internal.stream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 字符串输入转换成流程
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午04:48:23
 *@Team 研发中心
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
