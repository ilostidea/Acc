package com.bit.acc.model;
// Generated 2018-2-3 19:27:28 by Hibernate Tools 5.2.6.Final

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Answer generated by hbm2java
 */
@Entity
@Table(name = "Answer", catalog = "acc")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value={"creator", "modifyTime", "modifier", "answerCollecteds"}/*, ignoreUnknown = true*/)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "answer.user",  
	    attributeNodes = @NamedAttributeNode("user") 
	),
	@NamedEntityGraph(name = "answer.userAndPumps",  
	    attributeNodes = {@NamedAttributeNode("user"), @NamedAttributeNode(value="pumps", subgraph = "pump")},
        subgraphs = {//subgraphs 来定义关联对象的属性
                @NamedSubgraph(name = "pump", attributeNodes = @NamedAttributeNode("user"))//获得pump的user
        }
	)
})
public class Answer implements java.io.Serializable, Comparable<Answer> {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = 5266090550855332610L;
	private Long id;
	private Question question;
	private SysUser user;
	private String answer;
	private Boolean isAnonymous;
	private Integer approveCount;
	private Integer disapproveCount;
	private Integer pumpCount;
	private Integer collectedCount;
	private Boolean isAccused;
	private Boolean status;
	private Date createTime;
	private Long creator;
	private Date modifyTime;
	private Long modifier;
	private Set<Pump> pumps = new HashSet<Pump>(0);
	private Set<AnswerCollected> answerCollecteds = new HashSet<AnswerCollected>(0);
	
	private String questionTitle;
	private Boolean hasCollected;//当前用户是否已关注该问题
	private Boolean hasApproved;//当前用户是否已关注该问题
	private Boolean hasDisapproved;//当前用户是否已关注该问题

	public Answer() {
	}

	public Answer(Long id, Question question, SysUser user, String answer, Boolean isAnonymous, Integer approveCount,
                  Integer disapproveCount, Integer pumpCount, Integer collectedCount, Boolean isAccused, Boolean status) {
		this.id = id;
		this.question = question;
		this.user = user;
        this.answer = answer;
		this.isAnonymous = isAnonymous;
        this.approveCount = approveCount;
        this.disapproveCount = disapproveCount;
        this.pumpCount = pumpCount;
        this.collectedCount = collectedCount;
		this.isAccused = isAccused;
        this.status = status;
	}
	
	public Answer(Long id, Question question, SysUser user, String answer, Boolean isAnonymous, Integer approveCount,
			    Integer disapproveCount, Integer pumpCount, Integer collectedCount, Boolean isAccused, Boolean status,
                  Date createTime, Long creator, Date modifyTime, Long modifier) {
		this.id = id;
		this.question = question;
		this.user = user;
		this.answer = answer;
		this.isAnonymous = isAnonymous;
		this.approveCount = approveCount;
		this.disapproveCount = disapproveCount;
		this.pumpCount = pumpCount;
		this.collectedCount = collectedCount;
		this.isAccused = isAccused;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
	}

	public Answer(Long id, Question question, SysUser user, String answer, Boolean isAnonymous, Integer approveCount,
			Integer disapproveCount, Integer pumpCount, Integer collectedCount, Boolean isAccused, Boolean status,
                  Date createTime, Long creator, Date modifyTime, Long modifier, String questionTitle) {
		this.id = id;
		this.question = question;
		this.user = user;
		this.answer = answer;
		this.isAnonymous = isAnonymous;
		this.approveCount = approveCount;
		this.disapproveCount = disapproveCount;
		this.pumpCount = pumpCount;
		this.collectedCount = collectedCount;
		this.isAccused = isAccused;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime =modifyTime;
		this.modifier = modifier;
		this.questionTitle = questionTitle;
	}

	public Answer(Long id, Question question, SysUser user, String answer, Boolean isAnonymous, Integer approveCount,
			Integer disapproveCount, Integer pumpCount, Integer collectedCount, Boolean isAccused, Boolean status,
                  Date createTime, Long creator, Date modifyTime, Long modifier, Set<Pump> pumps, Set<AnswerCollected> answerCollecteds) {
		this.id = id;
		this.question = question;
		this.user = user;
		this.answer = answer;
		this.isAnonymous = isAnonymous;
		this.approveCount = approveCount;
		this.disapproveCount = disapproveCount;
		this.pumpCount = pumpCount;
		this.collectedCount = collectedCount;
		this.isAccused = isAccused;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
		this.pumps = pumps;
		this.answerCollecteds = answerCollecteds;
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

	@JsonIgnore//即使没有这个注解也是忽略掉这个字段的
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "QuestionID", nullable = false)
	public Question getQuestion() {
		return this.question;
	}

	@JsonIgnore(false)
	public void setQuestion(Question question) {
		this.question = question;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", nullable = false)
	public SysUser getUser() {
		return this.user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	@Type(type="text")
	@Column(name = "Answer", length = 65535)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "IsAnonymous", nullable = false)
	public Boolean isIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	@Column(name = "ApproveCount", nullable = false)
	public Integer getApproveCount() {
		return this.approveCount;
	}

	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
	}

	@Column(name = "DisapproveCount", nullable = false)
	public Integer getDisapproveCount() {
		return this.disapproveCount;
	}

	public void setDisapproveCount(Integer disapproveCount) {
		this.disapproveCount = disapproveCount;
	}

	@Column(name = "PumpCount", nullable = false)
	public Integer getPumpCount() {
		return pumpCount;
	}

	public void setPumpCount(Integer pumpCount) {
		this.pumpCount = pumpCount;
	}

	@Column(name = "CollectedCount", nullable = false)
	public Integer getCollectedCount() {
		return collectedCount;
	}

	public void setCollectedCount(Integer collectedCount) {
		this.collectedCount = collectedCount;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("createTime asc")
	public Set<Pump> getPumps() {
		return this.pumps;
	}

	public void setPumps(Set<Pump> pumps) {
		this.pumps = pumps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
	@Fetch(FetchMode.SUBSELECT)
	public Set<AnswerCollected> getAnswerCollecteds() {
		return this.answerCollecteds;
	}

	public void setAnswerCollecteds(Set<AnswerCollected> answerCollecteds) {
		this.answerCollecteds = answerCollecteds;
	}

	@Transient
	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	@Transient
	public Boolean isHasCollected() {
		return hasCollected;
	}

	public void setHasCollected(Boolean hasCollected) {
		this.hasCollected = hasCollected;
	}

	@Transient
	public Boolean isHasApproved() {
		return hasApproved;
	}

	public void setHasApproved(Boolean hasApproved) {
		this.hasApproved = hasApproved;
	}

	@Transient
	public Boolean isHasDisapproved() {
		return hasDisapproved;
	}

	public void setHasDisapproved(Boolean hasDisapproved) {
		this.hasDisapproved = hasDisapproved;
	}

	@Override
	public int compareTo(Answer a) {
		if(this.createTime == null)
			return -1;
		if(a.getCreateTime() == null)
			return 1;
		return -createTime.compareTo( a.getCreateTime() );
	}
}
