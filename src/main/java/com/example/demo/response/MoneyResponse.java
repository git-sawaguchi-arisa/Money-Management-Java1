package com.example.demo.response;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyResponse {

    private Integer amount;

    private String jenre;

    private Date date;

    private String details;
}
