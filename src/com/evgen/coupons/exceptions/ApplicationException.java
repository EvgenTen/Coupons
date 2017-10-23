/*
 * The class is own Exception class
 * that can be thrown. 
 * The class use its own error type 
 * exceptions as ENUM`s
*/
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