package jpabook.jpashop;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // junit4 @RunWith
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional // 이거 테스트에 있으면, 테스트 끝나고 롤백
    @Rollback(value = false) // 롤백 막기
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");
        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // 같은 영속성 컨텍스트 안에서는 아이디 같으면 같은 엔티티로 인식

    }
}