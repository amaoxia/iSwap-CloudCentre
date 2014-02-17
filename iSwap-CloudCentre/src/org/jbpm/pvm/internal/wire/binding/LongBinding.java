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

import org.jbpm.pvm.internal.wire.descriptor.AbstractDescriptor;
import org.jbpm.pvm.internal.wire.descriptor.LongDescriptor;
import org.jbpm.pvm.internal.xml.Parse;
import org.w3c.dom.Element;

/** parses a descriptor for creating a {@link java.lang.Long}.
 * 
 * See schema docs for more details.
 * 
 * @author Tom Baeyens
 * @author Guillaume Porcher (documentation)
 */
public class LongBinding extends BasicTypeBinding {

  public LongBinding() {
    super("long");
  }

  protected AbstractDescriptor createDescriptor(String value, Element element, Parse parse) {
    LongDescriptor longDescriptor =  new LongDescriptor();
    Long longValue;
    try {
      longValue = new Long(value);
    } catch (NumberFormatException e) {
      parse.addProblem(createValueExceptionMessage("'"+value+"' cannot be parsed to a long", element), element);
      return null;
    }
    longDescriptor.setValue(longValue);
    return longDescriptor;
  }
}