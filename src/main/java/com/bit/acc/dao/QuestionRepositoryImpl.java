package com.bit.acc.dao;

import com.bit.acc.model.Question;
import com.bit.acc.model.SysUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.Date;
import java.util.List;

public class QuestionRepositoryImpl {

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Question> findByCondition(Specification<Question> querySpecific) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
		
		Root<Question> root = criteriaQuery.from(Question.class);
		
		EntityType<Question> qestionModel = root.getModel();
		criteriaQuery.select( criteriaBuilder.construct( Question.class,
				root.get( qestionModel.getSingularAttribute( "id", Long.class ) ), 
				root.get( qestionModel.getSingularAttribute( "user", SysUser.class ) ), 
				root.get( qestionModel.getSingularAttribute( "title", String.class ) ), 
				root.get( qestionModel.getSingularAttribute( "question", String.class ) ), 
				root.get( qestionModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
				root.get( qestionModel.getSingularAttribute( "approveCount", Integer.class ) ), 
				root.get( qestionModel.getSingularAttribute( "disapproveCount", Integer.class ) ),
				root.get( qestionModel.getSingularAttribute( "answerCount", Integer.class ) ),
				root.get( qestionModel.getSingularAttribute( "collectedCount", Integer.class ) ),
				root.get( qestionModel.getSingularAttribute( "isAccused", Boolean.class ) ),
				root.get( qestionModel.getSingularAttribute( "status", Boolean.class ) ), 
				root.get( qestionModel.getSingularAttribute( "createTime", Date.class ) ), 
				root.get( qestionModel.getSingularAttribute( "creator", Long.class ) ), 
				root.get( qestionModel.getSingularAttribute( "modifyTime", Date.class ) ), 
				root.get( qestionModel.getSingularAttribute( "modifier", Long.class ) )/*,
				criteriaBuilder.countDistinct( root.join( qestionModel.getSet( "answers" ), JoinType.LEFT ) ),
				criteriaBuilder.countDistinct( root.join( qestionModel.getSet("questionCollecteds"), JoinType.LEFT ) )*/
				) );
		
		criteriaQuery.where(querySpecific.toPredicate(root, criteriaQuery, criteriaBuilder));
		criteriaQuery.groupBy(root.get("id"));
		criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );
		TypedQuery<Question> query = entityManager.createQuery(criteriaQuery);
		
		return query.getResultList();
		
	}

}
