package com.greedy.StudyFamily.msg.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.msg.entity.Msg;

public interface MsgRepository extends JpaRepository<Msg, Long> {

	List<Order> findByReceiver(Login login, Sort msgCode);

	//login 값과 일치 여부 확인 후 수발신함 리스트 불러오기
	List<Msg> findAllByReceiver(LoginDto login);
	List<Msg> findAllBySender(LoginDto login);

	
	


}
