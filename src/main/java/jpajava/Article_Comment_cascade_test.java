package jpajava;

import domain.Article;
import domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Article_Comment_cascade_test {
  public static void main(String[] args) {
    EntityManager em = Persistence.createEntityManagerFactory("jpatest").createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();
    try {
      // article 부모 엔티티
      Article article = new Article();
      article.setTitle("This is a test article");
      article.setContent("This is a test article - content");

      // comment 자식 엔티티
      Comment com1 = new Comment();
      com1.setContent("This is a test comment - content 1");
      article.addComment(com1);

      Comment com2 = new Comment();
      com2.setContent("This is a test comment - content 2");
      article.addComment(com2);

      em.persist(article);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
