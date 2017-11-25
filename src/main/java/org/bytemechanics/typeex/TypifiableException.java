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

import org.bytemechanics.typeex.internal.SimpleFormat;
import org.bytemechanics.typeex.internal.TypeExHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface to be implemented by any typified exception
 *
 * @author afarre
 * @param <T> extends herself
 * @since 0.1.0
 */
public interface TypifiableException<T extends TypifiableException> extends Supplier<T> {

	/**
	 * @return Throwable cause
	 * @see Throwable#getCause()
	 * @since 0.1.0
	 */
	public Throwable getCause();

	/**
	 * Returns the ExceptionType instance of this TypifiableException
	 *
	 * @return The ExceptionType instance of this TypifiableException
	 * @since 0.1.0
	 */
	public ExceptionType getExceptionType();

	/**
	 * Returns the arguments provided to replace into the ExceptionType message TypifiableException
	 *
	 * @return An Optional with the array of arguments to replace into the ExceptionType message TypifiableException
	 * @since 0.1.0
	 */
	public Optional<Object[]> getArguments();

	/**
	 * Returns the formatted message (replaced with provided to replace into the ExceptionType message TypifiableException
	 *
	 * @return An Optional with the array of arguments to replace into the ExceptionType message TypifiableException
	 * @since 0.1.0
	 */
	public default String getFormattedMessage() {
		return getArguments()
				.map(args -> SimpleFormat.format(getExceptionType().getMessage(), args))
				.orElse(getExceptionType().getMessage());
	}

	/**
	 * Supplier implementation in order to reduce code boilerplate
	 * 
	 * @see Supplier
	 * @return this instance
	 * @since 0.3.0
	 */
	@Override
	public default T get() {
		return (T)this;
	}
	
	/**
	 * Returns clone of this same TypifiableException object with the given cause
	 *
	 * @param _cause cause of the exception
	 * @return The T instance
	 * @since 0.3.0
	 */
	public default T from(final Throwable _cause) {
		return TypeExHelper.findSuitableConstructor(getExceptionType())
							.flatMap(constructor -> TypeExHelper.instance(constructor,_cause, getExceptionType(),getArguments().orElse(null)))
							.map(instance -> (T)instance)
							.orElseThrow(() -> new Error(SimpleFormat.format("Unable to find any suitable constructor for class {} with arguments {}"
																	,getExceptionType().getExceptionClass(),Arrays.asList(new Object[]{Throwable.class,getExceptionType().getClass(),Object[].class}))));
	}

	/**
	 * Returns clone of this same TypifiableException object with the given arguments to replace into message
	 *
	 * @param _args arguments to replace to the getMessage() text with the same format basis explained above
	 * @return The T instance
	 * @since 0.3.0
	 */
	public default T with(final Object... _args) {
		return TypeExHelper.findSuitableConstructor(getExceptionType())
							.flatMap(constructor -> TypeExHelper.instance(constructor,getCause(), getExceptionType(),_args))
							.map(instance -> (T)instance)
							.orElseThrow(() -> new Error(SimpleFormat.format("Unable to find any suitable constructor for class {} with arguments {}"
																	,getExceptionType().getExceptionClass(),Arrays.asList(new Object[]{Throwable.class,getExceptionType().getClass(),Object[].class}))));
	}

	/**
	 * @param _printWriter writer
	 * @see Throwable#printStackTrace(java.io.PrintWriter)
	 * @since 0.1.0
	 */
	public void printStackTrace(final PrintWriter _printWriter);

	/**
	 * Returns the stacktrace as string
	 *
	 * @return The stacktrace in string format
	 * @since 0.1.0
	 */
	public default Optional<String> getStringStacktrace() {

		Optional<String> reply;

		try (StringWriter writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer)) {
			printStackTrace(printWriter);
			reply = Optional.of(writer.toString());
		} catch (IOException e) {
			Logger.getLogger(TypifiableException.class.getName()).log(Level.SEVERE, "Unable to convert exception {} to string", new Object[]{getExceptionType(), e});
			reply = Optional.empty();
		}

		return reply;
	}
}
