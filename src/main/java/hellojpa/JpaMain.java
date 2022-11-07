package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();   // DB 작업은 꼭 EntityManager를 통해서 해야된다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member);  // 양방향 연관관계에서 순수 객체 상태를 고려해서 항상 양쪽에 값 설정하기.

            em.flush();
            em.clear();



            tx.commit();    // 영속성 컨텍스트가 DB에 저장되는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}