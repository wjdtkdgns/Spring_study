package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    // 사용 주의
    // 컴파일 타임에 오류 못잡음, 런타임에 오류 발생

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 메서드만 다르고 로직 동일
        // 1
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);

        // 2
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 획득
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        // 클래스, 메소드 동적 변경 가능 -> 메타 데이터를 통한 추상화 이용
        Hello target = new Hello();
        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target); // target의 callA 메소드를 실행하라
        log.info("result1={}", result1);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA"); // 동적 처리 필요
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
