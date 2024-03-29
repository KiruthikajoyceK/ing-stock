package com.hcl.stock.service;

import java.util.Optional;

import org.junit.After;

import org.junit.Assert;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpMethod;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.hcl.stock.dto.StockDto;
import com.hcl.stock.entity.Stock;
import com.hcl.stock.repository.StockRepository;

@RunWith(MockitoJUnitRunner.class)

public class StockServiceImplTest {

	@InjectMocks

	private StockServiceImpl stockExchangeServiceImpl;

	@Mock

	private StockRepository stockExchangeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImplTest.class);

	StockDto stock = new StockDto();

	Stock returnStock = new Stock();

	RestTemplate restTemplate = new RestTemplate();

	@Before

	public void setProperties() {

		stock.setStockId(1);

		stock.setStockPrice(10.15);

		stock.setStockName("wfc");

		returnStock.setDescription("");

		returnStock.setStockId(1);

		returnStock.setStockPrice(10.15);

		returnStock.setStockName("wfc");

	}

	@Test

	public void stockPrice() {

		String stockResourceUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="
				+ stock.getStockName()
				+ "&apikey=H3Y284LZQ4VPA5E1";
		JsonNode response = restTemplate

				.exchange(stockResourceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<JsonNode>() {

				}).getBody();

		JsonNode subResponse = response.get("Global Quote");

		Double price = subResponse.get("05. price").asDouble();

		LOGGER.info("Stock Response::" + response);

		stock.setStockPrice(price);

	Mockito.when(stockExchangeRepository.findById(Mockito.any())).thenReturn(Optional.of(returnStock));

		LOGGER.info("Stock Price::" + stock.getStockPrice());

		Assert.assertEquals(49.095, stock.getStockPrice(), 0.0);

	}

	@After

	public void destroy() {

		LOGGER.info("Test successfully completed");

	}

}