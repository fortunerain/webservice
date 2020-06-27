package com.farm.test.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 어노테이션이 생성될수 있는 위치 지정. 파라미터에서만 가능함.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   // @interface : 어노테이션 클래스로 지정함.
}
