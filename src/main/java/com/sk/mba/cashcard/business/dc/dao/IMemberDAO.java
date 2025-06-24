package com.sk.mba.cashcard.business.dc.dao;

import java.util.List;
import java.util.Optional;

import com.sk.mba.cashcard.business.dc.dao.model.MemberDTO;


public interface IMemberDAO {
	
	MemberDTO save(MemberDTO memberDto);
	Optional<MemberDTO> findById(Long id);
	Optional<MemberDTO> findByName(String name);
	List<MemberDTO> findAll();

}
