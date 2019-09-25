package com.hcl.stock.service;

import java.io.IOException;

import com.hcl.stock.dto.StockDto;
public interface StockServiceIntf {
	
	public StockDto stockDetails(int stockId) throws IOException;

}
