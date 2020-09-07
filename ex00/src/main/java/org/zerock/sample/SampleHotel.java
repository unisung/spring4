package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@Getter//getter메소드 자동생성
@ToString//toString 메소드 자동 생성
@RequiredArgsConstructor
public class SampleHotel {
	
	@NonNull
	private Chef chef;//객체타입 속성,스프링 4.3 버전 이후 부터 @Autowired표시 없이 자동 주입 됨.
	
	private String name;//String타입 속성

}
