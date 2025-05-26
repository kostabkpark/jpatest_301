package jpajava;

import domain.Department;
import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeUpdateTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작");

      try {
        // 미션 = 202530 인 홍길동의 부서를 sales 부서로 이동하시오.
        System.out.println("1차 캐시에 없음");
        Employee emp = em.find(Employee.class, "202530");
        System.out.println("데이터베이스에서 emp 를 1차 캐시로 가져옴");
        Department dept = em.find(Department.class, 5);
        System.out.println("데이터베이스에서 dept를 1차 캐시로 가져옴");
        System.out.println(emp);
        // 부서 정보를 변경하기
        emp.setDepartment(dept);
        em.persist(emp);
        System.out.println(emp);
        System.out.println("1차 캐시에 수정내용 반영");
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
