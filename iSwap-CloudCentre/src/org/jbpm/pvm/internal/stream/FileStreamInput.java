package org.jbpm.pvm.internal.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.jbpm.api.JbpmException;

/**
 * ���ļ�����ʽ����
 *@Company �к���Ԫ
 *@author   hudaowan
 *@version   iSwap V6.0 ���ݽ���ƽ̨  
 *@date   2011-8-13 ����04:41:20
 *@Team �з�����
 */
public class FileStreamInput extends StreamInput {
  
  protected File file;

  /** @throws JbpmException if file is null */
  public FileStreamInput(File file) {
    if (file==null) {
      throw new JbpmException("file is null");
    }

    try {
      this.name = file.toURL().toString();
    } catch (MalformedURLException e) {
      this.name = file.toString();
    }

    this.file = file;
  }

  public InputStream openStream() {
    InputStream stream = null;
    
    try {
      if (!file.exists()) {
        throw new JbpmException("file "+file+" doesn't exist");
      }
      if (file.isDirectory()) {
        throw new JbpmException("file "+file+" is a directory");
      }
      stream = new FileInputStream(file);
      
    } catch (Exception e) {
      throw new JbpmException("couldn't access file "+file+": "+e.getMessage(), e);
    }
    
    return stream;
  }

}
