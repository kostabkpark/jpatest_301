package jpajava;

import domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Emp_Dept_Comp_JPQLTest {
  public static void main(String[] args) {
    EntityManager em = Persistence.createEntityManagerFactory("jpatest").createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();
//    String jpql = "select e from Employee e";
//    List<Employee> resultList = em.createQuery(jpql, Employee.class).getResultList();
//    System.out.println(resultList);
    String fetchJoinJpql = "SELECT e FROM Employee e join fetch e.department";
    List<Employee> resultList = em.createQuery(fetchJoinJpql, Employee.class).getResultList();
    System.out.println(resultList);
    tx.commit();
  }
}
