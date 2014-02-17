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
package test.custom;

import java.util.Map;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;

/**
 * @author Tom Baeyens
 */
public class PrintDots implements ActivityBehaviour {

	private static final long serialVersionUID = 1L;
    public String state;
	public void execute(ActivityExecution execution) {
		String executionId = execution.getId();
		String name = execution.getActivity().getName();
        System.out.println("当前运行的节点：【"+name+"】");
	

		System.out.println("======="+execution.getVariable("hudw")+"=========="+name+"============"+state);
		
		state = null;
		execution.getVariable("test3");
		execution.setVariable("test2", "test22222222222222222222222222");
		execution.createVariable("test4", "test4444444444444444444444444");
//        if("3333333333".equals(execution.getVariable("hudw"))){
//    		execution.waitForSignal();
//        }


	}

//	public void signal(ActivityExecution execution, String signalName,
//			Map<String, ?> parameters) {
//		execution.take(signalName);
//	}
}
