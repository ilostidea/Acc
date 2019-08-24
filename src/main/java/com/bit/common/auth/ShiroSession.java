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
		System.out.println("ShiroSession constructor.");
	}

	public ShiroSession(String host) {
		super(host);
		this.setChanged(false);
		System.out.println("ShiroSession constructor with host " + host);
	}

	@Override
	public void setId(Serializable id) {
		super.setId(id);
		this.setChanged(true);
		System.out.println("ShiroSession setId " + id.toString()  + ".");
	}

	@Override
	public void setStopTimestamp(Date stopTimestamp) {
		super.setStopTimestamp(stopTimestamp);
		this.setChanged(true);
		System.out.println("ShiroSession setStopTimestamp " + stopTimestamp.toString()  + ".");
	}

	@Override
	public void setExpired(boolean expired) {
		super.setExpired(expired);
		this.setChanged(true);
		System.out.println("ShiroSession setExpired " + String.valueOf(expired) + ".");
	}

	@Override
	public void setTimeout(long timeout) {
		super.setTimeout(timeout);
		this.setChanged(true);
		System.out.println("ShiroSession setTimeout " + timeout  + ".");
	}

	@Override
	public void setHost(String host) {
		super.setHost(host);
		this.setChanged(true);
		System.out.println("ShiroSession setHost " + host + ".");
	}

	@Override
	public void setAttributes(Map<Object, Object> attributes) {
		super.setAttributes(attributes);
		this.setChanged(true);
		System.out.println("ShiroSession setAttributes.");
	}

	@Override
	public void setAttribute(Object key, Object value) {
		super.setAttribute(key, value);
		this.setChanged(true);
		System.out.println("ShiroSession setAttribute.");
	}

	@Override
	public Object removeAttribute(Object key) {
		this.setChanged(true);
		System.out.println("ShiroSession removeAttribute.");
		return super.removeAttribute(key);
	}

	@Override
	public void stop() {
		super.stop();
		this.setChanged(true);
		System.out.println("ShiroSession stop.");
	}

	@Override
	protected void expire() {
		super.expire();
		this.setChanged(true);
		System.out.println("ShiroSession expire.");
	}

	public boolean isChanged() {
		System.out.println("ShiroSession isChanged " + String.valueOf(isChanged) + ".");
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		System.out.println("ShiroSession setChanged " + String.valueOf(isChanged) + ".");
		this.isChanged = isChanged;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("ShiroSession equals.");
		return super.equals(obj);
	}

	@Override
	protected boolean onEquals(SimpleSession ss) {
		System.out.println("ShiroSession onEquals.");
		return super.onEquals(ss);
	}

	@Override
	public int hashCode() {
		System.out.println("ShiroSession hashCode.");
		return super.hashCode();
	}

	@Override
	public String toString() {
		System.out.println("ShiroSession toString.");
		return super.toString();
	}
}
