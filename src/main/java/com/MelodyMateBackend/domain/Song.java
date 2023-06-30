package com.MelodyMateBackend.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song {

    private Long sid;
    private int rank;
    private String songTitle;
    private String artist;
    private String thumbnail;
    private String duration;
    private String viewCount;
    private String url;
    private String releaseDate;
    private String chartMonth;

}
