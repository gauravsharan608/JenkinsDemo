package com.mindtree.controller.SpringBootApp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sdet.dto.ReportDto;
import com.sdet.exceptions.InvalidDateException;
import com.sdet.exceptions.InvalidEmailIdException;
import com.sdet.exceptions.InvalidPhoneNumberException;
import com.sdet.exceptions.InvalidTestNameException;
import com.sdet.service.DiagnosticService;
import com.sdet.service.DiagnosticServiceImplementations;

@EnableAutoConfiguration
@RestController
@RequestMapping("/diagnostic")
@CrossOrigin
/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * ********************CONTROLLER LAYER ****************** * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class HomeController 
{

	private DiagnosticService serviceObject;
	/* * * * * * * *
	 * Constructor * 
	 * * * * * * * */
	public HomeController()
	{
		super();
		serviceObject = new DiagnosticServiceImplementations();
	}

	/* * * * * * * *
	 * SERVER HOME * 
	 * * * * * * * */
	@GetMapping(value = "/")
	public @ResponseBody String serverHome() {
		System.out.println("home page");
		return "{\"message\":\"Welcome To Diagnostic Center\"}";
	}

	/** * * * * * * * * * * * * * * * * * * * * * *
	 *    FUNCTION TO FETCH REPORT BY PHONE NUMBER   * 
	 * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/getbyphone/{phone}/{date}", method = RequestMethod.GET)
	public String generateReportsByPhone(@PathVariable("phone") long phone, @PathVariable("date") String date) throws InvalidPhoneNumberException 
	{
		Gson gson = new Gson();
		ReportDto resultObject = new ReportDto() ;
		try {
			if(!date.matches("\\d{4}-\\d{2}-\\d{2}"))
			{
				throw new InvalidDateException("Enter a Valid Date");
			}
			if(!String.valueOf(phone).matches("\\d{10}"))
			{
				throw new InvalidDateException("Enter a Valid Phone Number.");
			}
			resultObject=serviceObject.generateReportsByPhone(phone, date);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return gson.toJson(resultObject);
	}
	/** * * * * * * * * * * * * * * * * * * * * * *
	 *    FUNCTION TO FETCH REPORT BY EMAIL NUMBER   * 
	 * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/getbyemail/{email}/{date}", method = RequestMethod.GET)
	public String generateReportsByEmail(@PathVariable("email") String email, @PathVariable("date") String date) throws InvalidEmailIdException 
	{
		Gson gson = new Gson();
		ReportDto resultObject = new ReportDto() ; 
		try 
		{
			resultObject=serviceObject.generateReportsByEmail(email, date);
		}catch(Exception e) 
		{
			System.out.println(e);
		}
		return gson.toJson(resultObject);
	}

	/** * * * * * * * * * * * * * * * * * * * * * *
	 *       FUNCTION TO ADD REPORT BY EMAIL       * 
	 * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/addbyemail", method = RequestMethod.POST)
	public void addDiagnosticTestsByEmail(@RequestParam("email") String email,@RequestParam("testname") String testName) throws InvalidTestNameException, InvalidEmailIdException 
	{
		try 
		{
			serviceObject.addDiagnosticTestsByEmail(email, testName);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/** * * * * * * * * * * * * * * * * * * * * * *
	 *    FUNCTION TO ADD REPORT BY PHONE NUMBER   * 
	 * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/addbyphone", method = RequestMethod.POST)
	public void addDiagnosticTestsByPhone(@RequestParam("phone") long number,@RequestParam("testName") String testName) throws InvalidTestNameException, InvalidPhoneNumberException 
	{
		try 
		{
			serviceObject.addDiagnosticTestsByPhone(number, testName);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
