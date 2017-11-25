/*
 * Copyright 2017 afarre.
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
package org.bytemechanics.typeex;

import org.bytemechanics.typeex.ExceptionType;
import org.bytemechanics.typeex.impl.TypifiedException;


/**
 * @author afarre
 */
public enum MockedTypifiedExceptionType implements ExceptionType<TypifiedException>{
	
	TEST_NO_PARAMS("Test message without parameters"),
	TEST_WITH_1_PARAM("Test message with parameter1 {} other"),
	TEST_WITH_PARAMS("Test message with parameter1 {} and parameter2 {}"),
	;
	
	private final String message;
	
	MockedTypifiedExceptionType(final String _message){
		this.message=_message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public Class<? extends TypifiedException> getExceptionClass() {
		return TypifiedException.class;
	}
}
