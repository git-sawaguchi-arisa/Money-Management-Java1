package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.IncomeInfo;
import com.example.demo.domain.MoneyInfo;
import com.example.demo.repository.IncomeInfoRepository;
import com.example.demo.repository.MoneyDataRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MoneyDataService {

    private final MoneyDataRepository moneyDataRepository;
    private final IncomeInfoRepository incomeInfoRepository;

    public List<MoneyInfo> getByYearMonth(String userNum, Integer year, Integer month) {
        return moneyDataRepository.getByYearMonth(userNum, year, month);
    }

    public List<IncomeInfo> getIncomeInfo(String userNum, Integer year, Integer month) {
        return incomeInfoRepository.getIncome(userNum, year, month);
    }

    public void updateMoneyInfo(MoneyInfo moneyInfo) {
        moneyDataRepository.save(moneyInfo);
    }

}
