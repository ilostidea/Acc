package com.bit.acc.model;
// Generated 2018-2-3 19:27:28 by Hibernate Tools 5.2.6.Final

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Questioncollected generated by hbm2java
 */
@Entity
@Table(name = "QuestionDisapproved", catalog = "acc")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value={"createTime", "creator", "modifyTime", "modifier"}/*, ignoreUnknown = true*/)
public class QuestionDisapproved implements java.io.Serializable {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = 5786612724829123952L;
	private Long id;
	private Question question;
	private SysUser user;
	private Date createTime;
	private Long creator;
	private Date modifyTime;
	private Long modifier;

	public QuestionDisapproved() {
	}

	public QuestionDisapproved(Long id, Question question, SysUser user) {
		this.id = id;
		this.question = question;
		this.user = user;
	}

	public QuestionDisapproved(Long id, Question question, SysUser user, Date createTime, Long creator, Date modifyTime,
                               Long modifier) {
		this.id = id;
		this.question = question;
		this.user = user;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QuestionID", nullable = false)
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SysUserID", nullable = false)
	public SysUser getUser() {
		return this.user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", length = 19)
	@CreationTimestamp
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
	@UpdateTimestamp
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
