package com.example.demo.response;

import java.util.List;

import com.example.demo.domain.MoneyInfo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MoneyDataResponse {

    private List<MoneyResponse> moneyInfoList;
}
