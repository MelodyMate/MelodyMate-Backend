package com.project.MelodyMateBackend.domain.Song;

import com.project.MelodyMateBackend.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Song extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 노래 제목
    @Column(length = 100, nullable = false)
    private String songTitle;

    // 가수
    @Column(length = 100, nullable = false)
    private String artist;

    // 썸네일 이미지
//    @Column(length = 500, nullable = false)
//    private String thumbnail;
//
//    // 재생시간
//    @Column(length = 100, nullable = false)
//    private String duration;
//
//    // 링크
//    @Column(length = 500, nullable = false)
//    private String url;
//
//    // 발매일
//    @Column(length = 100, nullable = false)
//    private String releaseDate;
//
//    // 조회수
//    @Column(length = 100, nullable = false)
//    private Long viewCount;
//
//    // 하트 상태
//    @Column(length = 100)
//    private String hearStatus = "unLike";
}
