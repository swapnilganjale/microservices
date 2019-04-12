package com.project.userservice.repository.opt;

import com.project.userservice.page.dto.PageDTO;
import com.project.userservice.page.dto.PagingAndSortingRequest;


public interface OptionalParameters<T> {

	public PageDTO<T> findWithOptionalParameters(PagingAndSortingRequest request, Class<T> type);
}
