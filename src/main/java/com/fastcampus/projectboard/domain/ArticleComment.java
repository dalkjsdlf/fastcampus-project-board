package com.fastcampus.projectboard.domain;

import com.querydsl.core.types.EntityPath;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Objects;

@EnableJpaAuditing
@ToString
@Entity
@Table(indexes={
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
public class ArticleComment extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // ID

    @ManyToOne(optional = false)
    @Setter
    private Article article;            // 게시글 ID

    @Setter
    @Column(nullable = false, length = 500)
    private String content;             // 댓글 내용

    protected ArticleComment(){

    }

    private ArticleComment(Article article, String content) {
        this.content = content;
        this.article = article;
    }

    public static ArticleComment of(Article article, String content){
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ArticleComment articleComment))return false;
        return id != null && id.equals(articleComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
