package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.MoneyInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyDataRepository extends JpaRepository<MoneyInfo, Integer> {

    // 支出のテーブルから情報を取る
    @Query(value = "SELECT * from money_info where user_num = ?1 AND DATE_FORMAT(date, '%Y') = ?2 AND DATE_FORMAT(date, '%m') = ?3", nativeQuery = true)
    List<MoneyInfo> getByYearMonth(String userNum, Integer year, Integer month);

    // @Query(value = "UPDATE money_info SET jenre = ?1 AND amount = ?2 AND date =
    // ?3 AND user")
}
