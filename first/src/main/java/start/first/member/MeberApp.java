package start.first.member;

import start.first.AppConfig;
import start.first.member.Grade;
import start.first.member.Member;
import start.first.member.MemberService;
import start.first.member.MemberServiceImpl;

public class MeberApp {
    //IntelliJ 기준 psvm을 치면 아래와 같은 main의 구조가 잡힌다.
    public static void main(String[] args) {
        //AppConfig 객체를 생성
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        //회원 가입
        //기존 코드
        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);
        
        //회원 조회
        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("find member: " + findMember.getName());
    }
}
