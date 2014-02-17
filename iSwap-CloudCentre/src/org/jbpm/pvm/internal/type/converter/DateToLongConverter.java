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
package org.jbpm.pvm.internal.type.converter;

import java.util.Date;

import org.jbpm.pvm.internal.model.ScopeInstanceImpl;
import org.jbpm.pvm.internal.type.Converter;
import org.jbpm.pvm.internal.type.Variable;

public class DateToLongConverter implements Converter {

  private static final long serialVersionUID = 1L;

  public boolean supports(Object value, ScopeInstanceImpl scopeInstance, Variable variable) {
    if (value==null) return true;
    return (Date.class.isAssignableFrom(value.getClass()));
  }

  public Object convert(Object o, ScopeInstanceImpl scopeInstance, Variable variable) {
    return new Long(((Date)o).getTime());
  }

  public Object revert(Object o, ScopeInstanceImpl scopeInstance, Variable variable) {
    return new Date(((Long)o).longValue());
  }
}
