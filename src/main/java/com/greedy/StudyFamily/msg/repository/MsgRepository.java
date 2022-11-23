package com.greedy.StudyFamily.msg.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.msg.entity.Msg;

public interface MsgRepository extends JpaRepository<Msg, Long> {

	//쪽지 수신함 조회
	@Query("SELECT m " +
			"FROM Msg m " +
			"WHERE m.receiver = :receiver "+
			"AND m.deleteStatus = 'N'")
	Page<Msg> findReceivedMsgs(Pageable pageable, Login receiver);

	
	


}
