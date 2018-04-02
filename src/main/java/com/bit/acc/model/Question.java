package com.bit.acc.model;
// Generated 2018-2-3 19:27:28 by Hibernate Tools 5.2.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Question generated by hbm2java
 */
@Entity
@Table(name = "question", catalog = "acc")
@DynamicInsert(true)
@DynamicUpdate(true)
@JsonIgnoreProperties(value={"approveCount", "disapproveCount", "createTime", "creator", "modifyTime", "modifier", "questionCollecteds"}/*, ignoreUnknown = true*/)
@NamedEntityGraphs({
	@NamedEntityGraph(name = "question.answers.pumpscount", 
            attributeNodes = {//attributeNodes 来定义需要加载的关联属性
                //@NamedAttributeNode("questionCollecteds"), 
                @NamedAttributeNode(value = "answers", subgraph = "pumps")
            },
            subgraphs = {//subgraphs 来定义关联对象的属性
                    @NamedSubgraph(name = "pumps", attributeNodes = @NamedAttributeNode("pumps"))/*,
                    @NamedSubgraph(name = "answer",//两层延伸
                            attributeNodes = @NamedAttributeNode( value = "answer", subgraph = "pumps")
                    ) */
            }
	)
})
public class Question implements java.io.Serializable {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = 116487971975059611L;
	private Long id;
	private SysUser user;
	private String title;
	private String question;
	private String tag;
	private Boolean isAnonymous;
	private Integer approveCount;
	private Integer disapproveCount;
	private Boolean isAccused;
	private Boolean status;
	private Date createTime;
	private Long creator;
	private Date modifyTime;
	private Long modifier;
	private Set<Answer> answers = new HashSet<Answer>(0);
	private Set<QuestionCollected> questionCollecteds = new HashSet<QuestionCollected>(0);
	private Long answerCount;
	private Long collectedCount;

	public Question() {
	}

	public Question(Question qeustion, Long answerCount, Long collectedCount ) {
		this.id = qeustion.id;
		this.user = qeustion.user;
		this.title = qeustion.title;
		this.question = qeustion.question;
		this.tag = qeustion.tag;
		this.isAnonymous = qeustion.isAnonymous;
		this.approveCount = qeustion.approveCount;
		this.disapproveCount = qeustion.disapproveCount;
		this.isAccused = qeustion.isAccused;
		this.status = qeustion.status;
		this.createTime = qeustion.createTime;
		this.creator = qeustion.creator;
		this.modifyTime = qeustion.modifyTime;
		this.modifier = qeustion.modifier;
		this.answers = qeustion.answers;
		this.questionCollecteds = qeustion.questionCollecteds;
		this.answerCount = answerCount;
		this.collectedCount = collectedCount;
	}

	public Question(Long id, SysUser user, String title, String question, Boolean isAnonymous, Boolean isAccused) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.question = question;
		this.isAnonymous = isAnonymous;
		this.isAccused = isAccused;
	}

	public Question(Long id, SysUser user, String title, String question, Boolean isAnonymous, Integer approveCount,
			Integer disapproveCount, Boolean isAccused, Boolean status, Date createTime, Long creator, Date modifyTime,
			Long modifier, Long answerCount, Long collectedCount) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.question = question;
		this.isAnonymous = isAnonymous;
		this.approveCount = approveCount;
		this.disapproveCount = disapproveCount;
		this.isAccused = isAccused;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
		this.answerCount = answerCount;
		this.collectedCount = collectedCount;
	}

	public Question(Long id, SysUser user, String title, String question, Boolean isAnonymous, Integer approveCount,
			Integer disapproveCount, Boolean isAccused, Boolean status, Date createTime, Long creator, Date modifyTime,
			Long modifier, List<Answer> answers/*, Set<QuestionCollected> questionCollecteds*/, Long answerCount, Long collectedCount) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.question = question;
		this.isAnonymous = isAnonymous;
		this.approveCount = approveCount;
		this.disapproveCount = disapproveCount;
		this.isAccused = isAccused;
		this.status = status;
		this.createTime = createTime;
		this.creator = creator;
		this.modifyTime = modifyTime;
		this.modifier = modifier;
		this.answers = new HashSet<Answer>( answers );
		//this.questionCollecteds = questionCollecteds;
		this.answerCount = answerCount;
		this.collectedCount = collectedCount;
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

	@Column(name = "Title", nullable = false, length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Type(type="text")
	@Column(name = "Question", nullable = false, length = 65535)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
	@Column(name = "Tag")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "IsAnonymous", nullable = false)
	public Boolean isIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	@Column(name = "ApproveCount")
	public Integer getApproveCount() {
		return this.approveCount;
	}

	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
	}

	@Column(name = "DisapproveCount")
	public Integer getDisapproveCount() {
		return this.disapproveCount;
	}

	public void setDisapproveCount(Integer disapproveCount) {
		this.disapproveCount = disapproveCount;
	}

	@Column(name = "IsAccused", nullable = false)
	public Boolean isIsAccused() {
		return this.isAccused;
	}

	public void setIsAccused(Boolean isAccused) {
		this.isAccused = isAccused;
	}

	@Column(name = "Status")
	public Boolean isStatus() {
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
	@Fetch(FetchMode.JOIN)
	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Transient
	public Long getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Long answerCount) {
		this.answerCount = answerCount;
	}

	@Transient
	public Long getCollectedCount() {
		return collectedCount;
	}

	public void setCollectedCount(Long collectedCount) {
		this.collectedCount = collectedCount;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	public Set<QuestionCollected> getQuestionCollecteds() {
		return this.questionCollecteds;
	}

	public void setQuestionCollecteds(Set<QuestionCollected> questionCollecteds) {
		this.questionCollecteds = questionCollecteds;
	}

}