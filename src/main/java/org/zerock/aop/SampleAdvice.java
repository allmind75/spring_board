package org.zerock.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@Componen : 스프링 빈으로 인식되기 위해 설정
//@Aspect : AOP 기능을 하는 클래스의 선언에 추가
@Component			
@Aspect				
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	
//	//@Before : 해당 메소드를 먼저 실행한 후 target 메소드가 실행되게 함
//	//excution ... 부분은 Pointcut을 지정하는 문법으로 AspectJ 언어의 문법 사용
//	//아래는 'org.zerock.service.MessageService'로 시작하는 모든 클래스의 모든 메소드를 지정
//	@Before("execution(* org.zerock.service.MessageService*.*(..))")
//	public void startLog(JoinPoint jp) {
//		
//		logger.info("---------------------------------");
//		logger.info("---------------------------------");
//		logger.info(Arrays.toString(jp.getArgs()));
//		logger.info(jp.getKind());
//		
//	}
//	
//	
//	//@Around : 메소드의 실행 전체를 앞,뒤로 감싸서 특정하 기능을 실행할 수 있는
//	// 가장 강력한 타입의 Advice
//	//@Around 이용하는 경우 반드시 메소드 리턴 타입은 Object
//	@Around("execution(* org.zerock.service.MessageService*.*(..))")
//	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
//	
//		long startTime = System.currentTimeMillis();
//		logger.info("Around time Log : " + Arrays.toString(pjp.getArgs()));
//		
//		Object result = pjp.proceed();				//Object proceed() : 다음 Advice를 실행하거나, 실제 target 객체의 메소드를 실행
//													//proceed는 Exception보다 상위의 Throwable 처리
//		
//		long endTime = System.currentTimeMillis();
//		logger.info(pjp.getSignature().getDeclaringTypeName() + " : " + (endTime - startTime));
//		logger.info("=================================================================");
//		
//		return result;
//	}
}
