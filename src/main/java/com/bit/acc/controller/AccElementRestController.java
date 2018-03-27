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

import com.bit.acc.model.AccElement;
import com.bit.acc.service.intfs.AccElementService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/accElement")
public class AccElementRestController {

    @Resource(name="accElementService")
    private AccElementService accElementService;
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody AccElement accElement, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	accElementService.save(accElement);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody AccElement accElement, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	accElementService.save(accElement);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/del",method=RequestMethod.POST)
    public Response del(@RequestParam("accElementID") long accElementID) {
    	accElementService.deleteById(accElementID);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部科目分类")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<AccElement> standardList = accElementService.findAll();
        return new Response().success(standardList);
    }
    
    /**
     * 通过准则ID获得该准则的会计要素
     * @param accStandardID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则ID获得该准则的会计要素")
    public Response queryByAccStandard(@RequestParam("accStandardID") long accStandardID) throws Exception{
    	 List<AccElement>  listAccElement = accElementService.findByAccStandard(accStandardID);
        return new Response().success( listAccElement );
    }
    
    /**
     * 通过准则代码、执行年份获得该准则的会计要素
     * @param accStandardID
     * @param exeYear
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCodeYear",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则代码、执行年份获得该准则的会计要素")
    public Response queryByStandardCodeYear(@RequestParam("accStandardCode") String code, @RequestParam("exeYear") int exeYear) throws Exception{
    	List<AccElement> listAccElement = accElementService.findByAccStandard(code, exeYear);
        return new Response().success( listAccElement );
    }
    
    @RequestMapping(value="/admin/show/{accElementID}",method=RequestMethod.GET)
    public Response show(@PathVariable long accElementID){
    	AccElement accStandard = accElementService.findById(accElementID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/admin/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("accElementID") long accElementID){
    	AccElement accStandard = accElementService.findById(accElementID);
        return new Response().success(accStandard);
    }
}
