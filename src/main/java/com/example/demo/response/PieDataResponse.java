package com.example.demo.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PieDataResponse {

    private List<Integer> pieDataList;
}
