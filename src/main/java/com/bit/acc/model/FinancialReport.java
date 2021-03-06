package com.bit.acc.model;

// default package
// Generated 2017-11-20 23:52:31 by Hibernate Tools 3.4.0.CR1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Financialreport generated by hbm2java
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "FinancialReport", catalog = "acc")
@JsonIgnoreProperties(value={"createTime", "creator", "modifyTime", "modifier"}/*, ignoreUnknown = true*/)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="accStandardCache")
@Cacheable(true)
public class FinancialReport implements java.io.Serializable {

	private static final long serialVersionUID = -6188142822769146149L;
	
	private Long id;
	private AccountingStandard accountingStandard;
	private String name;
	private String report;
	private Boolean status;
	private Date createTime;
	private Long creator;
	private Date modifyTime;
	private Long modifier;

	public FinancialReport() {
	}

	public FinancialReport(Long id, AccountingStandard accountingStandard,
			String name, String report, Boolean status) {
		this.id = id;
		this.accountingStandard = accountingStandard;
		this.name = name;
		this.report = report;
		this.status = status;
	}

	public FinancialReport(Long id, AccountingStandard accountingStandard,
			String name, String report, Boolean status, Date createTime,
			Long creator, Date modifyTime, Long modifier) {
		this.id = id;
		this.accountingStandard = accountingStandard;
		this.name = name;
		this.report = report;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AccountingStandardID", nullable = false)
	public AccountingStandard getAccountingStandard() {
		return this.accountingStandard;
	}

	public void setAccountingStandard(AccountingStandard accountingStandard) {
		this.accountingStandard = accountingStandard;
	}

	@Column(name = "Name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Type(type="text")
	@Column(name = "Report", nullable = false, length = 65535)
	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	@Column(name = "Status", nullable = false)
	public Boolean isStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", updatable = false, length = 19)
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
