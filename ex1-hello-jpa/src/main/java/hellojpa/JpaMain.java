package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
//            Member member = new Member();
//            member.setUsername("C");
//
//            em.persist(member);

//            // 연관관계 매핑
//            Team team = new Team();
//            team.setName("TeamA");
////            team.getMembers().add(member);
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();


//            // ??
//            Member member = new Member();
//            member.setUsername("member1");
//
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("teamA");
//            team.getMembers().add(member); // update query 발생
//
//            em.persist(team);

//            // 연관관계 매핑
//            Member member = new Member();
//            member.setCreateBy("kim");
//            member.setCreatedDate(LocalDateTime.now());
//            member.setUsername("user1");
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

//            // 즉시 지연 로딩
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("hello");
//            member.setTeam(team);
//            em.persist(member);
//
//
//            Team team2 = new Team();
//            team2.setName("teamA");
//            em.persist(team2);
//
//            Member member2 = new Member();
//            member2.setUsername("hello");
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

//            // 영속성 전이
//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//            em.remove(findParent);

//            // 임베디드 값 타입
//            Address address = new Address("city", "street", "10000");
//
//            Member member = new Member();
//            member.setUsername("aaaa");
//            member.setHomeAddress(address);
//            em.persist(member);
//            Address newAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//            member.setHomeAddress(newAddress);

//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member member2 = new Member();
//            member2.setUsername("aaaa");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//
//            member.getHomeAddress().setCity("newCity");
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1", "street", "10000"));

            member.getFavoriteFoods().add("dd");
            member.getFavoriteFoods().add("aa");
            member.getFavoriteFoods().add("cc");

            member.getAddressHistory().add(new Address("city2", "street", "10000"));
            member.getAddressHistory().add(new Address("city3", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============================");
            Member findMember = em.find(Member.class, member.getId());

//            findMember.getHomeAddress().setCity(""); // 이거 하지마삼
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("city4", a.getStreet(), a.getZipcode()));

            findMember.getFavoriteFoods().remove("cc");
            findMember.getFavoriteFoods().add("sss");

            findMember.getAddressHistory().remove(new Address("city2", "street", "10000"));
            findMember.getAddressHistory().add(new Address("city5", "street", "10000"));



//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }


            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 중요 닫아줘야함
            em.close();
        }
        emf.close();
    }

//    private static void printMemberAndTeam(Member member) {
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team.getName() = " + team.getName());
//    }
}
