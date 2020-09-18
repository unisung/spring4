package org.zerock.domain;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;//페이지 번호
	private int amount;//페이지당 글 갯수
	
	//검색타입, 검색어 저장 속성
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum,int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
	public String[] getTypeArr() {
		String[] arr;
		arr=type==null?new String[] {}:type.split("");//C,T
		System.out.println(Arrays.toString(arr));
		return type==null?new String[] {}:type.split("");//C,T
	}

}
