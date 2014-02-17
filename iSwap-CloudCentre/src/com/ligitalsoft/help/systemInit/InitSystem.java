package com.ligitalsoft.help.systemInit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("initSystem")
public class InitSystem extends HttpServlet {
	private static final long serialVersionUID = 6502217286096120170L;
	@Autowired
	private InitiSwap initiSwap;
	public void init() throws ServletException {
		initiSwap.initSystem();
	}

	/**
	 * Constructor of the object.
	 */
	public InitSystem() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

}
