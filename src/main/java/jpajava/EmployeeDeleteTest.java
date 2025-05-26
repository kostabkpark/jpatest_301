package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeDeleteTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작");

        try {
          System.out.println("1차 캐시에 없음");
          Employee emp = em.find(Employee.class, "202501");
          System.out.println("데이터베이스에서 1차 캐시로 가져옴");
          System.out.println(emp);
          em.remove(emp);
          System.out.println("1차 캐시에서 삭제");
          System.out.println("커밋전");
          tx.commit();
          System.out.println("커밋후");
        } catch (Exception e) {
          tx.rollback();
        }
        em.close();
        emf.close();
    }
}
