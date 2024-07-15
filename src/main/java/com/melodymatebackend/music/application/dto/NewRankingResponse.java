package com.melodymatebackend.music.application.dto;

import java.time.LocalDate;
import java.util.List;

public record NewRankingResponse(
    int count,
    LocalDate rankDate,
    List<NewMusicResponse> data
) {


}
