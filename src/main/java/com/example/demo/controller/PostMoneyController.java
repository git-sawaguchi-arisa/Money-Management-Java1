package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.MoneyInfo;
import com.example.demo.service.PostMoneyService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostMoneyController {

	private final PostMoneyService postMoneyService;

	@PostMapping("/setMoney")
	@ResponseStatus(HttpStatus.CREATED)
	public MoneyInfo insertMoney(@RequestBody MoneyInfo moneyInfo) {
		postMoneyService.insertMoneyInfo(moneyInfo);

		return moneyInfo;
	}

	@GetMapping("/getMoney")
	public List<MoneyInfo> getMoneyData(String userNum) {
		return postMoneyService.getMoneyData(userNum);
	}

	@GetMapping("/deleteMoney")
	public Integer deleteMoneyData(Integer moneyId) {
		postMoneyService.deleteMoneyData(moneyId);

		System.out.println(moneyId);
		return moneyId;
	}

}
