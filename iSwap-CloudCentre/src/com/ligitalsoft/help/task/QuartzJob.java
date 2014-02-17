package com.ligitalsoft.help.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.ligitalsoft.datasharexchange.service.IListenerService;


public class QuartzJob {

	private IListenerService listenerService;

	public void analyseTask() {
		listenerService.analyseTask();
	}

	public void buildTaskByMonthListener()  {
		this.listenerService.buildAllTask();
	}
	public void checkTaskListener() {
		this.listenerService.checkTaskListener();
	}
	 @Autowired
	public void setListenerService(IListenerService listenerService) {
		this.listenerService = listenerService;
	}
	
}
