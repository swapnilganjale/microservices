
/**
 * <h1>To manage Custom Exception Service</h1>
 * @author vishal
 */
package com.project.task1service.exception;

public interface CustomExceptionCreatorService {
	/**
	 * To get CustomException bean
	 * 
	 * @param statusCode
	 * @return CustomException
	 */
	CustomException getCustomException(String statusCode);

	/**
	 * To get CustomException bean
	 * 
	 * @param statusCode
	 * @param string
	 * @return CustomException
	 */
	CustomException getCustomException(String statusCode, String string);

}
