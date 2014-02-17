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
package org.jbpm.pvm.internal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jbpm.api.JbpmException;


/**
 * @author Tom Baeyens
 */
public class StringUtil {
  
  private StringUtil() {
    // hide default constructor to prevent instantiation
  }
  
  public static List<String> tokenize(String text, String delimiter) {
    if (delimiter==null) {
      throw new JbpmException("delimiter is null");
    }
    if (text==null) {
      return Collections.emptyList();
    }
    
    List<String> pieces = new ArrayList<String>();
    
    int start = 0;
    int end = text.indexOf(delimiter);
    while (end!=-1) {
      pieces.add(text.substring(start, end));
      start = end+delimiter.length();
      end = text.indexOf(delimiter, start);
    }
    
    if (start<text.length()) {
      pieces.add(text.substring(start));
    }
    
    return pieces;
  }

}
