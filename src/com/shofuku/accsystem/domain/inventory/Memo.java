package com.shofuku.accsystem.domain.inventory;

import java.io.Serializable;

public class Memo implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -4468008339307496650L;

	public Memo() {
		
	}
	
	public Memo(int id, String memoType, String memo) {
	super();
	this.id = id;
	this.memoType = memoType;
	this.memo = memo;
}
	private int id;
private String memoType;
private String memo;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMemoType() {
	return memoType;
}
public void setMemoType(String memoType) {
	this.memoType = memoType;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
}
