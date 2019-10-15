package com.dgut.spring.jdbc.bean;

public class Member {
	private Integer memberId;
	private String name;
	private Integer balance;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(Integer memberId, String name, Integer balance) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.balance = balance;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", balance=" + balance + "]";
	}

}
