package com.example.demo.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.IncomeInfo;
import com.example.demo.domain.MoneyInfo;
import com.example.demo.service.PostMoneyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostMoneyController {

	private final PostMoneyService postMoneyService;

	@PostMapping("/postExpense")
	@ResponseStatus(HttpStatus.CREATED)
	public MoneyInfo insertMoney(@RequestBody MoneyInfo moneyInfo) {
		
		postMoneyService.insertMoneyInfo(moneyInfo);

		return moneyInfo;
	}

	@PostMapping("/postIncome")
	@ResponseStatus(HttpStatus.CREATED)
	public IncomeInfo insertIncome(@RequestBody IncomeInfo incomeInfo) {

		postMoneyService.insertIncome(incomeInfo);

		return incomeInfo;
	}
	
	@GetMapping("/test")
	public String test() {
		return "テスト";
	}

	@GetMapping("/getMoney")
	public List<MoneyInfo> getMoneyData(String userNum) {
		return postMoneyService.getMoneyData(userNum);
	}

	@GetMapping("/getIncome")
	public List<IncomeInfo> getIncomeData(String userNum) {
		return postMoneyService.getIncomeData(userNum);
	}

	@GetMapping("/deleteMoney")
	public Integer deleteMoneyData(Integer moneyId) {
		postMoneyService.deleteMoneyData(moneyId);
		return moneyId;
	}

	@GetMapping("/deleteIncome")
	public Integer deleteIncomeData(Integer incomeId) {
		postMoneyService.deleteIncomeData(incomeId);

		return incomeId;
	}

	@PutMapping("/putExpense")
	public MoneyInfo updateExpenst(@RequestBody MoneyInfo moneyInfo) {
		
	
		System.out.println(moneyInfo);

		postMoneyService.insertMoneyInfo(moneyInfo);

		return moneyInfo;
	}
	
	@PutMapping("/putIncome")
	public IncomeInfo updateIncome (@RequestBody IncomeInfo incomeInfo) {

		System.out.println(incomeInfo);
		
		postMoneyService.insertIncome(incomeInfo);
		
		return incomeInfo;
	}
	
}
