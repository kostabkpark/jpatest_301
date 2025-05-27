package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeFindTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // JPA 의 성능 최적화 기능 - 1) 1차 캐시와 DB 의 동일성 보장
        tx.begin();
        System.out.println("트랜잭션 시작");

        System.out.println("1차 캐시에 없음");
        Employee emp1 = em.find(Employee.class, "202598");
        System.out.println("데이터베이스에서 1차 캐시로 가져옴");
        System.out.println(emp1.getEmpId() + emp1.getEmpName() +
            emp1.getDepartment() + emp1.getDepartment().getCompany());
        Employee emp2 = em.find(Employee.class, "202598");
        System.out.println("1차 캐시에서 가져옴");
        System.out.println(emp1 == emp2);
        System.out.println("데이터베이스에서 가져온 emp1 과 1차 캐시의 emp2 는 동일함");

        tx.commit();
        em.close();
        emf.close();
    }
}
