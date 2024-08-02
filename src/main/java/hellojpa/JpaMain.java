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
            /*

            // 영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            em.flush(); // 쿼리 강제 실행 (commit 전 쓰기 지연 SQL 에 저장된 쿼리들이 즉시 반영)
            // flush 는 영속성 컨텍스트를 비우지 않음!!

            // db 수정 코드 // 변경감지
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA"); // em.persist(member); 수정시엔 코드 입력하지 않아도 된다.

            // 페이징
            List<Member> result = em.createQuery("select m from Member as m where m.id = 1", Member.class)
                    .setFirstResult(0) // setFirstResult 부터 setMaxResults 까지 페이징 // 0번부터 10번까지
                    .setMaxResults(10) // 각 설정된 db에 맞게 번역해준다.
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 객체를 생성하기만 한 비영속 상태
            Member member = new Member(); // insert
            member.setId(3L);
            member.setName("HelloC");

            // 영속 상태 // 영속상태가 된다해서 쿼리가 실행되지는 않는다.
            em.persist(member); // 1차 캐시에 저장 & insert SQL 생성 후 쓰기 지연 SQL 저장소에 저장 (아직 커밋 안됨 tx.commit(); 시 실행)
            // **실무에선 em.persist(member); 만 호출해도 Spring 이 코드를 해결해준다.**

            // 엔티티 삭제
            em.remove(findMember);
            *
            */

            Member member = new Member();
//            member.setId("ID_A");
            member.setUsername("C");

            em.persist(member);

            tx.commit(); // 쓰기 지연 SQL 저장소에 있던 쿼리문들이 flush 됨
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
