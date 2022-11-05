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
//            Member findMember1 = em.find(Member.class, 101L);   // DB에서 조회
//            Member findMember2 = em.find(Member.class, 101L);   // 1차 캐시에 저장된 데이터 조회 (DB조회 X)
//            System.out.println(findMember1 == findMember2); // 영속 엔티티의 동일성 보장

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");    // 업데이트 시, Dirty checking(변경 감지) 기능으로 인해 em.persist(member); 코드 불필요
//            em.persist(member);
            
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