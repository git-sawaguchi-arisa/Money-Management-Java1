package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.IncomeInfo;
import com.example.demo.domain.MoneyInfo;
import com.example.demo.repository.IncomeInfoRepository;
import com.example.demo.repository.PostMoneyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostMoneyService {
	
	private final PostMoneyRepository postMoneyRepository;
	private final IncomeInfoRepository incomeInfoRepository;
	
	 public void insertMoneyInfo (MoneyInfo moneyInfo) {
	    postMoneyRepository.save(moneyInfo);
	}

	 public List<MoneyInfo> getMoneyData (String userNum) {
		 return postMoneyRepository.findByUserNum(userNum);
	 }
	 
	 public void deleteMoneyData (Integer moneyId) {
		 postMoneyRepository.findById(moneyId).ifPresent(moneyInfo -> postMoneyRepository.delete(moneyInfo));
	 }
	 
	 public void insertIncome (IncomeInfo incomeInfo){
		 incomeInfoRepository.save(incomeInfo);
	 }
	 
	 public List<IncomeInfo> getIncomeData (String userNum) {
		 return incomeInfoRepository.findByUserNum(userNum);
	 }
	 
	 public void deleteIncomeData(Integer incomeId) {
		 incomeInfoRepository.findById(incomeId).ifPresent(incomeInfo -> incomeInfoRepository.delete(incomeInfo));
	 }
}

