package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//@Select어노테이션으로 sql쿼리문을 매핑처리하여 사용할수 있음.
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();

}
