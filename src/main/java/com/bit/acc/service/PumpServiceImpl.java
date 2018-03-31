package com.bit.acc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.PumpRepository;
import com.bit.acc.model.Pump;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.PumpService;

@Service("pumpService")
public class PumpServiceImpl extends AbstractService<Pump, Long> implements PumpService {

	@Autowired
	private PumpRepository dao;
	
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
    	}
    	return super.save(entity);
    }

	@Override
	public List<Pump> findByAnswer(Long answerId) {
		return dao.findByAnswerId(answerId);
	}

}
