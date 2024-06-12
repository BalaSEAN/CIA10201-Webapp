package com.act.model;

public class ActVO implements java.io.Serializable{
	private Integer actPicNo;
	private Integer actNo;
	private String actPicName;
	private byte[] actPic;
	
	public Integer getActPicNo() {
		return actPicNo;
	}
	public void setActPicNo(Integer actPicNo) {
		this.actPicNo = actPicNo;
	}
	public Integer getActNo() {
		return actNo;
	}
	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}
	public String getActPicName() {
		return actPicName;
	}
	public void setActPicName(String actPicName) {
		this.actPicName = actPicName;
	}
	public byte[] getActPic() {
		return actPic;
	}
	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}
	
	
	
	
}
