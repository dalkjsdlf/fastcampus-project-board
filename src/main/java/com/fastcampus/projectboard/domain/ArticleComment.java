package com.fastcampus.projectboard.domain;

import java.time.LocalDateTime;


public class ArticleComment {

    private Long id;                    // ID
    private Article article;            // 게시글 ID
    private String Content;             // 댓글 내용

    private LocalDateTime createdAt;    // 생성일시
    private String createdBy;           // 생성자
    private LocalDateTime modifiedAt;   // 수정일시
    private String modifiedBy;          // 수정자
}
