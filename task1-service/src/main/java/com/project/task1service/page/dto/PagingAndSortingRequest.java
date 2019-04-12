package com.project.task1service.page.dto;

import java.io.Serializable;
import java.util.HashMap;

public class PagingAndSortingRequest implements Serializable {
	private static final long serialVersionUID = 9093686014222014790L;

	private Integer startIndex;
	private Integer endIndex;
	private Integer totalElements;

	
	private Integer page;

	private Integer limit;

	private String sortBy;

	private String sortType;// "asc" or "desc"

	private HashMap<String, Object> fields = new HashMap<String, Object>();

	public PagingAndSortingRequest() {
	}

	public PagingAndSortingRequest(Integer page, Integer limit, String sortBy, String sortType,
			HashMap<String, Object> fields,Integer startIndex,Integer endIndex,Integer totalElements) {
		super();
		this.page = page;
		this.limit = limit;
		this.sortBy = sortBy;
		this.sortType = sortType;
		this.fields = fields;
		this.startIndex=startIndex;
		this.endIndex=endIndex;
		this.totalElements=totalElements;
	}

	public HashMap<String, Object> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, Object> fields) {
		this.fields = fields;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortType() {
		return sortType;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

 

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	@Override
	public String toString() {
		return "PagingAndSortingRequest [startIndex=" + startIndex + ", endIndex=" + endIndex + ", totalElements="
				+ totalElements + ", page=" + page + ", limit=" + limit + ", sortBy=" + sortBy + ", sortType="
				+ sortType + ", fields=" + fields + "]";
	}

	
}
