package com.streetreview.reply.entity;


import com.streetreview.member.entity.Member;
import com.streetreview.review.entity.Review;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@DynamicUpdate
@DynamicInsert
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
@EntityListeners(AuditingEntityListener.class)
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", unique = true, nullable = false)
    private Long replyId;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_photo")
    private String replyPhoto;

    @Column(name = "reply_likey")
    private int replyLikey;

    @CreatedDate
    private Timestamp createDate;
    //카멜 표기법

    @LastModifiedDate
    private Timestamp updateDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;


    @Builder
    public Reply(Long replyId, String replyContent, String replyPhoto, int replyLikey, Timestamp createDate, Timestamp updateDate) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyPhoto = replyPhoto;
        this.replyLikey = replyLikey;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void addReview(Review review) {
        this.review = review;
    }

    public void writeBy(Member member) {
        this.member = member;
    }
}