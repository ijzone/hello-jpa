package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();   // DB 작업은 꼭 EntityManager를 통해서 해야된다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush(); // flush 기능으로 인해 DB에 바로 반영된다. flush를 호출한다고 해도 캐시에 있는 데이터가 지워지는 건 아니다.
            System.out.println("=====================");

            tx.commit();    // 영속성 컨텍스트가 DB에 저장되는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}