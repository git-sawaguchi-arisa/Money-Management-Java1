package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.IncomeInfo;
import com.example.demo.domain.MoneyInfo;
import com.example.demo.response.MoneyDataResponse;
import com.example.demo.response.MoneyResponse;
import com.example.demo.response.PieDataResponse;
import com.example.demo.service.MoneyDataService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MoneyDataController {

    public final MoneyDataService moneyDataService;

    @GetMapping("/piechart")
    public ResponseEntity<PieDataResponse> getMoneyInfo(String userNum, Integer year, Integer month) {

        List<MoneyInfo> getPieDataList = moneyDataService.getByYearMonth(userNum, year, month);
        List<Integer> totalAmountList = new ArrayList<>();
        Integer foodTotal = 0;
        Integer dailyTotal = 0;
        Integer clothTotal = 0;
        Integer transportTotal = 0;
        Integer anyTotal = 0;
        for (MoneyInfo info : getPieDataList) {
            if (info.getJenre().equals("食費")) {
                foodTotal += info.getAmount();
            }

            if (info.getJenre().equals("日用品")) {
                dailyTotal += info.getAmount();
            }

            if (info.getJenre().equals("衣服")) {
                clothTotal += info.getAmount();
            }
            if (info.getJenre().equals("交通費")) {
                transportTotal += info.getAmount();
            }
            if (info.getJenre().equals("その他")) {
                anyTotal += info.getAmount();
            }
        }

        if (foodTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(foodTotal);
        }

        if (dailyTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(dailyTotal);
        }

        if (clothTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(clothTotal);
        }

        if (transportTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(transportTotal);
        }

        if (anyTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(anyTotal);
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
            moneyResponse.setMoneyId(info.getMoneyId());
            moneyResponse.setAmount(info.getAmount());
            moneyResponse.setDate(info.getDate());
            moneyResponse.setDetails(info.getDetails());
            moneyResponse.setJenre(info.getJenre());
            moneyAllList.add(moneyResponse);
        }
        if (moneyAllList == null) {
            return null;
        }
        MoneyDataResponse moneyDataResponse = MoneyDataResponse.builder().moneyInfoList(moneyAllList).build();
        return new ResponseEntity<>(moneyDataResponse, HttpStatus.OK);
    }

    @GetMapping("/getTotalExpense")
    public Integer getTotalExpenses(String userNum, Integer year, Integer month) {
        List<MoneyInfo> getTotalExpense = moneyDataService.getByYearMonth(userNum, year, month);
        Integer totalExpense = 0;
        for (MoneyInfo info : getTotalExpense) {
            totalExpense += info.getAmount();
        }
        System.out.println(totalExpense);

        return totalExpense;
    }

    @GetMapping("/getTotalIncome")
    public Integer getTotalIncome(String userNum, Integer year, Integer month) {
        List<IncomeInfo> getTotalIncome = moneyDataService.getIncomeInfo(userNum, year, month);
        Integer totalIncome = 0;
        for (IncomeInfo info : getTotalIncome) {
            totalIncome += info.getIncome();
        }

        System.out.println(totalIncome);
        return totalIncome;
    }

    @GetMapping("/getIncomeData")
    public ResponseEntity<MoneyDataResponse> getIncomeInfo(String userNum, Integer year, Integer month) {
        List<IncomeInfo> getIncomeData = moneyDataService.getIncomeInfo(userNum, year, month);
        List<MoneyResponse> incomeAllList = new ArrayList<>();
        for (IncomeInfo info : getIncomeData) {
            MoneyResponse moneyResponse = new MoneyResponse();
            moneyResponse.setMoneyId(info.getIncome());
            moneyResponse.setAmount(info.getIncome());
            moneyResponse.setDate(info.getDate());
            moneyResponse.setDetails(info.getDetails());
            moneyResponse.setJenre(info.getJenre());
            incomeAllList.add(moneyResponse);
        }
        if (incomeAllList == null) {
            return null;
        }
        MoneyDataResponse moneyDataResponse = MoneyDataResponse.builder().moneyInfoList(incomeAllList).build();
        return new ResponseEntity<>(moneyDataResponse, HttpStatus.OK);
    }

    @PostMapping("/updateMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public MoneyInfo updateMoney(@RequestBody MoneyInfo moneyInfo) {
        moneyDataService.updateMoneyInfo(moneyInfo);
        return moneyInfo;
    }

    @PostMapping("/updateIncome")
    @ResponseStatus(HttpStatus.CREATED)
    public IncomeInfo updateIncome(@RequestBody IncomeInfo incomeInfo) {
        moneyDataService.updateIncome(incomeInfo);
        return incomeInfo;
    }

    @GetMapping("/deleteExpenses")
    public Integer deleteMoneyData(Integer moneyId) {
        moneyDataService.deleteExpenses(moneyId);
        return moneyId;
    }

    @GetMapping("/deleteIncomeData")
    public Integer deleteIncomeData(Integer moneyId) {
        System.out.println(moneyId);
        moneyDataService.deleteIncomeData(moneyId);
        return moneyId;
    }
}
