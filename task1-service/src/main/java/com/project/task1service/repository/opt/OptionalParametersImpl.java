package com.project.task1service.repository.opt;

import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.task1service.page.dto.PageDTO;
import com.project.task1service.page.dto.PagingAndSortingRequest;

@Component
@Transactional
public class OptionalParametersImpl<T> implements OptionalParameters<T> {

	@Autowired
	private WebApplicationContext appContext;

	@Autowired
	EntityManager entityManager;

	private static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			if (l > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			} else {
				throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
			}
		}
		return (int) l;
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public PageDTO<T> findWithOptionalParameters(PagingAndSortingRequest request, Class<T> type) {
		JpaRepository<T, Long> repository = getRepository(type);
		if (request == null) {
			request = new PagingAndSortingRequest();
		}
		if (request.getPage() != null) {
			request.setPage(request.getPage() - 1);
		}
		long total = repository.count();
		long page = 0;
		long limit = 1;
		if (request.getPage() == null && request.getLimit() == null) {
			if (total > 0) {
				limit = total;
			}
		}
		if (request.getPage() != null && request.getLimit() != null) {
			if (request.getPage() >= 0) {
				page = request.getPage();
			}
			if (request.getLimit() >= 1) {
				limit = request.getLimit();
			}
		}
		if (request.getPage() == null && request.getLimit() != null) {
			if (request.getLimit() >= 1) {
				limit = request.getLimit();
			}
		}
		if (request.getPage() != null && request.getLimit() == null) {
			limit = total;
		}
		Sort.Direction direction = Sort.Direction.ASC;
		try {
			direction = Sort.Direction.fromString(request.getSortType());
		} catch (Exception ex) {
		}
		PageRequest pageRequest = null;
		if (request.getSortBy() == null || request.getSortBy().isEmpty()) {
			pageRequest = new PageRequest(safeLongToInt(page), safeLongToInt(limit));
		} else {
			pageRequest = new PageRequest(safeLongToInt(page), safeLongToInt(limit),
					new Sort(direction, request.getSortBy()));
		}

		if (request.getFields().isEmpty()) {
			System.out.println("************ Normal way");
			Page<T> pageobj = repository.findAll(pageRequest);
			System.err.println(pageobj);

			return new PageDTO<>(pageobj.getContent(), pageobj.getNumber(), pageobj.getNumberOfElements(),
					pageobj.getSize(), pageobj.getTotalElements(), pageobj.getTotalPages());

		} else {
			ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING);
			Example<T> example = Example.of(convertValue(request.getFields(), type), matcher);

			// filtering not working in spring boot 2.0.2 remove "example" from
			// findAll()
			System.out.println("*************");

			System.out.println(repository.findAll(pageRequest).getContent().size());
			System.out.println(repository.findAll(example, pageRequest).getContent().size());

			System.out.println("*************");

			Page<T> pageobj = repository.findAll(example, pageRequest);
			return (PageDTO<T>) pageobj;

		}

	}

	@SuppressWarnings("unchecked")
	private JpaRepository<T, Long> getRepository(Class<T> type) {
		Repositories repositories = new Repositories(appContext);
		return (JpaRepository<T, Long>) repositories.getRepositoryFor(type).get();

	}

	private T convertValue(Map<String, Object> map, Class<T> type) {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(map, type);
	}
}