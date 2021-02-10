package com.example.demo.repository;

import com.example.demo.domain.IncomeInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface IncomeInfoRepository extends JpaRepository<IncomeInfo, Integer> {

    // 収入のテーブルから情報取得
    @Query(value = "SELECT * from income_info where user_num = ?1 AND DATE_FORMAT(date, '%Y') = ?2 AND DATE_FORMAT(date, '%m') = ?3", nativeQuery = true)
    List<IncomeInfo> getIncome(String userNum, Integer year, Integer month);
}
