package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//jUnit테스트(단위테스트-메소드단위)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	
	@Setter(onMethod_=@Autowired)
	private Restaurant restaurant;
	
	//if(restaurant!=null)
	@Test//메소드를 테스트할수 있는 모듈로 변환
	public void testExist() {
		//jUnit에서 실행하는 메소드 assert~로 시작하는 메소드들
		assertNotNull(restaurant);//Junit테스트 메소드  assertNotNull(객체):객체가   null이 아닌지 확인메소드
		
		log.info(restaurant);
		log.info("------------");
		log.info(restaurant.getChef());
	}
}
