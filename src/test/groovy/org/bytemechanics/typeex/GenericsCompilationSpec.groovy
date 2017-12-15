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

import spock.lang.*
import spock.lang.Specification
import org.bytemechanics.typeex.*
import org.bytemechanics.typeex.impl.*

/**
 * @author afarre
 */
class GenericsCompilationSpec extends Specification {

	def "Generic exception build should work correctly and return a throwable"(){
		when:
			def result=TestGenericsCompilation.correctCastWhenBuildExceptionThrowable()
			
		then:
			result==true
	}
	def "Generic exception build should work correctly"(){
		when:
			def result=TestGenericsCompilation.correctCastWhenBuildException()
			
		then:
			result==true
	}
	def "Generic exception build assignation should work correctly"(){
		when:
			def result=TestGenericsCompilation.assignMockedChekedException()
			
		then:
			result==true
	}
}