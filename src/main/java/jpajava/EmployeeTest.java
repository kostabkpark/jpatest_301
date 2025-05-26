package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();
    System.out.println("트랜잭션 시작");
    try {
      // 미션 : ERP 부서로 신입사원이 들어옴. 202550 / 김연아 / 2025-05-26 / 200
      System.out.println("비영속 상태");
      // dept 객체 가져와서 참조변수를 emp 객체 생성시에 생성자의 변수로 입력하자.
      System.out.println("부서 객체 정보 가져오기");
      Department dept = em.find(Department.class, 3);
      Employee emp = new Employee("202550","김연아",dept,"2025-05-26", 200L);
      em.persist(emp);
      System.out.println("영속 상태  -- 1차 캐시에 들어감/아직 db 에는 저장 되지 않은 상태");
      em.find(Employee.class, "202550");
      System.out.println("1차 캐시에서 가져옴 -- db에는 아직 저장 안된 상태");
      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후 --> 1차 캐시의 내용과 db 의 내용이 같아짐");
    } catch (Exception e) {
      tx.rollback();
    }
    System.out.println("트랜잭션 종료");
  }
}
