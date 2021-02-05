package com.example.demo.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "`money_info`")
public class MoneyInfo {

    /** 自動採番ID */
    @Id
    @Column(name = "money_id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moneyId;

    /** ジャンル */
    @Column(name = "jenre", columnDefinition = "VARCHAR(225)")
    private String jenre;

    /** 合計金額 */
    @Column(name = "amount", columnDefinition = "INT")
    private Integer amount;

    /** 詳細 */
    @Column(name = "details", columnDefinition = "VARCHAR(225)")
    private String details;

    /** 日付 */
    @Column(name = "date", columnDefinition = "DATE")
    private Date date;

    @Column(name = "user_num", columnDefinition = "VARCHAR(255)")
    private String userNum;

}
