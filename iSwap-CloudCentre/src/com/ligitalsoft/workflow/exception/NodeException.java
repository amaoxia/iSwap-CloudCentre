package com.ligitalsoft.workflow.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class NodeException extends ProcessException {

	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -4127701829201672310L;
	private Throwable nestedThrowable = null;

    public NodeException() {
        super();
    }
    public NodeException(String msg) {
        super(msg);
    }

    public NodeException(Throwable nestedThrowable) {
        this.nestedThrowable = nestedThrowable;
    }

    public NodeException(String msg, Throwable nestedThrowable) {
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
