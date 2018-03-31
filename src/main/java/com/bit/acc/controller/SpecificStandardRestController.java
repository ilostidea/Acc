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

import com.bit.acc.model.SpecificStandard;
import com.bit.acc.service.intfs.SpecificStandardService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/sp")
public class SpecificStandardRestController {

    @Resource(name="specificStandardService")
    private SpecificStandardService specificStandardService;
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody SpecificStandard specificStandard, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
        specificStandardService.save(specificStandard);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody SpecificStandard specificStandard, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	specificStandardService.save(specificStandard);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/del",method=RequestMethod.POST)
    public Response del(@RequestParam("spID") Long spID) {
    	specificStandardService.deleteById(spID);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部具体准则")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<SpecificStandard> standardList = specificStandardService.findAll();
        return new Response().success(standardList);
    }
    
    /**
     * 通过准则ID获得该准则的具体准则
     * @param accStandardID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/admin/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则ID获得该准则的具体准则")
    public Response queryByAccStandard(@RequestParam("accStandardID") Long accStandardID) throws Exception{
    	List<SpecificStandard> generalPrinciple = specificStandardService.findByAccStandard(accStandardID);
        return new Response().success(generalPrinciple);
    }
    
    /**
     * 通过准则代码、执行年份获得该准则的具体准则ID、标题
     * @param accStandardID
     * @param exeYear
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCodeYear",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则代码、执行年份获得该准则的具体准则标题")
    public Response queryByStandardCodeYear(@RequestParam("accStandardCode") String code, @RequestParam("exeYear") int exeYear) throws Exception{
    	Map<String, Object> prefaceAndListSpecificStandardAndPreface = specificStandardService.getTitlesByAccStandard(code, exeYear);
        return new Response().success( prefaceAndListSpecificStandardAndPreface );
    }
    
    @RequestMapping(value="/show/{spID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long spID){
    	SpecificStandard accStandard = specificStandardService.findById(spID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("spID") Long spID){
    	SpecificStandard accStandard = specificStandardService.findById(spID);
        return new Response().success(accStandard);
    }
}
