package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MoneyInfo;
import com.example.demo.repository.MoneyDataRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MoneyDataService {

    private final MoneyDataRepository moneyDataRepository;

    public List<MoneyInfo> getByYearMonth(String userNum, Integer year, Integer month) {
        return moneyDataRepository.getByYearMonth(userNum, year, month);
    }
}
