package jpql;

import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("팀A");
            em.persist(team);

            Member member = new Member();
            member.setUsername("회원1");
            member.setTeam(team);
            em.persist(member);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);

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
