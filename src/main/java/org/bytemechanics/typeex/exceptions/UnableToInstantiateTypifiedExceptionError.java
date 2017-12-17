/*
 * Copyright 2017 Byte Mechanics.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bytemechanics.typeex.exceptions;

import java.text.MessageFormat;
import org.bytemechanics.typeex.ExceptionType;

/**
 * Exception to indicate that automatic exception type creation can not be performed, due permissions or wrong class construction
 * @author afarre
 * @since 1.0.3
 * @version 1.0.0
 */
public class UnableToInstantiateTypifiedExceptionError extends Error{
	
	/**
	 * Message format to use
	 */
	protected static final String MESSAGE="Unable to construct typified exception {0} with class {1} with arguments {2} caused: {3}";


	/**
	 * Unable to instantiate typified exception constructor
	 * @param _exceptionType exception type unable to instance
	 * @param _args arguments used to instantiate exception
	 * @since 1.0.0
	 */
	public UnableToInstantiateTypifiedExceptionError(final ExceptionType _exceptionType,final Object... _args) {
		super(MessageFormat.format(MESSAGE,_exceptionType,_exceptionType.getClass(),_args));
	}
	/**
	 * Unable to instantiate typified exception constructor
	 * @param _cause cause of the error
	 * @param _exceptionType exception type unable to instance
	 * @param _args arguments used to instantiate exception
	 * @since 1.0.0
	 */
	public UnableToInstantiateTypifiedExceptionError(final Throwable _cause,final ExceptionType _exceptionType,final Object... _args) {
		super(MessageFormat.format(MESSAGE,_exceptionType,_exceptionType.getClass(),_args,(_cause!=null)? _cause.getMessage() : ""),_cause);
	}
}
