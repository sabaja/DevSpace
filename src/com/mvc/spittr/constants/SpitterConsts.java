package com.mvc.spittr.constants;

public enum SpitterConsts {
	NUM_OF_SPITTLES_PER_PAGE(20), 
	DOWNLOAD_PATH("/home/sabaja/Scrivania/spring-hibernate-workspace/Spring-In-Action_WEBApp/Downloads"),
	TEMPDIR(System.getProperty("java.io.tmpdir")),
	MAX_FILE_SIZE(2_097_152L), MAX_REQUEST_SIZE(4_194_304L), FILE_SIZE_THRESHOLD(0),
	E (Math.PI),
	PI (Math.PI);
	
	
	private int num;
	private String str;
	private Long lnum;
	private Double dble;
	
	private SpitterConsts(int num){
		this.num = num;
	}
	
	private SpitterConsts(String str){
		this.str = str;
	}
	
	private SpitterConsts(Long lnum){
		this.lnum = lnum;
	}
	
	private SpitterConsts(Double dble){
		this.dble = dble;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStr() {
		return str;
	}

	public Long getLnum() {
		return lnum;
	}

	public Double getDble() {
		return dble;
	}

	public void setDble(Double dble) {
		this.dble = dble;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
