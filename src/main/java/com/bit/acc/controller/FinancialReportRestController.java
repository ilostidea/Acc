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

import com.bit.acc.model.FinancialReport;
import com.bit.acc.model.FinancialReport;
import com.bit.acc.service.intfs.IFinancialReportService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/rp")
public class FinancialReportRestController {

    @Resource(name="financialReportService")
    private IFinancialReportService financialReportService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody FinancialReport financialReport, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
        financialReportService.persist(financialReport);
        return new Response().success();
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody FinancialReport financialReport, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	financialReportService.merge(financialReport);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("rpID") long rpID) {
    	financialReportService.remove(rpID);
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部财务报表")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<FinancialReport> standardList = financialReportService.queryAll();
        return new Response().success(standardList);
    }
    
    /**
     * 通过准则ID获得该准则的财务报表
     * @param accStandardID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则ID获得该准则的财务报表")
    public Response queryByAccStandard(@RequestParam("accStandardID") long accStandardID) throws Exception{
    	List<FinancialReport> listFinancialReport = financialReportService.queryByAccStandard(accStandardID);
        return new Response().success( listFinancialReport );
    }
    
    /**
     * 通过准则代码、执行年份获得该准则的财务报表
     * @param accStandardID
     * @param exeYear
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCodeYear",method=RequestMethod.GET)
    @ControllerLog(value = "通过准则代码、执行年份获得该准则的财务报表")
    public Response queryByStandardNameYear(@RequestParam("accStandardCode") String code, @RequestParam("exeYear") int exeYear) throws Exception{
    	List<FinancialReport> listFinancialReport = financialReportService.queryByAccStandard(code, exeYear);
        return new Response().success(listFinancialReport);
    }
    
    @RequestMapping(value="/show/{rpID}",method=RequestMethod.GET)
    public Response show(@PathVariable long rpID){
    	FinancialReport accStandard = financialReportService.findById(rpID);
        return new Response().success(accStandard);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("rpID") long rpID){
    	FinancialReport accStandard = financialReportService.findById(rpID);
        return new Response().success(accStandard);
    }
}
