package com.bit.acc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit.acc.model.AccountingStandard;
import com.bit.acc.service.intfs.AccountingStandardService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/accStandard")
public class AccStandardRestController {

    @Resource(name="accStandardService")
    private AccountingStandardService accountingStandardService;
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody AccountingStandard accountingStandard, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
        accountingStandardService.save(accountingStandard);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody AccountingStandard accountingStandard, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	accountingStandardService.save(accountingStandard);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/del",method=RequestMethod.POST)
    public Response del(@RequestParam("accStandardID") Long accStandardID) throws Exception{
		accountingStandardService.deleteById(accStandardID);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部准则")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<AccountingStandard> standardList = accountingStandardService.findAll();
        return new Response().success(standardList);
    }
    
    @RequestMapping(value="/show/{accStandardID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long accStandardID){
    	AccountingStandard accStandard = accountingStandardService.findById(accStandardID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("accStandardID") Long accStandardID){
    	AccountingStandard accStandard = accountingStandardService.findById(accStandardID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/getDistinctName",method=RequestMethod.GET)
    @ControllerLog(value = "获得准则的名称和编号")
    public Response queryDistinctNameCode() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
    	List<Map<String, Object>> standardNameCodeList = accountingStandardService.getNameExeyearsWithDistinctCode();
        return new Response().success(standardNameCodeList);
    }
}
