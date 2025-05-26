package jpajava;

import domain.Company;
import domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DepartmentUpdateTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();

    try {
      System.out.println("부서정보 1차 캐시로 들고 오기");
      Department department = em.find(Department.class, 4);
      System.out.println(department);
      System.out.println("회사정보 1차 캐시로 들고 오기");
      Company company = em.find(Company.class, 2);
      System.out.println(company);
      System.out.println("부서의 회사필드를 수정하기");
      department.setCompany(company);
      System.out.println(department);
      em.persist(department);
      System.out.println("dirty checking 중");
      tx.commit();
      System.out.println("변경사항이 있어서 update 하는 중");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
