package start.first.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Configuration
    @ComponentScan(
            //type = FilterType.ANNOTATION으로 어노테이션을 사용하여 필터링 할 것임을 알려준다.
            //classes = MyIncludeComponent.class로 어떤 어노테이션으로 필터링 할 것인지 알려준다.
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        //아래 코드를 실행하면 바로 NoSuchBeanDefinitionException가 발생
        //BeanB beanB = ac.getBean("beanB",BeanB.class);

        //따라서 아래와 같이 테스트 한다.
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                ()->ac.getBean("beanB",BeanB.class));
    }

}
