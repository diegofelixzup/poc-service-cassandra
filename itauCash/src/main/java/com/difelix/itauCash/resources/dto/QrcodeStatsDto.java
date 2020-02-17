package com.difelix.itauCash.resources.dto;

public class QrcodeStatsDto {
	
	private Long value;
	private String url;
	private String alias;
	
	public QrcodeStatsDto() {
		super();
	}

	public QrcodeStatsDto(Long value, String url, String alias) {
		super();
		this.value = value;
		this.url = url;
		this.alias = alias;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "QrcodeStatsDto [value=" + value + ", url=" + url + ", alias=" + alias + "]";
	}

}
