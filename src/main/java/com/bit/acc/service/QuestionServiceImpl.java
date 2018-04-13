package com.bit.acc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.AnswerRepository;
import com.bit.acc.dao.QuestionRepository;
import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.QuestionService;

@Service("questionService")
public class QuestionServiceImpl extends AbstractService<Question, Long> implements QuestionService {

    @Autowired
	private QuestionRepository dao;
	
    @Autowired
	private AnswerRepository answerDao;
    
    protected JpaRepository<Question, Long> getDao( ) {
    	return dao;
    }

    @Override
    public <S extends Question> S save(S entity) {
    	if (entity.getId() == null) {
        	entity.setApproveCount(0);
        	entity.setDisapproveCount(0);
        	entity.setIsAccused(false);
        	if(entity.isIsAnonymous() == null)
        		entity.setIsAnonymous(false);
        	entity.setStatus(true);
    	}
    	return super.save(entity);
    }
    
	@Override
	public List<Question> findRecent() {
		return dao.findRecent( );
	}

	@Override
	public List<Question> findByCondition(String userName, String question, Boolean status, Boolean accused) {
		List<Question> resultList = null;
		
        @SuppressWarnings("serial")
		Specification<Question> querySpecifi = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if( userName != null && userName.length() > 0 ){
                    predicates.add( criteriaBuilder.or( criteriaBuilder.equal( root.get( "user" ).get("mobile"), userName),
    						criteriaBuilder.equal( root.get( "user" ).get("email"), userName ), 
    						criteriaBuilder.equal( root.get( "user" ).get("nickName"), userName ) ) );
                }
                if( question != null && question.length() > 0 ){
                    predicates.add( criteriaBuilder.like( root.get( "question" ), "%" + question + "%") );
                }
                if( status != null ){
                    predicates.add( status ? criteriaBuilder.isTrue( root.get("status") ) : criteriaBuilder.isFalse( root.get("status") ) );
                }
                if( accused != null ){
                    predicates.add( status ? criteriaBuilder.isTrue( root.get("isAccused") ) : criteriaBuilder.isFalse( root.get("isAccused") ) );
                }
                return criteriaBuilder.and( predicates.toArray( new Predicate[ predicates.size() ] ) );
                //return criteriaQuery.where( predicates.toArray( new Predicate[ predicates.size() ] ) ).getRestriction();
            }
        };
        resultList =  dao.findByCondition(querySpecifi);
        return resultList;
		//return dao.queryForAdmin(userName, question, status, accused);
	}

	@Override
	public List<Question> findByUser(Long userId) {
		return dao.findByUser(userId);
	}

	@Override
	public List<Question> findByAnsweredUser(Long userId) {
		return dao.findByAnsweredUser(userId);
	}

	@Override
	public List<Question> findByCollectedUser(Long userId) {
		return dao.findByCollectedUser(userId);
	}

	@Override
	public Question getQuesstionAndAnswersById(Long id) {
		return dao.getQuesstionAndAnswersById(id);
	}

	@Override
	public Question getQuesstionAndAnswersPumpCountById(Long id) {
		Object[] array = (Object[]) dao.getQuesstionAndAnswersPumpCountById(id);
		Question question = (Question) array[0];
		Long answerCount = (Long) array[1];
		Long collectedCount = (Long) array[2];
		question.setAnswerCount(answerCount);
		question.setCollectedCount(collectedCount);
		List<Answer> answers = answerDao.findByQuestion(id); 
		for(Answer answer : answers) {
			answer.setPumpCount( (long) answer.getPumps().size() );
		}
		question.setAnswers(new HashSet<Answer>(answers) );
		return question;
	}

	@Override
	public Map<String, Long> getQuestionProfileById(Long userId) {
		return dao.getQuestionProfileById(userId);
	}

}
