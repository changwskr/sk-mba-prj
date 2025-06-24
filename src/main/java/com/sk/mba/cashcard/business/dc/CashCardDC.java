package com.sk.mba.cashcard.business.dc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.mba.cashcard.business.dc.dao.MemberDAOImpl;
import com.sk.mba.cashcard.business.dc.dao.model.MemberDTO;


@Service
public class CashCardDC {

	private MemberDTO member;
	private final MemberDAOImpl memberRepository;

	
	@Autowired
	public CashCardDC(MemberDAOImpl memberRepository) {
		this.memberRepository = new MemberDAOImpl();
	}

	public CashCardDC() {
		this.memberRepository = new MemberDAOImpl();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO execute(MemberDTO member) {

		// DTO에서 NAME 정리
		this.member = member;
		
		member.setName("11006-dao-jws");
				
		return member;
	}

	//=============================================================================================
	
	//============================================================================================
	
	
	/**
     * 회원가입
     * */
    public Long join(MemberDTO member){

    	// 같은 이름이 있는 중복 회원 X
    	// 회의의 이름으로 메모리에서 찾는다.
        Optional<MemberDTO> result = memberRepository.findByName(member.getName());
        
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        

        //위의 방식에서 아래방식으로 코딩
        //로직이 들어가면 메소드로 뺀다
        validateDuplicateMember(member); //중복 회원 검증
        
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(MemberDTO member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }
    /**
     * 전체 회원 조회
     * */
    public List<MemberDTO> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<MemberDTO> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
