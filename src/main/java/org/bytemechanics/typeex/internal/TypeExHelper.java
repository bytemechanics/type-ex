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
package org.bytemechanics.typeex.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Stream;
import org.bytemechanics.typeex.ExceptionType;
import org.bytemechanics.typeex.TypifiableException;
import org.bytemechanics.typeex.exceptions.UnableToInstantiateTypifiedExceptionError;

/**
 * Helper class for <strong>internal use only</strong>
 * Please keep in mind that this <strong>iclass can be changed, renamed, deleted or extended without previous advice between fix releases, minor versions or major versions</strong>
 * @author Albert Farré Figueras
 * @since 0.0.1
 * @version 1.0.0
 */
public final class TypeExHelper {
	
	public static final Optional<Constructor> findSuitableConstructor(final ExceptionType _exceptionType) {

		Optional<Constructor> reply;
		
		reply=Stream.of(_exceptionType.getExceptionClass().getConstructors())
				.filter(constructor -> constructor.getParameterCount()==3)
				.filter(constructor -> constructor.getParameterTypes()[0].isAssignableFrom(Throwable.class))
				.filter(constructor -> constructor.getParameterTypes()[1].isAssignableFrom(_exceptionType.getClass()))
				.filter(constructor -> constructor.getParameterTypes()[2].isAssignableFrom(Object[].class))
				.findAny();
		
		return reply;	
	}

	public static final Optional<TypifiableException> instance(final Constructor _constructor,final Throwable _cause,final ExceptionType _exceptionType,final Object... _args) {

		Optional<TypifiableException> reply=Optional.empty();
		
		try {
			reply=Optional.ofNullable(_constructor.newInstance(_cause,_exceptionType,_args))
							.map(instance -> (TypifiableException)instance);			
		} catch (SecurityException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			throw new UnableToInstantiateTypifiedExceptionError(e,_exceptionType,_args);
		}
		
		return reply;	
	}
}
