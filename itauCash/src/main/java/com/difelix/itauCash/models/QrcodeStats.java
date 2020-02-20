package com.difelix.itauCash.models;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class QrcodeStats {
	
	@PrimaryKey
	private String alias;
	
	private String url;
	private Long value;

	public QrcodeStats() {
		super();
	}

	public QrcodeStats(String alias, String url, Long value) {
		super();
		this.alias = alias;
		this.url = url;
		this.value = value;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "QrcodeStats [alias=" + alias + ", url=" + url + ", value=" + value + "]";
	}
}
