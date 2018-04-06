package com.bit.common.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.session.mgt.SimpleSession;

public class ShiroSession extends SimpleSession implements Serializable {

	private static final long serialVersionUID = -3930906683875108601L;
	
	// 除lastAccessTime以外其他字段发生改变时为true
	private boolean isChanged;

	public ShiroSession() {
		super();
		this.setChanged(false);
	}

	public ShiroSession(String host) {
		super(host);
		this.setChanged(false);
	}

	@Override
	public void setId(Serializable id) {
		super.setId(id);
		this.setChanged(true);
	}

	@Override
	public void setStopTimestamp(Date stopTimestamp) {
		super.setStopTimestamp(stopTimestamp);
		this.setChanged(true);
	}

	@Override
	public void setExpired(boolean expired) {
		super.setExpired(expired);
		this.setChanged(true);
	}

	@Override
	public void setTimeout(long timeout) {
		super.setTimeout(timeout);
		this.setChanged(true);
	}

	@Override
	public void setHost(String host) {
		super.setHost(host);
		this.setChanged(true);
	}

	@Override
	public void setAttributes(Map<Object, Object> attributes) {
		super.setAttributes(attributes);
		this.setChanged(true);
	}

	@Override
	public void setAttribute(Object key, Object value) {
		super.setAttribute(key, value);
		this.setChanged(true);
	}

	@Override
	public Object removeAttribute(Object key) {
		this.setChanged(true);
		return super.removeAttribute(key);
	}

	@Override
	public void stop() {
		super.stop();
		this.setChanged(true);
	}

	@Override
	protected void expire() {
		super.expire();
		this.setChanged(true);
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected boolean onEquals(SimpleSession ss) {
		return super.onEquals(ss);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
