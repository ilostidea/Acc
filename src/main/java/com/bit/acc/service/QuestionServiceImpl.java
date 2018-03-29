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
        	entity.setStatus(true);
    	}
    	return super.save(entity);
    }
    
	@Override
	public List<Question> queryRecent() {
		return dao.queryRecent( );
	}

	@Override
	public List<Question> queryForAdmin(String userName, String question, Boolean status, Boolean accused) {
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
        resultList =  dao.queryForAdmin(querySpecifi);
        return resultList;
		//return dao.queryForAdmin(userName, question, status, accused);
	}

	@Override
	public List<Question> queryByUser(long userID) {
		return dao.queryByUser(userID);
	}

	@Override
	public List<Question> queryByAnsweredUser(long userID) {
		return dao.queryByAnsweredUser(userID);
	}

	@Override
	public List<Question> queryByCollectedUser(long userID) {
		return dao.queryByCollectedUser(userID);
	}

	@Override
	public Question getQuesstionAndAnswersById(long id) {
		return dao.getQuesstionAndAnswersById(id);
	}

	@Override
	public Question getQuesstionAndAnswersPumpCountById(long id) {
		Question question = dao.getQuesstionAndAnswersPumpCountById(id);
		 List<Answer> answers = null;//answerDao.queryByQuestion(id); //TODO
		 question.setAnswers(new HashSet<Answer>(answers) );
		return question;
	}

	@Override
	public Map getQuestionProfileById(long userID) {
		return dao.getQuestionProfileById(userID);
	}

}
