package com.bit.acc.controller;

import java.util.List;

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

import com.bit.acc.model.AccUsage;
import com.bit.acc.service.intfs.IAccUsageService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/accUsage")
public class AccUsageRestController {

    @Resource(name="accUsageService")
    private IAccUsageService accUsageService;
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody AccUsage accUsage, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
        accUsageService.persist(accUsage);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody AccUsage accUsage, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	accUsageService.merge(accUsage);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/del",method=RequestMethod.POST)
    public Response del(@RequestParam("accUsageID") long accUsageID) {
    	accUsageService.remove(accUsageID);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部科目使用方法")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<AccUsage> accUsageList = accUsageService.queryAll();
        return new Response().success(accUsageList);
    }
    
    /**
     * 通过科目ID获得该科目的使用方法
     * @param coaID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过科目ID获得该科目的使用方法")
    public Response queryByAcc(@RequestParam("coaID") long coaID) throws Exception{
    	List<AccUsage> accUsage = accUsageService.queryByAcc(coaID);
        return new Response().success(accUsage);
    }
    
    @RequestMapping(value="/show/{accUsageID}",method=RequestMethod.GET)
    public Response show(@PathVariable long accUsageID){
    	AccUsage accStandard = accUsageService.findById(accUsageID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("accUsageID") long accUsageID){
    	AccUsage accStandard = accUsageService.findById(accUsageID);
        return new Response().success(accStandard);
    }
}
