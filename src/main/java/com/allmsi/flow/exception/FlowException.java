package com.allmsi.flow.exception;

public class FlowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionCode;

	private String exceptionMessage;

	public FlowException() {

	}

	public FlowException(FlowExceptionEnums flowException) {
		this.exceptionCode = flowException.getExceptionCode();
		this.exceptionMessage = flowException.getExceptionMessage();
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String toString() {
		return "FlowException [exceptionCode=" + exceptionCode + ", exceptionMessage=" + exceptionMessage + "]";
	}

}
