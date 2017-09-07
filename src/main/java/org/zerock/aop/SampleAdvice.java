package org.zerock.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@Componen : ������ ������ �νĵǱ� ���� ����
//@Aspect : AOP ����� �ϴ� Ŭ������ ���� �߰�
@Component			
@Aspect				
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	
//	//@Before : �ش� �޼ҵ带 ���� ������ �� target �޼ҵ尡 ����ǰ� ��
//	//excution ... �κ��� Pointcut�� �����ϴ� �������� AspectJ ����� ���� ���
//	//�Ʒ��� 'org.zerock.service.MessageService'�� �����ϴ� ��� Ŭ������ ��� �޼ҵ带 ����
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
//	//@Around : �޼ҵ��� ���� ��ü�� ��,�ڷ� ���μ� Ư���� ����� ������ �� �ִ�
//	// ���� ������ Ÿ���� Advice
//	//@Around �̿��ϴ� ��� �ݵ�� �޼ҵ� ���� Ÿ���� Object
//	@Around("execution(* org.zerock.service.MessageService*.*(..))")
//	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
//	
//		long startTime = System.currentTimeMillis();
//		logger.info("Around time Log : " + Arrays.toString(pjp.getArgs()));
//		
//		Object result = pjp.proceed();				//Object proceed() : ���� Advice�� �����ϰų�, ���� target ��ü�� �޼ҵ带 ����
//													//proceed�� Exception���� ������ Throwable ó��
//		
//		long endTime = System.currentTimeMillis();
//		logger.info(pjp.getSignature().getDeclaringTypeName() + " : " + (endTime - startTime));
//		logger.info("=================================================================");
//		
//		return result;
//	}
}
