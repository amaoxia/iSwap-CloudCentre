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
package org.jbpm.pvm.internal.env;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jbpm.pvm.internal.job.JobImpl;

public class JobContext implements Context {
  
  private static final String KEY_JOB = "job";
  
  static final Set<String> keys = Collections.unmodifiableSet(getKeys());
  static Set<String> getKeys() {
    Set<String> keys = new HashSet<String>();
    keys.add(KEY_JOB);
    return keys;
  }

  JobImpl job;

  public JobContext(JobImpl job) {
    this.job = job;
  }

  public Object get(String key) {
    if (KEY_JOB.equals(key)) {
      return job;
    }
    return null;
  }

  public boolean has(String key) {
    return KEY_JOB.equals(key);
  }

  public Set<String> keys() {
    return keys;
  }

  public Object set(String key, Object value) {
    throw new UnsupportedOperationException("set is not supported on "+JobContext.class.getName());
  }
  
  public <T> T get(Class<T> type) {
    if (type.isAssignableFrom(JobImpl.class)) {
      return type.cast(job);
    }
    return null;
  }

  public String getName() {
    return Context.CONTEXTNAME_JOB;
  }
}
