package com.bit.acc.model;
// Generated 2018-2-3 19:27:28 by Hibernate Tools 5.2.6.Final

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Pump generated by hbm2java
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "pump", catalog = "acc")
@JsonIgnoreProperties(value={"creator", "modifyTime", "modifier"}/*, ignoreUnknown = true*/)
public class Pump implements java.io.Serializable {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = -2512448693347432778L;
	private Long id;
	private Answer answer;
	private SysUser user;
	private String content;
	private Boolean isAnonymous;
	private Boolean isAccused;
	private Boolean status;
	private Date createTime;
	private Long creator;
	private Date modifyTime;
	private Long modifier;

	public Pump() {
	}

	public Pump(Long id, Answer answer, SysUser user, Boolean isAnonymous, Boolean isAccused) {
		this.id = id;
		this.answer = answer;
		this.user = user;
		this.isAnonymous = isAnonymous;
		this.isAccused = isAccused;
	}

	public Pump(Long id, Answer answer, SysUser user, String content, Boolean isAnonymous, Boolean isAccused,
			Boolean status, Date createTime, Long creator, Date modifyTime, Long modifier) {
		this.id = id;
		this.answer = answer;
		this.user = user;
		this.content = content;
		this.isAnonymous = isAnonymous;
		this.isAccused = isAccused;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
	}

	@Id
    @GeneratedValue(generator="idgen")
	@GenericGenerator(name="idgen", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonBackReference
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "AnswerID", nullable = false)
	public Answer getAnswer() {
		return this.answer;
	}
	
	@JsonIgnore(false)
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", nullable = false)
	public SysUser getUser() {
		return this.user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	@Column(name = "Content", length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "IsAnonymous", nullable = false)
	public Boolean isIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	@Column(name = "IsAccused", nullable = false)
	public Boolean isIsAccused() {
		return this.isAccused;
	}

	public void setIsAccused(Boolean isAccused) {
		this.isAccused = isAccused;
	}

	@Column(name = "Status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Creator")
	public Long getCreator() {
		return this.creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ModifyTime", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "Modifier")
	public Long getModifier() {
		return this.modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

}
