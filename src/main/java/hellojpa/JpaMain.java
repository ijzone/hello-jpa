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
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.detach(member);  // 영속성 컨텍스트에서 더이상 관리하지 않는다.
//            em.clear(); // 영속성 컨텍스트 초기화
            Member member2 = em.find(Member.class, 150L);
//            System.out.println(member == member2);  // 초기화 이후 DB에서 다시 조회하기 때문에 false

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