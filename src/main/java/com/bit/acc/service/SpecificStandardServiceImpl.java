package com.bit.acc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bit.acc.dao.SpecificStandardRepository;
import com.bit.acc.model.SpecificStandard;
import com.bit.acc.service.baseservice.AbstractService;
import com.bit.acc.service.intfs.SpecificStandardService;

@Service("specificStandardService")
public class SpecificStandardServiceImpl extends AbstractService<SpecificStandard, Long> implements SpecificStandardService {

	@Autowired
	private SpecificStandardRepository dao;

	@Override
	protected JpaRepository<SpecificStandard, Long> getDao() {
		return this.dao;
	}

    public List<SpecificStandard> findByAccStandard(Long accountingStandardId) {
    	return dao.findByAccountingStandardId(accountingStandardId);
    }
	
	public List<SpecificStandard> findByAccStandard(String accountingStandardCode, int exeYear) {
		return dao.findByAccountingStandardCodeAndAccountingStandardExeYear(accountingStandardCode, exeYear);
	}

	public  Map<String, Object> getTitlesByAccStandard(String accountingStandardCode, int exeYear)  {
		List<SpecificStandard> listSpecificStandard = dao.getTitlesByAccStandard(accountingStandardCode, exeYear);
		Map<String, Object> mapResult = new HashMap<String, Object>( );
		
		Map< String, String> mapPreface = new HashMap< String, String>();
		mapPreface.put("title", null);
		mapPreface.put("preface", null);
		
		mapResult.put( "preface", mapPreface );
		mapResult.put("chapters", new ArrayList< Map<String, Object> >() );
		
		for( SpecificStandard specificStandard : listSpecificStandard ) {
			if( specificStandard.isIsPreface() ) {
				mapPreface.put( "title", specificStandard.getTitle() );
				mapPreface.put( "preface", specificStandard.getSpecifics() );
			} else {
				Map<String, Object> mapChapter = new HashMap<String, Object>();
				mapChapter.put( "id", specificStandard.getId() );
				mapChapter.put( "title", specificStandard.getTitle() );
				mapChapter.put( "specifics", specificStandard.getSpecifics() );
				( (List< Map<String, Object> >) mapResult.get( "chapters" ) ).add( mapChapter );
			}
		}
		return mapResult;
	}
	
}
