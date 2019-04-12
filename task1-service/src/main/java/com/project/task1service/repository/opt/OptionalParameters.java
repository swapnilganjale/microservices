package com.project.task1service.repository.opt;

import com.project.task1service.page.dto.PageDTO;
import com.project.task1service.page.dto.PagingAndSortingRequest;


public interface OptionalParameters<T> {

	public PageDTO<T> findWithOptionalParameters(PagingAndSortingRequest request, Class<T> type);
}
