package com.ligitalsoft.workflow.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ProcessException extends Exception {

	private static final long serialVersionUID = 4449252083159767248L;
	private Throwable nestedThrowable = null;

    public ProcessException() {
        super();
    }
    public ProcessException(String msg) {
        super(msg);
    }

    public ProcessException(Throwable nestedThrowable) {
        this.nestedThrowable = nestedThrowable;
    }

    public ProcessException(String msg, Throwable nestedThrowable) {
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
