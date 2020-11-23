package start.first.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import start.first.member.MemberService;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
        //GenericXmlApplicationContext를 사용하여 xml 파일 명을 넣는다.
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
