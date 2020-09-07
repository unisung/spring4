package com.zerock.domain;

import lombok.Data;

@Data//생성자,getter/setter,toString()
public class SampleDTO {
	private String name;//String타입 DTO 객체 내에 있음-값이 설정되어 전달됨.
	private  int age;//기본타입 속성 - DTO 객체 내에 있음,- 값이 설정되어서 전달됨.
	

}
