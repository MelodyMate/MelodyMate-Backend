package com.melodymatebackend.music.domain;

import com.melodymatebackend.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Long id;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "artist", nullable = false, length = 50)
    private String artist;

    @Column(name = "thumbnail", nullable = false, length = 300)
    private String thumbnail;

    @Column(name = "duration", nullable = false, length = 20)
    private String duration;

    @Column(name = "releaseDate", nullable = true, length = 15)
    private String releaseDate;

    @OneToMany(mappedBy = "music")
    private List<ViewCount> viewCountList = new ArrayList<>();

    public void addViewCount(ViewCount viewCount) {
        viewCountList.add(viewCount);
        viewCount.setMusic(this);
    }


}