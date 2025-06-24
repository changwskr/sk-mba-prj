package com.sk.mba.cashcard.business.as;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sk.mba.cashcard.business.dc.CashCardDC;
import com.sk.mba.cashcard.business.dc.dao.model.MemberDTO;
import com.sk.mba.cashcard.business.dto.CashCardIO11007DTO;


@Service
public class CashCardAS {
	
	private CashCardIO11007DTO vIo11007 ;
	private final CashCardDC cashCardDC;

	
	@Autowired
	public CashCardAS(CashCardDC cashCardDC) {
		this.cashCardDC = cashCardDC;
	}
	
    public CashCardIO11007DTO execute(CashCardIO11007DTO io){
    	
    	System.out.println("IO 셋팅");
    	this.vIo11007 = io;
    	
		this.vIo11007 = io;
		MemberDTO member = new MemberDTO();
		member.setName(vIo11007.getName());

    	//==================================================
        // - DC 호출
        //==================================================

		member = this.cashCardDC.execute(member);
		
		this.vIo11007.setName(member.getName());		
		
    	System.out.println("IO.out 정보 셋팅");
    	vIo11007.setOut(vIo11007.toString());

        return vIo11007; //템플릿을 찾지 않고 그대로 API 방식으로 클라이언트로 내려간다.

    }
    
	/**
     * 회원가입
     * */
    public CashCardIO11007DTO join(CashCardIO11007DTO pIn){

    	
    	this.vIo11007 = pIn;
    	MemberDTO member = new MemberDTO();
		member.setName(this.vIo11007.getName());
		
		Long id = this.cashCardDC.join(member);
		
		System.out.println("join-"+ Long.toString(id));
		
		this.vIo11007.setId(Long.toString(id));
        return vIo11007;
    }

    /**
     * 전체 회원 조회
     * */
    public List<CashCardIO11007DTO> findMembers() {
    	
		
    	List<MemberDTO> members = this.cashCardDC.findMembers();
    	List<CashCardIO11007DTO> allList = new ArrayList<CashCardIO11007DTO>();

    	System.out.println("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");

    	int v=0;
    	for(MemberDTO member : members) {
    		CashCardIO11007DTO io = new CashCardIO11007DTO();
    		io.setCompany("dao-sk");
    		io.setId(Long.toString(member.getId()) );
    		io.setIn("dao-test-in-aaaaaaaaa");
    		io.setOut("dao-test-out-aaaaaaaaa");
    		io.setName(member.getName());
    		
    		allList.add(io);
    	}

    	int i=0;
    	for(CashCardIO11007DTO io : allList)
    		System.out.println( "id-" + io.getId() + "-" + io.getName());
    	
    	System.out.println("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
    	
        return allList ;
    }

    public CashCardIO11007DTO findOne(CashCardIO11007DTO in){
        return in;
    }


}
