package com.hcl.stock.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockDto {
	
	private int stockId;
	private double stockPrice;
	private String stockName;
	

}
