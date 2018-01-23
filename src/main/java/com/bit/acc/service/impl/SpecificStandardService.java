package com.bit.acc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Generated 2017-11-20 23:50:31 by Hibernate Tools 3.4.0.CR1

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bit.acc.dao.intfs.ISpecificStandardDao;
import com.bit.acc.model.SpecificStandard;
import com.bit.acc.service.intfs.ISpecificStandardService;
import com.bit.common.db.AbstractService;
import com.bit.common.db.IOperations;

/**
 * Home object for domain model class Specificstandard.
 * @see com.bit.acc.dao.impl.SpecificStandard
 * @author Hibernate Tools
 */
@Service("specificStandardService")
public class SpecificStandardService extends AbstractService<SpecificStandard> implements ISpecificStandardService{

	@Resource(name = "specificStandardDao")
	private ISpecificStandardDao dao;

	@Override
	protected IOperations<SpecificStandard> getDao() {
		return this.dao;
	}
	
	public List<SpecificStandard> queryByAccStandard(Object accountingStandardID) {
		List<SpecificStandard> listSpecificStandard = dao.queryByAccStandard(accountingStandardID);
		return listSpecificStandard;
	}
	
	public List<SpecificStandard> queryByAccStandard(String accountingStandardCode, int exeYear) {
		List<SpecificStandard> listSpecificStandard = dao.queryByAccStandard(accountingStandardCode, exeYear);
		/*SpecificStandard specificStandard = null;
		if(listSpecificStandard != null && listSpecificStandard.size() > 0)
			specificStandard = listSpecificStandard.get(0);
		return specificStandard;*/
		return listSpecificStandard;
	}
	
	public Map<String, Object> getTitlesByAccStandard(String accountingStandardCode, int exeYear) {
		List<SpecificStandard> listSpecificStandard = dao.getTitlesByAccStandard(accountingStandardCode, exeYear);
		Map<String, Object> mapResult = new HashMap<String, Object>( );
		
		Map< String, String> mapPreface = new HashMap< String, String>();
		mapPreface.put("titile", null);
		mapPreface.put("preface", null);
		
		mapResult.put( "preface", mapPreface );
		mapResult.put("chapters", new ArrayList< Map<String, Object> >() );
		
		for( SpecificStandard specificStandard : listSpecificStandard ) {
			if( specificStandard.isIsPreface() ) {
				mapPreface.put( "titile", specificStandard.getTitle() );
				mapPreface.put( "preface", specificStandard.getSpecifics() );
			} else {
				Map<String, Object> mapChapter = new HashMap<String, Object>();
				mapChapter.put( "id", specificStandard.getId() );
				mapChapter.put( "titile", specificStandard.getTitle() );
				mapChapter.put( "specifics", specificStandard.getSpecifics() );
				( (List< Map<String, Object> >) mapResult.get( "chapters" ) ).add( mapChapter );
			}
		}
		return mapResult;
	}

}
