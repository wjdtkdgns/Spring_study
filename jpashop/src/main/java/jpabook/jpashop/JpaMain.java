package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
