package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderSerivceImpl;
import hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // @Bean만으로도 스프링 빈으로 등록되지만, 싱글턴은 보장되지 않음 주석처리 해보고 주소 찍어보면 알 수 있다.
public class AppConfig { // FactoryMethod를 통해서 제공

    // @Bean memberService --> new MemoryMemberRepository()
    // @Bean orderService --> new MemoryMemberRepository()
    // 두 번이나 new로 생성했는데 싱글턴 깨지는거 아님?

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository
    // 이렇게 memberRepository가 3번 호출 되어야 정상 일 것 같은데 실제로 출력해보면

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // 실제로는 한 번만 호출 된다. (아마 싱글턴으로 스프링이 처리해주기 때문)

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderSerivceImpl(memberRepository(), new FixDiscountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
