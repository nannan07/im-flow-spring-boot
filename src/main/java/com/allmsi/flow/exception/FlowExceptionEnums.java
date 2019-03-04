package com.allmsi.flow.exception;

public enum FlowExceptionEnums implements IExceptionCode {

	/** 对象为空 */
	NULL_OBJ("A11002", "对象为空"),
	/** 参数错误 */
	ERROR_PARAME("A11002", "参数错误");
    //参数不合法       A10001开始
	private String exceptionCode;

	private String exceptionMessage;

	private FlowExceptionEnums(String exceptionCode, String exceptionMessage) {
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
