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
package org.jbpm.pvm.internal.wire.usercode;

import java.util.Map;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.api.activity.ExternalActivityBehaviour;
import org.jbpm.pvm.internal.model.ExecutionImpl;

import com.ligitalsoft.workflow.exception.NodeException;

/**
 * @author Tom Baeyens
 */
public class UserCodeActivityBehaviour implements ExternalActivityBehaviour {

  private static final long serialVersionUID = 1L;

  protected UserCodeReference customActivityReference;

  public void execute(ActivityExecution execution) throws NodeException {
    ActivityBehaviour activityBehaviour = (ActivityBehaviour) customActivityReference.getObject(execution);
    activityBehaviour.execute(execution);
    ((ExecutionImpl)execution).historyAutomatic();
  }

  public void signal(ActivityExecution execution, String signalName, Map<String, ? > parameters) throws Exception {
    ExternalActivityBehaviour externalActivityBehaviour = (ExternalActivityBehaviour) customActivityReference.getObject(execution);
    externalActivityBehaviour.signal(execution, signalName, parameters);
  }

  public void setCustomActivityReference(UserCodeReference customActivityReference) {
    this.customActivityReference = customActivityReference;
  }
}
