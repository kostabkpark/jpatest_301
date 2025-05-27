package jpajava;

import domain.Article;
import domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Article_Comment_cascade_delete_test {
  public static void main(String[] args) {
    EntityManager em = Persistence.createEntityManagerFactory("jpatest").createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();
    try {
      // article 부모 엔티티
      Article article = em.find(Article.class, 5L);

      em.remove(article);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
