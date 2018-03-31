package com.bit.acc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.AnswerRepository;
import com.bit.acc.model.Answer;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.AnswerService;

@Service("answerService")
public class AnswerServiceImpl extends AbstractService<Answer, Long> implements AnswerService {

	@Autowired
	private AnswerRepository dao;
	
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
	public List<Answer> findByQuestion(Long questionId) {
		return dao.findByQuestion(questionId);
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
                    predicates.add( status ? criteriaBuilder.isTrue( root.get("isAccused") ) : criteriaBuilder.isFalse( root.get("isAccused") ) );
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

	@Override
	public void approve(Long id) {
		dao.approve(id);
	}

	@Override
	public void disapprove(Long id) {
		dao.disapprove(id);
	}

}
