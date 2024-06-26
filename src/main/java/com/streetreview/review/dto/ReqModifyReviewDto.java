package com.streetreview.review.dto;

import com.streetreview.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ReqModifyReviewDto {

    private String content;
    @LastModifiedDate
    private Timestamp updatedDate;

    @Builder
    public ReqModifyReviewDto(String content, Timestamp updatedDate) {
        this.content = content;
        this.updatedDate = updatedDate;
    }
}
