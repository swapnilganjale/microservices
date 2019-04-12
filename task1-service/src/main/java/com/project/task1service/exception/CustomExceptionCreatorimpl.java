
/**
 * <h1>To get bean of CustomException</h1>
 * 
 * @author Vishal
 * @see CustomExceptionCreatorService
 */
package com.project.task1service.exception;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CustomExceptionCreatorimpl implements CustomExceptionCreatorService {

	@Autowired
	private Environment env;

	@Override
	public CustomException getCustomException(String statusCode) {

		return new CustomException(env.getProperty(statusCode + "_msg"), env.getProperty(statusCode + "_desc"),
				statusCode);
	}

	@Override
	public CustomException getCustomException(String statusCode, String string) {
		return new CustomException(MessageFormat.format(env.getProperty(statusCode + "_msg"), string),
				env.getProperty(statusCode + "_desc"), statusCode);
	}

}
