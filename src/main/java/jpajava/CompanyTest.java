package jpajava;

import domain.Company;
import domain.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CompanyTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    System.out.println("트랜잭션 시작");
    // company entity 객체 생성 < == 비영속
    try {
      Company company = new Company();
      company.setName("LG CNS");
      System.out.println("비영속 상태");
      // persist() <== 영속
      em.persist(company);
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
