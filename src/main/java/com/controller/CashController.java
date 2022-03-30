package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CashApiRequestException;
import com.model.Cash;
import com.service.CashService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/cashService")
@Api(value = "CashRestController", tags = "REST Apis related to Cash!!!!")
public class CashController {

	@Autowired
	private CashService cashService;

	@ApiOperation(value = "Add cash details", response = Cash.class)
	@PostMapping("/postCash")
	public ResponseEntity<Cash> postCashModel(@Valid @RequestBody Cash cash) {
		Cash resp = null;
		try {
			resp = cashService.postCashDetails(cash);
		} catch (CashApiRequestException e) {
			log.info(e.getLocalizedMessage());
			throw new CashApiRequestException("Cash details not getting saved");
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@ApiOperation(value = "Getting cash details by id", response = Cash.class)
	@GetMapping("/getCashById/{id}")
	public ResponseEntity<Cash> getCashDetailsById(@PathVariable Integer id) {
		Cash resp = null;
		try {
			if (id < 0 || id < Integer.MIN_VALUE)
				throw new CashApiRequestException("Cash id does not exists");
			resp = cashService.getCashDetailsById(id);
			if (resp == null)
				throw new CashApiRequestException("Cash id does not exists");
		} catch (CashApiRequestException e) {
			throw new CashApiRequestException("Cash id does not exists", e.getCause());
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@ApiOperation(value = "Getting cash details", response = Cash.class)
	@GetMapping("/getCash")
	public ResponseEntity<List<Cash>> getCashDetails() {
		List<Cash> resp = null;
		try {
			log.info("Inside get Cash details method");
			resp = cashService.getCashDetails();
			if (resp == null)
				throw new CashApiRequestException("Cash details does not exists");
		} catch (CashApiRequestException e) {
			log.info(e.getLocalizedMessage());
			throw new CashApiRequestException("Cash details not getting saved");
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@ApiOperation(value = "Updating cash details", response = Cash.class)
	@PostMapping("/updateCash")
	public ResponseEntity<Cash> updateCashDetailsById(@RequestBody Cash cash) throws CashApiRequestException {
		Cash resp = null;	
		try {
			log.info("Inside update Cash details method");
			resp = cashService.updateCashDetailsWithId(cash);
		} catch (CashApiRequestException e) {
			log.info(e.getLocalizedMessage());
			throw new CashApiRequestException("Cash details not getting saved");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete cash details", response = Cash.class, notes = "Deleting the cash details")
	@DeleteMapping("/deleteCash/{id}")
	public String deleteCashDetails(@PathVariable Integer id) {
		log.info("Inside deleting Cash details method");
		return cashService.deleteCashDetails(id);
	}
}
