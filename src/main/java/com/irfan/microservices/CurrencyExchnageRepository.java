package com.irfan.microservices;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchnageRepository extends JpaRepository<CurrencyExchnage, Long> {
	
	CurrencyExchnage findByFromAndTo(String from,String to);

}
