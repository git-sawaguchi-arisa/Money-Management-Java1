package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.MoneyInfo;

@Repository
public interface PostMoneyRepository extends JpaRepository<MoneyInfo, Integer>{

	//ユーザーIdで入力した情報をgetする
	List<MoneyInfo> findByUserNum(String userNum);
	

}
