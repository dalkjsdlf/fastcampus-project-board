package com.fastcampus.projectboard.domain;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class Article {

    private Long id;                    // ID
    private String title;               // 게시글 제목
    private String content;             // 내용
    private String hashtag;             // 해시태그

    private LocalDateTime createdAt;    // 생성일시
    private String createdBy;           // 생성자
    private LocalDateTime modifiedAt;   // 수정일시
    private String modifiedBy;          // 수정자
}
