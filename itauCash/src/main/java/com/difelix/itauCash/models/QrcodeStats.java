package com.difelix.itauCash.models;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class QrcodeStats {
	
	@PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED,
			ordering = Ordering.DESCENDING, ordinal = 0)
	private UUID id;
	
	@PrimaryKeyColumn(name = "alias", type = PrimaryKeyType.PARTITIONED, 
			ordinal = 1)
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
		return "QrcodeStats [id=" + id + ", alias=" + alias + ", url=" + url + ", value=" + value + "]";
	}
	
	

}
