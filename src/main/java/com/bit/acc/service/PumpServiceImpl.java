package com.bit.acc.service;

import com.bit.acc.dao.AnswerRepository;
import com.bit.acc.dao.PumpRepository;
import com.bit.acc.model.Pump;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.PumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pumpService")
public class PumpServiceImpl extends AbstractService<Pump, Long> implements PumpService {

	@Autowired
	private PumpRepository dao;

	@Autowired
	private AnswerRepository answerDao;
	
	protected JpaRepository<Pump, Long> getDao() {
		return dao;
	}

    @Override
    public <S extends Pump> S save(S entity) {
    	if (entity.getId() == null) {
        	entity.setIsAccused(false);
        	if(entity.isIsAnonymous() == null)
        		entity.setIsAnonymous(false);
        	entity.setStatus(true);
			answerDao.pumpCountAdd(entity.getAnswer().getId(), 1);
    	}
    	return super.save(entity);
    }

    @Override
    public void delete(Pump entity) {
        super.delete(entity);
        answerDao.pumpCountAdd(entity.getAnswer().getId(), -1);
    }

    @Override
    public void deleteById(Long id) {
        Pump pump = super.getOne(id);
        super.deleteById(id);
        answerDao.pumpCountAdd(pump.getAnswer().getId(), -1);
    }

    public void switchStatus(Long id, Boolean status) {
    	dao.switchStatus(id, status);
    }

	@Override
	public List<Pump> findByAnswer(Long answerId) {
		return dao.findByAnswerId(answerId);
	}

}
