package com.hcl.stock.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Stock {
	@Id
	
	private int stockId;
	private String stockName;
	private String description;
	private double stockPrice;
	
	

}
