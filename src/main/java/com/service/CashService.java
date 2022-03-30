package com.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.model.Cash;


public interface  CashService {

	public Cash postCashDetails(@Valid Cash cash) ;
		
	public Cash getCashDetailsById(Integer id) ;

	public List<Cash> getCashDetails() ;

	public Cash updateCashDetailsWithId(Cash cash);

	public String deleteCashDetails(Integer id) ;

}
