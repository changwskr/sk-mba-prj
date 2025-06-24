package com.sk.mba.cashcard.business.dto;

import java.util.Objects;

public class CashCardIO11007DTO {
	
	private String txcode;
	private String routing_page;
	private String in;	
	private String out;
	private String id;
	private String name;
	private String company;


	public CashCardIO11007DTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CashCardIO11007DTO(String in, String out) {
		this.in = in;
		this.out = out;
	}


	public CashCardIO11007DTO(String txcode, String routing_page, String in, String out, String id) {
		super();
		this.txcode = txcode;
		this.routing_page = routing_page;
		this.in = in;
		this.out = out;
		this.id = id;
	}


	public String getRouting_page() {
		return routing_page;
	}


	public void setRouting_page(String routing_page) {
		this.routing_page = routing_page;
	}


	public String getTxcode() {
		return txcode;
	}


	public void setTxcode(String txcode) {
		this.txcode = txcode;
	}


	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(company, id, in, name, out, routing_page, txcode);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashCardIO11007DTO other = (CashCardIO11007DTO) obj;
		return Objects.equals(company, other.company) && Objects.equals(id, other.id) && Objects.equals(in, other.in)
				&& Objects.equals(name, other.name) && Objects.equals(out, other.out)
				&& Objects.equals(routing_page, other.routing_page) && Objects.equals(txcode, other.txcode);
	}


	@Override
	public String toString() {
		return "IO11007DTO [txcode=" + txcode + ", routing_page=" + routing_page + ", in=" + in + ", out=" + out + ", id="
				+ id + ", name=" + name + ", company=" + company + "]";
	}
	
	
	

}
