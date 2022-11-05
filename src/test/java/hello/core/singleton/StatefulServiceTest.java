package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

// Spring Bean은 무상태로 설계해야한다. 절대 공유 자원이 있으면 안된다!
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자가 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        // ThreadA: A사용자 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("UserA : "+userAPrice);
        System.out.println("UserB : "+userBPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}