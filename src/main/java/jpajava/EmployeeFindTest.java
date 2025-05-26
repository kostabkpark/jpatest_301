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

        tx.begin();
        System.out.println("트랜잭션 시작");

        System.out.println("1차 캐시에 없음");
        Employee emp = em.find(Employee.class, "202402");
        System.out.println("데이터베이스에서 1차 캐시로 가져옴");
        System.out.println(emp);
        Employee emp1 = em.find(Employee.class, "202402");
        System.out.println("1차 캐시에서 가져옴");
        System.out.println(emp == emp1);
        tx.commit();
        em.close();
        emf.close();
    }
}
