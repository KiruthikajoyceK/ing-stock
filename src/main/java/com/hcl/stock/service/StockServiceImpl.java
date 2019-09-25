package com.hcl.stock.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.stock.dto.StockDto;
import com.hcl.stock.entity.Stock;
import com.hcl.stock.repository.StockRepository;

@Service
public class StockServiceImpl implements StockServiceIntf {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	RestTemplate restTemplate;

	 Double stockPrice;
	 
	
	public StockDto stockDetails(int stockId) throws IOException  {

		Optional<Stock> stock = stockRepository.findById(stockId);
		String response=restTemplate.exchange("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stock.get().getStockName()+"&apikey=OZMZJVYG19XSKZ1I",
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				},stockId).getBody();
		
		
		 ObjectMapper objectMapper=new ObjectMapper();
		 JsonNode jsonNode=objectMapper.readTree(response);
		 
		 JsonNode subNode=jsonNode.get("Global Quote");
		 JsonNode errorNode=jsonNode.get("Error Message");
		 StockDto stockDto=new StockDto();
		 if (errorNode==null) {
			 	 stockPrice=subNode.get("05. price").asDouble();
			 	 	 }
		 else
		 {
		
		stockPrice=stock.get().getStockPrice();
		 }
		
		  stockDto.setStockId(stock.get().getStockId());
		  stockDto.setStockPrice(stockPrice);
		  stockDto.setStockName(stock.get().getStockName());
		 
					
		return stockDto;
		
		
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
