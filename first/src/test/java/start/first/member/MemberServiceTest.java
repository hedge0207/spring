package start.first.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import start.first.AppConfig;

public class MemberServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    //MemberService memberService = new MemberServiceImpl();
    
    @Test
    void join(){

        //given: 무엇이 주어졌을 때
        Member member = new Member(1L,"memberA",Grade.VIP);

        //when: 무엇을 하면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then: 어떻게 되는가
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
