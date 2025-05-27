package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeMultipleTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    // JPA 성능 최적화 기능 - 2) 트랜잭션을 지원하는 쓰기 지연
    tx.begin();
    System.out.println("트랜잭션 시작");
    try {
      // 미션 : IT 부서로 신입사원이 3명 들어옴. 202590, 202591, 202592 / test90, test91, test92 / 2025-05-27 / 200
      System.out.println("비영속 상태");
      // dept 객체 가져와서 참조변수를 emp 객체 생성시에 생성자의 변수로 입력하자.
      System.out.println("부서 객체 정보 가져오기");
      Department dept = em.find(Department.class, 1); // IT 부서
      Employee emp1 = new Employee("202590","test90",dept,"2025-05-27", 200L);
      em.persist(emp1);
      Employee emp2 = new Employee("202591","test91",dept,"2025-05-27", 200L);
      em.persist(emp2);
      Employee emp3 = new Employee("202592","test92",dept,"2025-05-27", 200L);
      em.persist(emp3);
      System.out.println("영속 상태  -- 1차 캐시에 들어감/쓰기 지연 SQL 저장소에 Insert SQL 문이 저장된 상태");

      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후 --> 1차 캐시의 내용과 db 의 내용이 같아짐");
    } catch (Exception e) {
      tx.rollback();
    }
    System.out.println("트랜잭션 종료");
  }
}
