/**
 * 
 */
package com.bit.common.model;

/**
 * @author ZL
 * 
 */
public class Response {

	private static final String SUCCSESS = "succsess";
	private static final String FAILURE = "failure";

	private Meta meta;
	private Object data;

	public Response success() {
		this.meta = new Meta(true, SUCCSESS);
		return this;
	}

	public Response success(Object data) {
		this.meta = new Meta(true, SUCCSESS);
		this.data = data;
		return this;
	}

	public Response failure() {
		this.meta = new Meta(false, FAILURE);
		return this;
	}

	public Response failure(String message) {
		this.meta = new Meta(false, message);
		return this;
	}
	
	public Response failure(Object message) {
		this.meta = new Meta(false, message);
		return this;
	}

	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}

	public class Meta {

		private boolean success;
		private Object message;

		public Meta(boolean success) {
			this.success = success;
		}

		public Meta(boolean success, Object message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public Object getMessage() {
			return message;
		}
	}

}
