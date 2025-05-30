package jpajava;

import domain.Company;
import domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

public class DepartmentTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    System.out.println("트랜잭션 시작");
    // department entity 객체 생성 < == 비영속
    try {
      Department department = new Department();
      // company 정보를 db 에서 가져와서 객체에 저장한 후, 참조변수에 객체의 주소를 저장
      Company company = em.find(Company.class, 1);
      department.setCompany(company);
//      department.setDeptId(1);
      department.setDeptName("Sales");
      System.out.println("비영속 상태");
      // persist() <== 영속
      em.persist(department);
      System.out.println("영속 상태  -- 1차 캐시에 들어가고, 캐시의 내용과 db 의 내용을 동일성 보장하기 위해 db 에 바로 insert 를 함");
      // commit()
      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 완료");
    } catch (Exception e) {
      // rollback()
      tx.rollback();
    }

  }
}
