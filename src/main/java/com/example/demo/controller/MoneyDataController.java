package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.IncomeInfo;
import com.example.demo.domain.MoneyInfo;
import com.example.demo.response.IncomeResponse;
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

        var getPieDataList = moneyDataService.getByYearMonth(userNum, year, month);
        var totalAmountList = new ArrayList<Integer>();
        // 食費
        Integer foodTotal = 0;
        // 日用品
        Integer dailyTotal = 0;
        // 衣服
        Integer clothTotal = 0;
        // 交通費
        Integer transportTotal = 0;
        // その他
        Integer anyTotal = 0;
        // 家賃、光熱費
        Integer homeTotal = 0;
        // 趣味
        Integer hobbyTotal = 0;
        // 美容
        Integer beautyTotal = 0;
        // 医療
        Integer medicalTotal = 0;
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
            if (info.getJenre().equals("家賃・光熱費")) {
                homeTotal += info.getAmount();
            }
            if (info.getJenre().equals("趣味")) {
                hobbyTotal += info.getAmount();
            }
            if (info.getJenre().equals("美容")) {
                beautyTotal += info.getAmount();
            }
            if (info.getJenre().equals("医療費")) {
                medicalTotal += info.getAmount();
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

        if (homeTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(homeTotal);
        }

        if (hobbyTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(hobbyTotal);
        }

        if (beautyTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(beautyTotal);
        }

        if (medicalTotal == 0) {
            totalAmountList.add(0);
        } else {
            totalAmountList.add(medicalTotal);
        }

        PieDataResponse pieDataResponse = PieDataResponse.builder().pieDataList(totalAmountList).build();
        return new ResponseEntity<>(pieDataResponse, HttpStatus.OK);
    }

    @GetMapping("/getMoneyData")
    public ResponseEntity<MoneyDataResponse> getMoney(String userNum, Integer year, Integer month) {
        var getMoneyInfoList = moneyDataService.getByYearMonth(userNum, year, month);
        var moneyAllList = new ArrayList<MoneyResponse>();
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
        var getTotalExpense = moneyDataService.getByYearMonth(userNum, year, month);
        Integer totalExpense = 0;
        for (MoneyInfo info : getTotalExpense) {
            totalExpense += info.getAmount();
        }
        System.out.println(totalExpense);

        return totalExpense;
    }

    @GetMapping("/getTotalIncome")
    public Integer getTotalIncome(String userNum, Integer year, Integer month) {
        var getTotalIncome = moneyDataService.getIncomeInfo(userNum, year, month);
        Integer totalIncome = 0;
        for (IncomeInfo info : getTotalIncome) {
            totalIncome += info.getIncome();
        }

        System.out.println(totalIncome);
        return totalIncome;
    }

    @GetMapping("/getIncomeData")
    public ResponseEntity<MoneyDataResponse> getIncomeInfo(String userNum, Integer year, Integer month) {
        var getIncomeData = moneyDataService.getIncomeInfo(userNum, year, month);
        var incomeAllList = new ArrayList<IncomeResponse>();
        for (IncomeInfo info : getIncomeData) {

            IncomeResponse incomeResponse = new IncomeResponse();
            incomeResponse.setIncomeId(info.getIncomeId());
            incomeResponse.setIncome(info.getIncome());
            incomeResponse.setDate(info.getDate());
            incomeResponse.setDetails(info.getDetails());
            incomeResponse.setJenre(info.getJenre());

            incomeAllList.add(incomeResponse);
        }
        if (incomeAllList == null) {
            return null;
        }
        MoneyDataResponse moneyDataResponse = MoneyDataResponse.builder().incomeInfoList(incomeAllList).build();
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
    public Integer deleteIncomeData(Integer incomeId) {
        System.out.println(incomeId);
        moneyDataService.deleteIncomeData(incomeId);
        return incomeId;
    }
}
