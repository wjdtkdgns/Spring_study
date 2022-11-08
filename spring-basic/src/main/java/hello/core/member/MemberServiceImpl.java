package hello.core.member;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    // cmd + shift + enter 면 ;까지 붙음


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
