package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "`income_info`")
public class IncomeInfo {

    /** 自動採番ID */
    @Id
    @Column(name = "income_id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeId;

    /** ジャンル */
    @Column(name = "jenre", columnDefinition = "VARCHAR(225)")
    private String jenre;

    /** 合計金額 */
    @Column(name = "income", columnDefinition = "INT")
    private Integer income;

    /** 詳細 */
    @Column(name = "details", columnDefinition = "VARCHAR(225)")
    private String details;

    /** 日付 */
    @Column(name = "date", columnDefinition = "DATE")
    private Date date;

    @Column(name = "user_num", columnDefinition = "VARCHAR(255)")
    private String userNum;
}
