package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    // option + enter로 오버라이드 쉽게 해결

    private static Map<Long, Member> store = new HashMap<>();
    // conquered hashmap 쓰는게 좋음 => 동시성 이슈 때문

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
