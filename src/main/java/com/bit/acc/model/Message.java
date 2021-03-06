 package com.bit.acc.model;
// Generated 2017-11-16 0:24:29 by Hibernate Tools 5.2.6.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Message generated by hbm2java
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "Message")
@JsonIgnoreProperties(value={"createTime", "creator", "modifyTime", "modifier"}/*, ignoreUnknown = true*/)
public class Message implements java.io.Serializable {

	private static final Long serialVersionUID = 8569283491852258878L;

	@Id
    @GeneratedValue(generator="idgen")
	@GenericGenerator(name="idgen", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "Type", nullable = false)
	private int type;
	
	@Column(name = "Content", nullable = false, length = 2000)
	private String content;
	
	@ManyToOne(optional = true, /**cascade = { CascadeType.PERSIST, CascadeType.MERGE },*/  fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "Receiver", referencedColumnName="ID", nullable = false)
	private SysUser receiver;
	
	
	@ManyToOne(optional = true, /**cascade = { CascadeType.PERSIST, CascadeType.MERGE },*/  fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "Sender", referencedColumnName="ID", nullable = false)
	private SysUser sender;
	
	@Column(name = "HasRead")
	private Boolean hasRead;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", updatable = false, length = 19)
	@CreationTimestamp
	private Date createTime;
	
	@ManyToOne(optional = true, /**cascade = { CascadeType.PERSIST, CascadeType.MERGE },*/  fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "Creator", referencedColumnName="ID", nullable = false)
	private SysUser creator;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ModifyTime", length = 19)
	@UpdateTimestamp
	private Date modifyTime;
	
	@ManyToOne(optional = true, /**cascade = { CascadeType.PERSIST, CascadeType.MERGE },*/  fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "Modifier", referencedColumnName="ID", nullable = false)
	private SysUser modifier;

	public Message() {
	}

	public Message(Long id, int type, String content, SysUser receiver, SysUser sender) {
		this.id = id;
		this.type = type;
		this.content = content;
		this.receiver = receiver;
		this.sender = sender;
	}

	public Message(Long id, int type, String content, SysUser receiver, SysUser sender, Boolean hasRead, Date createTime,
			SysUser creator, Date modifyTime, SysUser modifier) {
		this.id = id;
		this.type = type;
		this.content = content;
		this.receiver = receiver;
		this.sender = sender;
		this.hasRead = hasRead;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public SysUser getReceiver() {
		return this.receiver;
	}

	public void setReceiver(SysUser receiver) {
		this.receiver = receiver;
	}

	
	public SysUser getSender() {
		return this.sender;
	}

	public void setSender(SysUser sender) {
		this.sender = sender;
	}

	
	public Boolean getHasRead() {
		return this.hasRead;
	}

	public void setHasRead(Boolean hasRead) {
		this.hasRead = hasRead;
	}

	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public SysUser getCreator() {
		return this.creator;
	}

	public void setCreator(SysUser creator) {
		this.creator = creator;
	}

	
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	
	public SysUser getModifier() {
		return this.modifier;
	}

	public void setModifier(SysUser modifier) {
		this.modifier = modifier;
	}

}
