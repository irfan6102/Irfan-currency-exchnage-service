package com.irfan.microservices;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger=LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchnageRepository repo;
	@Autowired
	private Environment enviroment;
	
	@GetMapping("/currency-exchnage/from/{from}/to/{to}")
	public CurrencyExchnage retrieveExchnageValue(@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		logger.info("retrieveExchnageValue called {} to {}",from,to);
		
//		 CurrencyExchnage currencyExchnage= new 
//				 CurrencyExchnage(1000L,from,to,BigDecimal.valueOf(50));
		
		CurrencyExchnage currencyExchnage=repo.findByFromAndTo(from, to);
		
		if(currencyExchnage==null) {
			throw new RuntimeException("Unable to find data for "+from+" to "+to);
		}
		 
		 String port=enviroment.getProperty("local.server.port");
		 currencyExchnage.setEnvoirement(port);
		 return currencyExchnage;
		
	}
	
	@GetMapping("/hello")
	public String helloMsg() {
		return "Welcome";
	}

}
