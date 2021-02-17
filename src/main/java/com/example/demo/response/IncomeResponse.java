package com.example.demo.response;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeResponse {
	
	 private Integer incomeId;
	 
	 private String jenre;
	 
	 private Integer income;
	 
	 private String details;

	 private Date date;

}
