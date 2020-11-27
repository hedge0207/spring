package start.first;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import start.first.member.MemberRepository;
import start.first.member.MemoryMemberRepository;

@Configuration
//@ComponentScan는 @Component 어노테이션이 붙은 클래스들을 스캔해서 빈을 등록한다.
//속성 중 제외 할 것들을 지정할 수 있는 excludeFilters가 있다.
//아래에서 Configuration을 제외한 이유는 @Configuration 어노테이션의 소스코드를 보면 내부에 @Component 어노테이션이 사용되기 때문이다.
//보통 설정 정보(AppConfig)를 스캔 대상에서 제외하지는 않지만 현재는 앞에서 예시를 위해 사용한 AppConfigSpring이 살아있기에 아래와 같이 제외해준다.
@ComponentScan(
        basePackages = "start.first",
        //아래와 같이 복수의 시작 위치를 지정할 수도 있다.
        //basePackages = {"start.first","start.second"},
        //혹은 아래와 같이 basePackageClasses 사용할 수도 있다.
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //자동 등록-수동 등록 충돌 테스트를 위한 임시 코드
    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    
}
