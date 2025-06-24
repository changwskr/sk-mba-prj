package com.sk.mba.cashcard.business.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sk.mba.cashcard.business.as.CashCardAS;
import com.sk.mba.cashcard.business.dto.CashCardIO11007DTO;
import com.sk.mba.cashcard.transfer.CashCardIO11007FormCDTO;

/**
 * API 방식으로 변경한다.
 * 여기서는 문자열을 리턴하는 것이 아니라 객체로 리턴할 경우 어떻게 되는 가
 * 
 */
@Controller
public class CashCardCTL {
	
    private final CashCardAS as;

    @Autowired
    public CashCardCTL(CashCardAS pvAS11007) {
        this.as = pvAS11007;
        System.out.println("AS11007 = " + pvAS11007.getClass());
    }

	/**
	 * http://127.0.0.1:8080/roianhome
	 * txhome.html 호출
	 * @param model
	 * @return
	 */
    @GetMapping("roianhome")
    public String roianHome(Model model){ 
    	
    	// txhome.html 페이지는 다음 2가지 스타일의 호출을 한다.
    	// http://127.0.0.1:8080/txstyle/11006.html
    	// http://127.0.0.1:8080/txstyle/11005.html
    	
        return "roianhome"; 
    }


    /**
     * http://127.0.0.1:8080/post/11006
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/11007")
    public Object execute(CashCardIO11007FormCDTO form, Model model){
    	
    	System.out.println("IO 셋팅");
    	
    	// 폼데이타 이전
    	CashCardIO11007DTO io = new CashCardIO11007DTO();
    	
    	io.setCompany(form.getCompany());
    	io.setId(form.getId());
    	io.setIn(form.getIn());
    	io.setCompany(form.getCompany());
    	io.setOut(form.getOut());
    	io.setId(form.getId());
    	io.setName(form.getName());
    	io.setRouting_page("R11007");
    	io.setTxcode(form.getTxcode());
    	
	
    	// IO전문 셋팅
    	System.out.println("AS-" + io.toString());
    	
        //==================================================
        // AS 호출
        //==================================================
    	System.out.println("-[AS11007 호출]");
        io = as.execute(io);

    	System.out.println("클라이언트 정보 셋팅");
    	io.setOut(io.toString());

    	
    	// 모델이라는 객체에 속성변수에 값을 저장한다.
    	model.addAttribute("id", io.getId());
    	model.addAttribute("in", io.getIn());
        model.addAttribute("out", io.getOut());
        model.addAttribute("company", io.getCompany());
        model.addAttribute("name", io.getName());
        model.addAttribute("txcode", io.getTxcode());
        model.addAttribute("routepage", io.getRouting_page());
        

    	System.out.println("클라이언트 리턴");

        return io.getRouting_page(); //템플릿을 찾지 않고 그대로 API 방식으로 클라이언트로 내려간다.
        

    }

    @GetMapping("/11007/new")
    public String createForm() {
        return "11007/11007-createMemberForm";
    }

    @PostMapping("/11007/new")
    public String create(CashCardIO11007FormCDTO form) {

    	// IO전문 셋팅
    	System.out.println("AS-" + form.toString());

    	// 폼데이타 이전
    	CashCardIO11007DTO io = new CashCardIO11007DTO();
    	
    	io.setCompany(form.getCompany());
    	io.setId(form.getId());
    	io.setIn(form.getIn());
    	io.setCompany(form.getCompany());
    	io.setOut(form.getOut());
    	io.setId(form.getId());
    	io.setName(form.getName());
    	io.setRouting_page("R11006");
    	io.setTxcode(form.getTxcode());


        as.join(io);
        
    	// IO전문 셋팅
    	System.out.println("create end===========");


        return "redirect:/roianhome";
    }

    @GetMapping("/11007/all")
    public String list(Model model){
    	List<CashCardIO11007DTO> members = as.findMembers();    	

    	model.addAttribute("members", members);

        return "11007/11007-memberList";
    }

}