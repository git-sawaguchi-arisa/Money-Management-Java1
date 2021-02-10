package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MoneyInfo;
import com.example.demo.repository.PostMoneyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostMoneyService {
	
	private final PostMoneyRepository postMoneyRepository;
	
	
	 public void insertMoneyInfo (MoneyInfo moneyInfo) {
	    postMoneyRepository.save(moneyInfo);
	}

	 public List<MoneyInfo> getMoneyData (String userNum) {
		 return postMoneyRepository.findByUserNum(userNum);
	 }
	 
	 public void deleteMoneyData (Integer moneyId) {
		 postMoneyRepository.findById(moneyId).ifPresent(moneyInfo -> postMoneyRepository.delete(moneyInfo));
	 }
}

