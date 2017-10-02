package com.evgen.coupons.exceptions;

import com.evgen.coupons.enums.ErrorType;

public class ApplicationException extends Exception {
	private ErrorType errorType;

	public ApplicationException(Exception e, ErrorType errorType) {
		super(e);

		this.errorType = errorType;
	}

	
	public ApplicationException(String msg, ErrorType errorType) { 
																
		super(msg);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}