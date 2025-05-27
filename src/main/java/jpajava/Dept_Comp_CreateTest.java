package jpajava;

import domain.Company;
import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Dept_Comp_CreateTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin(); // 1) 트랜잭션 시작
    try {
      System.out.println("트랜잭션 시작 == 1)");
      Department dept = new Department(); // 3) DO - dept 생성 , 비영속
      dept.setDeptId(0);
      dept.setDeptName("NewDept");
      dept.setCompany(null);
      System.out.println("dept 생성 == 3)"  + dept);
      Company comp = new Company(); // 4) DO - comp 생성, 비영속
      comp.setId(0);
      comp.setName("NewComp");
      System.out.println("comp 생성 == 4)" + comp);
      em.persist(comp);  // 5) comp - 영속상태로 만듦 / SQL 즉시 쓰기
      System.out.println("comp 영속상태 == 5) " + comp);
      dept.setCompany(comp);
      System.out.println(dept);
      em.persist(dept); // 6) dept - 영속상태로 만듦 / SQL 즉시 쓰기
      System.out.println("dept 영속상태 6) " + dept);
      System.out.println("커밋전 == 8) ");
      tx.commit(); // 8) 지연쓰기 저장소에 있는 SQL 문 이때 발생
      System.out.println("커밋후");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
