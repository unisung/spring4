package com.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
   private String title;
   //private Date dueDate;//이 형식으로 선언한 후에 Controller에서 @InitBinder사용하여 format맞춤
   
   //파라미터로넘어온 문자열"yyyy/MM/dd"형태의 값을  Date 타입으로 변환하는 어노테이션
   //아래와 같이 선언하면 Controller에서 @InitBinder어노테이션을 사용하지 않고 Date 변환 함.
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date fromDate;	
}

