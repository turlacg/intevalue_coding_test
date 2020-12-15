package com.intevalue.ct03.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intevalue.ct03.entity.ExchangeRate;
import com.intevalue.ct03.exception.ResourceNotFoundException;
import com.intevalue.ct03.repository.ExchangeRateRepository;

@RestController
@RequestMapping("/api/exchangeRate")
public class ExchangeRateController {
	
	@Autowired
    private ExchangeRateRepository exchangeRateRepo;
	
    // update exchange rate list
	@GetMapping("/updateRateValues")
    public List<ExchangeRate> updateRateValues() {
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<ExchangeRate>> mapType = new TypeReference<List<ExchangeRate>>() {};
		InputStream is = TypeReference.class.getResourceAsStream("/json/exchangeRate.json");
		List<ExchangeRate> stateList = new ArrayList<ExchangeRate>();
		try {
			stateList = mapper.readValue(is, mapType);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		return exchangeRateRepo.saveAll(stateList);
    }
	
    // get exchange rate per currency
    @GetMapping("/getExchangeRate/{currency}")
    public ExchangeRate getExchangeRate(@PathVariable("currency") String currency) {
        return this.exchangeRateRepo.findDistinctByToCurrency(currency)
            .orElseThrow(()-> new ResourceNotFoundException("Exchange rate not found for currency :" + currency));
    }

}
