package com.hcl.stock.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.stock.dto.StockDto;
import com.hcl.stock.service.StockServiceImpl;

@RestController
@RequestMapping("/stock")
@CrossOrigin(allowedHeaders = {"*","*/"},origins= {"*","*/"})
public class StockController {
	@Autowired
	StockServiceImpl stockServiceImpl;
	
	@GetMapping("/stockDetails/{stockId}")
	public ResponseEntity<StockDto> getStockDetails(@PathVariable("stockId") int stockId) throws IOException
	{
		StockDto stockDto=stockServiceImpl.stockDetails(stockId);
		return new ResponseEntity<StockDto>(stockDto, HttpStatus.OK);
		
	}
	

}
