package com.bit.acc.service;

import com.bit.acc.dao.AnswerApprovedRepository;
import com.bit.acc.dao.AnswerDisapprovedRepository;
import com.bit.acc.dao.AnswerRepository;
import com.bit.acc.model.Answer;
import com.bit.acc.model.AnswerApproved;
import com.bit.acc.model.AnswerDisapproved;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("answerService")
public class AnswerServiceImpl extends AbstractService<Answer, Long> implements AnswerService {

	@Autowired
	private AnswerRepository dao;

	@Autowired
	private AnswerApprovedRepository approveDao;

    @Autowired
    private AnswerDisapprovedRepository disapproveDao;
	
	protected JpaRepository<Answer, Long> getDao() {
		return dao;
	}

    @Override
    public <S extends Answer> S save(S entity) {
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
	public List<Answer> findByQuestion(Long questionId, Long userId) {
        List<Object> answers = dao.findByQuestion(questionId, userId);
        List result = new ArrayList<Answer>();
        for(Object o : answers ) {
            Object[] array = (Object[]) o;
            Answer answer = (Answer) array[0];
            Boolean answerHasCollected = array[1]==null?Boolean.FALSE:Boolean.TRUE;
            Boolean answerHasApproved = array[2]==null?Boolean.FALSE:Boolean.TRUE;
            Boolean answerHasDisapproved = array[3]==null?Boolean.FALSE:Boolean.TRUE;
            answer.setHasCollected(answerHasCollected);
            answer.setHasApproved(answerHasApproved);
            answer.setHasDisapproved(answerHasDisapproved);
            result.add(answer);
        }
        return result;
	}

	@Override
	public List<Answer> findForAdmin(String userName, String answer, Boolean status, Boolean accused) {
		List<Answer> resultList = null;
		
        @SuppressWarnings("serial")
		Specification<Answer> querySpecifi = new Specification<Answer>() {
            @Override
            public Predicate toPredicate(Root<Answer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if( userName != null && userName.length() > 0 ){
                    predicates.add( criteriaBuilder.or( criteriaBuilder.equal( root.get( "user" ).get("mobile"), userName),
    						criteriaBuilder.equal( root.get( "user" ).get("email"), userName ), 
    						criteriaBuilder.equal( root.get( "user" ).get("nickName"), userName ) ) );
                }
                if( answer != null && answer.length() > 0 ){
                    predicates.add( criteriaBuilder.like( root.get( "answer" ), "%" + answer + "%") );
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
	}

	@Override
	public List<Answer> findByUser(Long userId) {
		return dao.findByUser(userId);
	}

	@Override
	public List<Answer> findByCollectedUser(Long userId) {
		return dao.findByCollectedUser(userId);
	}
	
	public Answer getAnswerQuestionPumps(Long id) {
		/*Object[] queryResult = dao.getAnswerQuestionPumps(id);
		Answer answer = (Answer) queryResult[1];
		answer.setQuestion( (Question) queryResult[0] );
		return answer;*/
		return dao.getAnswerQuestionPumps(id);
	}
    
    public void switchStatus(Long id, Boolean status) {
    	dao.switchStatus(id, status);
    }

	@Override
	public void approve(Long id, Long userId, AnswerDisapproved answerDisapproved) {
		dao.approveAdd(id);//增加点赞的次数，answer中的冗余字段approveCount加1
        AnswerApproved answerApproved = new AnswerApproved();
        SysUser user = new SysUser();
        user.setId(userId);
        answerApproved.setUser(user);
        Answer answer = new Answer();
        answer.setId(id);
        answerApproved.setAnswer(answer);
		approveDao.save(answerApproved);//增加用户对问题点赞的记录，重复点赞会抛出异常
		if(answerDisapproved != null){//如果点过踩，需要取消踩
            dao.disapproveMinus(id);//answer中的冗余字段disapproveCount减1
            disapproveDao.delete(answerDisapproved);//删除用户对问题踩的记录，记录不存在时会抛出异常
        }
	}

	@Override
	public void disapprove(Long id, Long userId, AnswerApproved answerApproved) {
		dao.disapproveAdd(id);
        AnswerDisapproved answerDisapproved = new AnswerDisapproved();
        SysUser user = new SysUser();
        user.setId(userId);
        answerDisapproved.setUser(user);
        Answer answer = new Answer();
        answer.setId(id);
        answerDisapproved.setAnswer(answer);
        disapproveDao.save(answerDisapproved);
        if(answerApproved != null){
            dao.approveMinus(id);
            approveDao.delete(answerApproved);
        }
	}

}
