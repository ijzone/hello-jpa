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
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.setTeamId(team.getId());
            member.setTeam(team);   // 객체 연관관계 매핑
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);
            Team findTeam = findMember.getTeam();   // 객체 연관관계 매핑
            System.out.println("findTeam = " + findTeam.getName());



            tx.commit();    // 영속성 컨텍스트가 DB에 저장되는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}