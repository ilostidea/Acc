package com.bit.acc.service;

import com.bit.acc.dao.AnswerRepository;
import com.bit.acc.dao.QuestionRepository;
import com.bit.acc.model.Answer;
import com.bit.acc.model.Question;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

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
            entity.setAnswerCount(0);
            entity.setCollectedCount(0);
        	entity.setIsAccused(false);
        	if(entity.isIsAnonymous() == null)
        		entity.setIsAnonymous(false);
        	entity.setStatus(true);
    	}
    	return super.save(entity);
    }

    @Override
    public void switchStatus(Long id, Boolean status) {
    	dao.switchStatus(id, status);
    }

    @Override
    public void answerCountAdd(Long id, int count){
        dao.answerCountAdd(id, count);
    }
    
	@Override
	public Map<String, Object> findRecent(Long userId, Pageable pageable) {
		Long curUserId = 0l;
    	if(userId != null)
			curUserId = userId;
		Page<Object> list = dao.findRecent(curUserId, pageable );
		Map<String, Object> resultMap = new HashMap<>();
		List<Question> resultList = new ArrayList<>();
		for(Object o : list ) {
			Object[] array = (Object[]) o;
			Question question = (Question) array[0];
			Boolean hasCollected = array[1]==null?Boolean.FALSE:Boolean.TRUE;
			question.setHasCollected(hasCollected);
			resultList.add(question);
		}
		resultMap.put("list", resultList);
		resultMap.put("total", list.getTotalElements());
		resultMap.put("totalPages", list.getTotalPages());
		return resultMap;
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
                    predicates.add( accused ? criteriaBuilder.isTrue( root.get("isAccused") ) : criteriaBuilder.isFalse( root.get("isAccused") ) );
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
	public Question getQuesstionAndAnswersByIdAndUser(Long id, Long userId) {
		Long curUserId = 0l;
		if(userId != null)
			curUserId = userId;
		Object[] array = (Object[]) dao.getQuesstionAnswersByIdAndUser(id, userId);
		Question question = (Question) array[0];
		Boolean hasCollected = array[1]==null?Boolean.FALSE:Boolean.TRUE;
		question.setHasCollected(hasCollected);
		List<Object> answers = answerDao.findByQuestion(id, userId);
		Set answerSet = new TreeSet<Answer>();
		for(Object o : answers ) {
			array = (Object[]) o;
			Answer answer = (Answer) array[0];
			Boolean answerHasCollected = array[1]==null?Boolean.FALSE:Boolean.TRUE;
            Boolean answerHasApproved = array[2]==null?Boolean.FALSE:Boolean.TRUE;
            Boolean answerHasDisapproved = array[3]==null?Boolean.FALSE:Boolean.TRUE;
            answer.setHasCollected(answerHasCollected);
            answer.setHasApproved(answerHasApproved);
            answer.setHasDisapproved(answerHasDisapproved);
            answerSet.add(answer);
		}
		question.setAnswers( answerSet );
		return question;
	}

	/**
	 * 不管是启用还是禁用的都查出来了
	 */
	@Override
	public Question getQuesstionAndAnswersByIdAndUserForAdmin(Long id) {
		Object[] array = (Object[]) dao.getQuesstionAnswersByIdAndUserForAdmin(id, 0l);//不用判断管理员是否已收藏问题，传入一个不存在的用户ID=0即可
		Question question = (Question) array[0];
		List<Answer> answers = answerDao.findByQuestionForAdmin(id);
		question.setAnswers(new TreeSet<Answer>(answers) );
		return question;
	}

	@Override
	public Map<String, Long> getQuestionProfileById(Long userId) {
		return dao.getQuestionProfileById(userId);
	}

}
