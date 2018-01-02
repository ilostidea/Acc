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

import com.bit.acc.model.GeneralPrinciple;
import com.bit.acc.service.intfs.IGeneralPrincipleService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/gp")
public class GeneralPrincipleRestController {

    @Resource(name="generalPrincipleService")
    private IGeneralPrincipleService generalPrincipleService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody GeneralPrinciple generalPrinciple, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
        generalPrincipleService.persist(generalPrinciple);
        return new Response().success(generalPrinciple);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody GeneralPrinciple generalPrinciple, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	generalPrincipleService.merge(generalPrinciple);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("gpID") long gpID) {
    	generalPrincipleService.remove(gpID);
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部基本准则")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<GeneralPrinciple> standardList = generalPrincipleService.queryAll();
        return new Response().success(standardList);
    }
    
    /**
     * 通过准则ID获得该准则的基本准则
     * @param accStandardID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则ID获得该准则的基本准则")
    public Response queryByAccStandard(@RequestParam("accStandardID") long accStandardID) throws Exception{
    	GeneralPrinciple generalPrinciple = generalPrincipleService.queryByAccStandard(accStandardID);
        return new Response().success(generalPrinciple);
    }
    
    /**
     * 通过准则代码、执行年份获得该准则的基本准则
     * @param accStandardID
     * @param exeYear
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCodeYear",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则代码、执行年份获得该准则的基本准则")
    public Response queryByStandardNameYear(@RequestParam("accStandardCode") String code, @RequestParam("exeYear") int exeYear) throws Exception{
    	GeneralPrinciple generalPrinciple = generalPrincipleService.queryByAccStandard(code, exeYear);
        return new Response().success(generalPrinciple);
    }
    
    @RequestMapping(value="/show/{gpID}",method=RequestMethod.GET)
    public Response show(@PathVariable long gpID){
    	GeneralPrinciple accStandard = generalPrincipleService.findById(gpID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("gpID") long gpID){
    	GeneralPrinciple accStandard = generalPrincipleService.findById(gpID);
        return new Response().success(accStandard);
    }
}
