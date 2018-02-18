# Type-ex
[![Latest version](https://maven-badges.herokuapp.com/maven-central/org.bytemechanics/type-ex/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.bytemechanics/type-ex/badge.svg)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=org.bytemechanics%3Atype-ex)](https://sonarcloud.io/dashboard/index/org.bytemechanics%3Atype-ex)
[![Coverage](https://sonarcloud.io/api/badges/measure?key=org.bytemechanics%3Atype-ex&metric=coverage)](https://sonarcloud.io/dashboard/index/org.bytemechanics%3Atype-ex)

Type-ex it's a library to make easy application errors classification. 

## Motivation
In the old times all application errors were indexed in never-ending lists in big manuals to describe the possible problems and its solutions. 

## Quick start
1. First of all include the Jar file in your compile and execution classpath.
**Maven**
```Maven
	<dependency>
		<groupId>org.bytemechanics</groupId>
		<artifactId>type-ex</artifactId>
		<version>X.X.X</version>
	</dependency>
```
**Graddle**
```Gradle
dependencies {
    compile 'org.bytemechanics:type-ex:X.X.X'
}
```
1. Create your typified exceptions
```Java
package mypackage;
import org.bytemechanics.typeex.impl.TypifiedException;
public enum MyExceptionType implements ExceptionType{
	EXCEPTION_TYPE_NO_PARAMETERIZED("This message has no substitution parameters"),
	EXCEPTION_TYPE_PARAMETERIZED("This message has two {} substitution parameters {}"),
	;	
	private final String message;
	MyExceptionType(final String _message){
		this.message=_message;
	}	
	@Override
	public String getMessage() {
		return this.message;
	}
}
```
1. Launch an exception
**Manually**
```Java
throw MyExceptionType.EXCEPTION_TYPE_PARAMETERIZED
						.from(cause)
						.with("String1",1);
```
**From lambda**
```Java
Optional.of(null)
		.orElseThrow(MyExceptionType.EXCEPTION_TYPE_PARAMETERIZED
						.from(cause)
						.with("String1",1))
```
