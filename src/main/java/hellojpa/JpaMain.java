package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            List<Member> result = em.createQuery("select m from Member as m where m.id = 1", Member.class)
                    .setFirstResult(0) // setFirstResult 부터 setMaxResults 까지 페이징 // 0번부터 10번까지
                    .setMaxResults(10) // 각 설정된 db에 맞게 번역해준다.
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            /*Member member = new Member(); // insert
            member.setId(3L);
            member.setName("HelloC");

            em.persist(member); // set 한 후에 persist를 해줘야 한다.
            // 실무에선 em.persist(member); 만 호출해도 Spring 이 코드를 해결해준다.
            */

//            em.remove(findMember); // 삭제하는 쿼리
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
