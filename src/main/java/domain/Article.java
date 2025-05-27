package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Article { // 게시글 엔티티
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "article_id")
  private Long id;

  private String title;
  private String content;

  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  //== 연관관계 메서드 ==//
  public void addComment(Comment comment) {
    comments.add(comment);
    comment.belongToArticle(this);
  }






















}
