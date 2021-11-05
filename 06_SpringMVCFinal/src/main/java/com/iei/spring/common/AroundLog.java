package com.iei.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundLog {
	
	@Pointcut("execution(* com.iei.spring.notice..*Impl.*(..))")
	public void allPointCut() {}
	
	@Around("allPointCut()")
	public Object aroundLogs(ProceedingJoinPoint pp) throws Throwable {
		//ProceedingJoinPoint는 JoinPoint를 상속받아 구현된 인터페이스
		String methodName = pp.getSignature().getName(); //호출될때의 메서드 이름을 알 수 있음
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object obj = pp.proceed(); //메서드가 동작한 시점
		stopWatch.stop();
		System.out.println(methodName + "() 메소드 수행에 걸린 시간 : "+stopWatch.getTotalTimeMillis()+"(ms)");
		return obj;
	}
}
