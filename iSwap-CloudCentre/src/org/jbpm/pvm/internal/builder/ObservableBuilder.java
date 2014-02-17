package org.jbpm.pvm.internal.builder;

import org.jbpm.api.listener.EventListener;
import org.jbpm.pvm.internal.model.EventImpl;
import org.jbpm.pvm.internal.model.EventListenerReference;
import org.jbpm.pvm.internal.model.ObservableElementImpl;
import org.jbpm.pvm.internal.wire.Descriptor;


/**
 * @author Tom Baeyens
 */
public class ObservableBuilder {

  protected ObservableElementImpl observableElement;
  protected String eventName;
  protected EventImpl event = null;
  
  public ObservableBuilder(ObservableElementImpl observableElement, String eventName) {
    this.observableElement = observableElement;
    this.eventName = eventName;
  }

  protected void addListener(EventListener eventListener) {
    getEvent().createEventListenerReference(eventListener);
  }

  protected void addListener(EventListener eventListener, boolean propagation) {
    EventListenerReference eventListenerReference = getEvent().createEventListenerReference(eventListener);
    eventListenerReference.setPropagationEnabled(propagation);
  }

  protected void addListener(Descriptor descriptor) {
    getEvent().createEventListenerReference(descriptor);
  }

  protected void addListener(Descriptor descriptor, boolean propagation) {
    EventListenerReference eventListenerReference = getEvent().createEventListenerReference(descriptor);
    eventListenerReference.setPropagationEnabled(propagation);
  }

  protected EventImpl getEvent() {
    if (event==null) {
      this.event = observableElement.createEvent(eventName);
    }
    return event;
  }
}
