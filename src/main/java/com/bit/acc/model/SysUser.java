package com.bit.acc.model;

// Generated 2016-6-26 0:51:48 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.bit.common.util.IConstants;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SysUser generated by hbm2java
 */
//@SequenceGenerator(name="SysUserSeq",sequenceName="SysUserSeq")
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "SysUser")
@JsonIgnoreProperties(value={"createTime", "creator", "modifyTime", "modifier"}, ignoreUnknown = true)//"passwd", "status"
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//解决json循环嵌套的问题,加上了该注解，会导致反序列化子对象失败
@GroupSequence({First.class, Second.class, SysUser.class})
public class SysUser implements java.io.Serializable {

	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 8217941855974065503L;

	@Id
    @GeneratedValue(generator="idgen")
	@GenericGenerator(name="idgen", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	//@JsonBackReference
	/*@OneToOne(optional = true, cascade = { CascadeType.PERSIST },  fetch = FetchType.LAZY, orphanRemoval = true)
	@LazyToOne(LazyToOneOption.PROXY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "EmployeeID", referencedColumnName="id", nullable = false)
	private Employee employee;*/
	
	@Column(name = "Mobile")
	@NotEmpty(message = "{user.mobile.null}", groups = {First.class})
	@Size(min = 11, max = 16, message = "{user.mobile.length.illegal}", groups = {First.class})
	@Pattern(regexp = IConstants.REGEX_MOBILE, message = "{user.mobile.illegal}", groups = {First.class})
	private String mobile;

	@Column(name = "Email")
	@Length(max = 64, message = "{user.email.length.illegal}", groups = {First.class})
	@Email(message = "{user.email.illegal}", groups = {First.class})
	private String email;
	
	@Column(name = "Passwd")
	@NotEmpty(message = "{user.password.null}", groups = {First.class})
	//@Length(min = 32, max = 32, message = "{user.password.length.illegal}", groups = {Second.class})
	//@Pattern(regexp = IConstants.REGEX_PASSWORD, message = "{user.password.illegal}")
	private String passwd;

	@Column(name = "NickName")
	@NotEmpty(message = "{user.nickName.null}", groups = {First.class})
	@Length(max = 32, message = "{user.nickName.length.illegal}", groups = {Second.class})
	private String nickName;

	@Column(name = "Status", nullable = false)
	private Boolean status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", updatable = false, nullable = false, length = 19)
	private Date createTime;

	@Column(name = "Creator")
	private Long creator;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ModifyTime", nullable = false, length = 19)
	private Date modifyTime;

	@Column(name = "Modifier")
	private Long modifier;

	public SysUser() {
	}
	
	public SysUser(Long id, String nickName) {
		this.id = id;
		this.nickName = nickName;
	}

	public SysUser(Long id, /*Employee employee,*/ String mobile, String passwd,
			Boolean status) {
		this.id = id;
		//this.employee = employee;
		this.mobile = mobile;
		this.passwd = passwd;
		this.status = status;
	}

	public SysUser(Long id, /*Employee employee,*/ String mobile, String email,
			String passwd, String nickName, Boolean status) {
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.passwd = passwd;
		this.nickName = nickName;
		this.status = status;
		//this.employee = employee;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPasswd() {
		return this.passwd;
	}

	@JsonProperty
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Boolean isStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

/*	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}*/


	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreator() {
		return this.creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getModifier() {
		return this.modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

}
