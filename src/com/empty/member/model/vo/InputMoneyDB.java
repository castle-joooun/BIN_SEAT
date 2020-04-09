package com.empty.member.model.vo;

import java.util.Date;

public class InputMoneyDB {
	private String storeId;
	private int inputNum;
	private String userId;
	private Date ipDate;
	private int ipMoney;
	private int afterIm;
	
	public InputMoneyDB() {
		// TODO Auto-generated constructor stub
	}

	public InputMoneyDB(String storeId, int inputNum, String userId, Date ipDate, int ipMoney, int afterIm) {
		super();
		this.storeId = storeId;
		this.inputNum = inputNum;
		this.userId = userId;
		this.ipDate = ipDate;
		this.ipMoney = ipMoney;
		this.afterIm = afterIm;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public int getInputNum() {
		return inputNum;
	}

	public void setInputNum(int inputNum) {
		this.inputNum = inputNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getIpDate() {
		return ipDate;
	}

	public void setIpDate(Date ipDate) {
		this.ipDate = ipDate;
	}

	public int getIpMoney() {
		return ipMoney;
	}

	public void setIpMoney(int ipMoney) {
		this.ipMoney = ipMoney;
	}

	public int getAfterIm() {
		return afterIm;
	}

	public void setAfterIm(int afterIm) {
		this.afterIm = afterIm;
	}

	@Override
	public String toString() {
		return "InputMoneyDB [storeId=" + storeId + ", inputNum=" + inputNum + ", userId=" + userId + ", ipDate="
				+ ipDate + ", ipMoney=" + ipMoney + ", afterIm=" + afterIm + "]";
	}
	
}
