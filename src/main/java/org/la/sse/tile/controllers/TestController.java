package org.la.sse.tile.controllers;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.la.sse.tile.futur.FutureObject;
import org.la.sse.tile.futur.FutureTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class TestController {

	@Autowired
	private FutureTwo futureTwo;
	
	@Autowired
	private FutureObject futureObject;
	
	@GetMapping("/welcome")
	public ModelAndView firstPage() {
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("serviceUrl", "/sse/mvc/words");
		futureObject.setArray(new String[] {"hello", "world", "quick", "fox."});
		futureTwo.process();
		return mav;
	}
	
	@RequestMapping("/sec")
	public String secondPage() {
		return "second";
	}
	
	@RequestMapping(value="/registerform", method=RequestMethod.GET)
	public ModelAndView registerForm() throws InterruptedException, ExecutionException{
//		Future<Integer> future = new FutureTwo().calculate(10);
		System.out.println("futuretwo :::::::::::::::::::: ");
		futureObject.setArray(new String[] {"hello", "world", "quick", "fox."});
		futureObject.setNotEmpty(true);
		futureTwo.process();
		ModelAndView mav = new ModelAndView("registerForm");
		mav.addObject("serviceUrl", "/sse/mvc/background/process");
//		RestTemplate restTemplate = new RestTemplate ();
//		restTemplate.getForObject("http://localhost:8080/sse/mvc/background/process", SseEmitter.class);
		return mav;
    	
	}
}
