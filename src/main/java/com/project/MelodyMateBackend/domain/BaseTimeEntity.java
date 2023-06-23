package com.project.MelodyMateBackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

//    public String getCreatedDate() {
//        String dateFormat = "yyyy-MM-dd";
//        if (LocalDate.now().isEqual(LocalDate.of(createdDate.getYear(), createdDate.getMonth(), createdDate.getDayOfMonth()))) {
//            dateFormat = "HH:mm";
//        } else if (LocalDate.now().compareTo(LocalDate.of(createdDate.getYear(), createdDate.getMonth(), createdDate.getDayOfMonth())) < 0) {
//            dateFormat = "yyyy-MM-dd";
//        }
//        DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern(dateFormat);
//        return createdDate.format(dtFmt);
//    }
}
