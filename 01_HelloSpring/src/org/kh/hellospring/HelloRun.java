package org.kh.hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloRun {

	public static void main(String[] args) {
//		스프링이 아닌 원래 사용법은 객체생성후 불러오는 방법
//		new MessageBeanHello().sayHellow("spring");
//		ctx 빈 객체 생성 완료
		ApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");
//		빈 호출완료
		MessageBean bean = (MessageBean)ctx.getBean("messageBean");
//		MessageBeanHello 객체 메소드 호출
		bean.sayHello("Spring");
	}
 
}