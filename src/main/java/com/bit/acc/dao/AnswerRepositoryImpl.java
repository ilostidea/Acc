package com.bit.acc.dao;

import com.bit.acc.model.Answer;
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

public class AnswerRepositoryImpl {

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Answer> findByCondition(Specification<Answer> querySpecific) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Answer> criteriaQuery = criteriaBuilder.createQuery(Answer.class);
		Root<Answer> root = criteriaQuery.from(Answer.class);
		//root.fetch( root.getModel().getSingularAttribute("question"), JoinType.INNER);

		EntityType<Answer> answerModel = root.getModel();
		criteriaQuery.select( criteriaBuilder.construct( Answer.class,
				root.get( answerModel.getSingularAttribute( "id", Long.class ) ), 
				root.get( answerModel.getSingularAttribute( "question", Question.class ) ), 
				root.get( answerModel.getSingularAttribute( "user", SysUser.class ) ), 
				root.get( answerModel.getSingularAttribute( "answer", String.class ) ), 
				root.get( answerModel.getSingularAttribute( "isAnonymous", Boolean.class ) ), 
				root.get( answerModel.getSingularAttribute( "approveCount", Integer.class ) ), 
				root.get( answerModel.getSingularAttribute( "disapproveCount", Integer.class ) ),
				root.get( answerModel.getSingularAttribute( "pumpCount", Integer.class ) ),
				root.get( answerModel.getSingularAttribute( "collectedCount", Integer.class ) ),
				root.get( answerModel.getSingularAttribute( "isAccused", Boolean.class ) ),
				root.get( answerModel.getSingularAttribute( "status", Boolean.class ) ), 
				root.get( answerModel.getSingularAttribute( "createTime", Date.class ) ), 
				root.get( answerModel.getSingularAttribute( "creator", Long.class ) ), 
				root.get( answerModel.getSingularAttribute( "modifyTime", Date.class ) ), 
				root.get( answerModel.getSingularAttribute( "modifier", Long.class ) ),
				/*criteriaBuilder.count( root.join( answerModel.getSet( "pumps" ), JoinType.LEFT ) ),*/
				root.get( answerModel.getSingularAttribute( "question", Question.class ) ).get("title")
				) );

		criteriaQuery.where(querySpecific.toPredicate(root, criteriaQuery, criteriaBuilder));
		criteriaQuery.groupBy (root.get("id") );
		criteriaQuery.orderBy( criteriaBuilder.desc( root.get("createTime") ) );

		//EntityGraph graph = entityManager.getEntityGraph("answer.question");
		TypedQuery<Answer> query = entityManager.createQuery(criteriaQuery);//.setHint(org.hibernate.annotations.QueryHints.FETCHGRAPH, graph);
		return query.getResultList();
	}

}
