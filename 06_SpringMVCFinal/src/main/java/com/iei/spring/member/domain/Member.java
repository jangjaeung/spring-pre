package com.iei.spring.member.domain;

import java.sql.Date;

public class Member {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private String memberAddr;
	private Date enrollDate;
	private Date updateDate;
	private String mStatus;
	
	public Member() {}

	
	
	public Member(String memberId, String memberPwd, String memberName, String memberEmail, String memberPhone,
			String memberAddr, Date enrollDate, Date updateDate, String mStatus) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
		this.enrollDate = enrollDate;
		this.updateDate = updateDate;
		this.mStatus = mStatus;
	}



	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	@Override
	public String toString() {
		return "회원정보 [아이디=" + memberId + ", 비밀번호=" + memberPwd + ", 이름=" + memberName
				+ ", 이메일=" + memberEmail + ", 전화번호=" + memberPhone + ", 주소=" + memberAddr
				+ ", 등록일=" + enrollDate + ", 수정일=" + updateDate + ", 회원상태=" + mStatus + "]";
	}
	
	
}
