package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DisplayName("Jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository
                           , @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName("select test")
    void givenArticle_whenSelecting_then() {
        List<Article> articles = articleRepository.findAll();

        Assertions.assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @Test
    @DisplayName("insert test")
    void givenData_whenInsert_thenCompareCount() {

        //given
        int previusCount = (int)articleRepository.count();
        Article article = Article.of("This Issue about ..","I beilive ..","issue");

        //when
        articleRepository.save(article);

        //then

        Assertions.assertThat(articleRepository.count()).isEqualTo(previusCount + 1);

    }

    @DisplayName("update test")
    @Test
    void givenData_whenUpdate_thenCompareValue() {

        //given
        Article article = articleRepository.findById(1L).orElseThrow();

        //when
        String title = article.getTitle();
        String newTitle = title + " (this is gonna be updated!!!)";
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + title);
        article.setTitle(newTitle);

        Article savedArticle = articleRepository.save(article);

        //then
        Assertions.assertThat(savedArticle).hasFieldOrPropertyWithValue("title",newTitle);
    }

    @Test
    @DisplayName("delete test")
    void givenId_whenDelete_thenCompareCount() {
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousCount = articleRepository.count();
        long previousCommentCount = articleCommentRepository.count();
        int previousCommentSize = article.getArticleComment().size();

        System.out.println("previousCount >>>  " + previousCommentSize);
        System.out.println("previousCommentCount >>>  " + previousCommentCount);
        System.out.println("previousCommentSize >>>  " + previousCommentSize);

        Long id = article.getId();
        //when
        articleRepository.deleteById(id);

        //then
        Assertions.assertThat(articleRepository.count()).isEqualTo(previousCount - 1);
        Assertions.assertThat(articleCommentRepository.count()).isEqualTo(previousCommentCount - previousCommentSize);

    }
}