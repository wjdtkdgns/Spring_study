package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 로딩때, 이건 하나만, 공유해서 사용
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 작업 대신 해주는 애, 쓰레드간 공유 x
        EntityManager em = emf.createEntityManager();

        // 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 삽입
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//
//            em.persist(member);

            //검색
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());

            // 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // 객체로 가져오기 -> JPQL
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//
//            }

//            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            //영속
//            System.out.println("Before");
//            // 캐쉬에 저장
//            em.persist(member);
//            System.out.println("After");
//
//            // 캐쉬에서 조회
//            Member findMember = em.find(Member.class, 101L);
//
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            // db에서 조회
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);

//            // 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("=================");

//            // 변경 감지
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");
//            // 이걸 할 필요 없음
////            em.persist(member);

//            // 플러쉬
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush();
//            System.out.println("=================");
            Member member = new Member();
            member.setUsername("C");

            em.persist(member);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            // 중요 닫아줘야함
            em.close();
        }
        emf.close();
    }
}
