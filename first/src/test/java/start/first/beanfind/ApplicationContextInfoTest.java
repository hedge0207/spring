package start.first.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import start.first.AppConfigSpring;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        //getBeanDefinitionNames은 컨테이너에 등록 된 모든 빈 이름을 조회한다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            //getBean은 빈 이름으로 빈 객체를 조회한다.
            Object bean = ac.getBean(beanDefinitionName);
            //name은 key, object는 value에 해당
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }
    
    //spirng 내부에서 등록하는 Bean은 제외하고 내가 등록했거나 외부 라이브러리에서 등록한 Bean만 검색
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    ac.getBeanDefinition(beanDefinitionName);
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            //beanDefinition의 역할이 APPLICATION일 때만 출력
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" +
                        bean);
            }
        }
    }

}
