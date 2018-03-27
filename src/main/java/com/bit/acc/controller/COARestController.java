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

import com.bit.acc.model.COA;
import com.bit.acc.service.intfs.COAService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/coa")
public class COARestController {

    @Resource(name="coaService")
    private COAService coaService;
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody COA coa, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
        coaService.save(coa);
        return new Response().success(coa);
    }
    
    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody COA coa, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	coaService.save(coa);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/del",method=RequestMethod.POST)
    public Response del(@RequestParam("coaID") long coaID) {
    	coaService.deleteById(coaID);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部科目")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<COA> listCOA = coaService.findAll();
        return new Response().success( listCOA );
    }
    
    /**
     * 通过准则ID获得该准则的科目表
     * @param accStandardID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/admin/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则ID获得该准则的科目表")
    public Response queryByAccStandard(@RequestParam("accStandardID") long accStandardID) throws Exception{
    	List<COA> listCOA = coaService.findByAccountingStandardId(accStandardID);
        return new Response().success(listCOA);
    }
    
    /**
     * 通过准则代码、执行年份获得该准则的科目
     * @param accStandardID
     * @param exeYear
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCodeYear",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则代码、执行年份获得该准则的科目")
    public Response queryByStandardNameYear(@RequestParam("accStandardCode") String code, @RequestParam("exeYear") int exeYear) throws Exception{
    	List<COA> listCOA = coaService.findByAccStandard(code, exeYear);
        return new Response().success(listCOA);
    }

    
    /**
     * 通过准则代码、执行年份、会计要素（科目分类）获得该准则的科目
     * @param accStandardID
     * @param exeYear
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByStdCodeYearElement",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则代码、执行年份、会计要素获得该准则的科目")
    public Response queryByStandardNameYearElement(@RequestParam("accStandardCode") String accStandardCode, @RequestParam("exeYear") int exeYear, @RequestParam("accElementCode") String elementCode) throws Exception{
    	List<COA> listCOA = coaService.findByAccStandardElement(accStandardCode, exeYear, elementCode);
        return new Response().success(listCOA);
    }
    
    @RequestMapping(value="/show/{coaID}",method=RequestMethod.GET)
    public Response show(@PathVariable long coaID){
    	COA coa = coaService.findById(coaID);
        return new Response().success(coa);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("coaID") long coaID){
    	COA coa = coaService.findById(coaID);
        return new Response().success(coa);
    }
}
