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
package org.bytemechanics.typeex;

import org.bytemechanics.typeex.impl.TypifiedError;
import org.bytemechanics.typeex.impl.TypifiedException;

/**
 * AS Mockito uses groovy that is a non strong typed language, it's impossible to test generics casting in groovy
 * @author afarre
 */
public class TestGenericsCompilation {
	
	public static boolean correctCastWhenBuildException(){
		
		boolean reply=true;
		
		try{
			final MockedCheckedException mockedException=MockedTypifiedCheckedExceptionType.TEST_NO_PARAMS.with("arg1").from(new Exception("origin"));
			throw mockedException;
		}catch(MockedCheckedException e){
			reply&=(e instanceof MockedCheckedException);
			try{
				final TypifiedError typifiedError=MockedTypifiedErrorType.TEST_NO_PARAMS.with("arg1").from(e);
				throw typifiedError;
			}catch(TypifiedError e2){
				reply&=(e2 instanceof TypifiedError);
				try{
					final TypifiedException typifiedException=MockedTypifiedExceptionType.TEST_NO_PARAMS.with("arg1").from(e2);
					throw typifiedException;
				}catch(TypifiedException e3){
					reply&=(e3 instanceof TypifiedException);
					try{
						final TypifiedException typifiedException=MockedTypifiedExceptionType.TEST_NO_PARAMS.get();
						throw typifiedException;
					}catch(TypifiedException e4){
						reply&=(e4 instanceof TypifiedException);
					}
				}
			}
		}
		
		return reply;
	}
	public static boolean correctCastWhenBuildExceptionThrowable(){
		
		boolean reply=true;
		
		try{
			throw MockedTypifiedCheckedExceptionType.TEST_NO_PARAMS.with("arg1").from(new Exception("origin"));
		}catch(MockedCheckedException e){
			reply&=(e instanceof MockedCheckedException);
			try{
				throw MockedTypifiedErrorType.TEST_NO_PARAMS.with("arg1").from(e);
			}catch(TypifiedError e2){
				reply&=(e2 instanceof TypifiedError);
				try{
					throw MockedTypifiedExceptionType.TEST_NO_PARAMS.with("arg1").from(e2);
				}catch(TypifiedException e3){
					reply&=(e3 instanceof TypifiedException);
					try{
						throw MockedTypifiedExceptionType.TEST_NO_PARAMS.get();
					}catch(TypifiedException e4){
						reply&=(e4 instanceof TypifiedException);
					}
				}
			}
		}
		
		return reply;
	}

	private static boolean assigableMethod(MockedCheckedException _exception){
		return _exception!=null;
	}
	public static boolean assignMockedChekedException(){
		return assigableMethod(MockedTypifiedCheckedExceptionType.TEST_NO_PARAMS.with("arg1").from(new Exception("origin")));
	}
	public static boolean assignMockedChekedExceptionSupplier(){
		return assigableMethod(MockedTypifiedCheckedExceptionType.TEST_NO_PARAMS.get());
	}
}
