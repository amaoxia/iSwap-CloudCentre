package com.ligitalsoft.workflow.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ActionException extends NodeException {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	private Throwable nestedThrowable = null;

	public ActionException() {
		super();
	}

	public ActionException(String msg) {
		super(msg);
	}

	public ActionException(Throwable nestedThrowable) {
		this.nestedThrowable = nestedThrowable;
	}

	public ActionException(String msg, Throwable nestedThrowable) {
		super(msg);
		this.nestedThrowable = nestedThrowable;
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (nestedThrowable != null) {
			nestedThrowable.printStackTrace();
		}
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (nestedThrowable != null) {
			nestedThrowable.printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (nestedThrowable != null) {
			nestedThrowable.printStackTrace(pw);
		}
	}

}
