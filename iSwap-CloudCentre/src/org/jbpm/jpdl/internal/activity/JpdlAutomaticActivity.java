package org.jbpm.jpdl.internal.activity;

import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.pvm.internal.model.ExecutionImpl;

import com.ligitalsoft.workflow.exception.NodeException;


public abstract class JpdlAutomaticActivity extends JpdlActivity implements EventListener {

  private static final long serialVersionUID = 1L;

  public void execute(ActivityExecution execution) throws NodeException {
    perform(execution);
    ((ExecutionImpl)execution).historyAutomatic();
  }
    
  public void notify(EventListenerExecution execution) throws NodeException {
    perform(execution);
  }    
    
  abstract void perform(OpenExecution execution) throws NodeException;
}
