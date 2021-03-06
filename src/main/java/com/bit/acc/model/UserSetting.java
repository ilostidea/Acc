package com.bit.acc.model;
// Generated 2018-2-3 19:27:28 by Hibernate Tools 5.2.6.Final

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Usersetting generated by hbm2java
 */
@Entity
@Table(name = "UserSetting", catalog = "acc")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value={"createTime", "creator", "modifyTime", "modifier"}/*, ignoreUnknown = true*/)
public class UserSetting implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6360678699247978343L;
	private Long id;
	private SysUser user;
	private String paramName;
	private String paramValue;
	private String description;
	private Date createTime;
	private Long creator;
	private Date modifyTime;
	private Long modifier;

	public UserSetting() {
	}

	public UserSetting(Long id, SysUser user, String paramName, String paramValue) {
		this.id = id;
		this.user = user;
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public UserSetting(Long id, SysUser user, String paramName, String paramValue, String description, Date createTime,
			Long creator, Date modifyTime, Long modifier) {
		this.id = id;
		this.user = user;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.description = description;
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
	@JoinColumn(name = "UserID", nullable = false)
	public SysUser getUser() {
		return this.user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	@Column(name = "ParamName", nullable = false, length = 64)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "ParamValue", nullable = false, length = 64)
	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Column(name = "Description", length = 64)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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
