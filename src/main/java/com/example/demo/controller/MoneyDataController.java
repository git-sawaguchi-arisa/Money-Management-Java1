package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.MoneyInfo;
import com.example.demo.response.MoneyDataResponse;
import com.example.demo.response.MoneyResponse;
import com.example.demo.response.PieDataResponse;
import com.example.demo.service.MoneyDataService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MoneyDataController {

    public final MoneyDataService moneyDataService;

    @GetMapping("/piechart")
    public ResponseEntity<PieDataResponse> getMoneyInfo(String userNum, Integer year, Integer month) {

        System.out.println(year);
        System.out.println(month);

        List<MoneyInfo> getPieDataList = moneyDataService.getByYearMonth(userNum, year, month);
        List<Integer> totalAmountList = new ArrayList<>();
        Integer musicTotal = 0;
        Integer bookTotal = 0;
        Integer foodTotal = 0;
        for (MoneyInfo info : getPieDataList) {
            // MoneyInfo moneyInfo = new MoneyInfo();
            // moneyInfo.setAmount(info.getAmount());
            // Integer amount = moneyInfo.getAmount();
            if (info.getJenre().equals("音楽")) {
                musicTotal += info.getAmount();
            }

            if (info.getJenre().equals("本")) {
                bookTotal += info.getAmount();
            }

            if (info.getJenre().equals("食費")) {
                foodTotal += info.getAmount();
            }
        }

        if (musicTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(musicTotal);
        }

        if (bookTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(bookTotal);
        }

        if (foodTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(foodTotal);
        }

        PieDataResponse pieDataResponse = PieDataResponse.builder().pieDataList(totalAmountList).build();
        return new ResponseEntity<>(pieDataResponse, HttpStatus.OK);
    }

    @GetMapping("/getMoneyData")
    public ResponseEntity<MoneyDataResponse> getMoney(String userNum, Integer year, Integer month) {
        List<MoneyInfo> getMoneyInfoList = moneyDataService.getByYearMonth(userNum, year, month);
        List<MoneyResponse> moneyAllList = new ArrayList<>();
        for (MoneyInfo info : getMoneyInfoList) {
            MoneyResponse moneyResponse = new MoneyResponse();
            moneyResponse.setAmount(info.getAmount());
            moneyResponse.setDate(info.getDate());
            moneyResponse.setDetails(info.getDetails());
            moneyResponse.setJenre(info.getJenre());
            moneyAllList.add(moneyResponse);
        }
        MoneyDataResponse moneyDataResponse = MoneyDataResponse.builder().moneyInfoList(moneyAllList).build();
        return new ResponseEntity<>(moneyDataResponse, HttpStatus.OK);
    }
}
