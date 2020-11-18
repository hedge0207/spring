package start.first.member;

public class MemberServiceImpl implements MemberService{

    //기존에는 아래와 같이 MemberServiceImpl에서 MemberRepository도 의존하고 MemoryMemberRepository에도 의존했다.
    //이는 배우가 기획과 캐스팅을 하는 것과 마찬가지인 것이다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //생성자를 만든다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
