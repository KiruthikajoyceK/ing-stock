package com.hcl.stock.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.stock.dto.StockDto;
import com.hcl.stock.entity.Stock;
import com.hcl.stock.service.StockServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration

public class StockControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	StockServiceImpl stockServiceImpl;
	
	@InjectMocks
	StockController stockController;
	
	
	@Test
	public void testGetStockDetails() throws Exception
	{
		StockDto stockDto=new StockDto();
	
		stockDto.setStockId(1);
		stockDto.setStockName("IBM");
		stockDto.setStockPrice(100.0);
		
		Stock stock= new Stock();
		stock.setStockId(1);
		stock.setStockName("Kiruthika");
		Mockito.when(stockServiceImpl.stockDetails(Mockito.anyInt())).thenReturn(stockDto);

		ResponseEntity<StockDto> response = stockController.getStockDetails(1);
		assertEquals(200, response.getStatusCode().value());

	}
	public static String asJsonString(final Object obj) {

		try {

			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}



	}

}
