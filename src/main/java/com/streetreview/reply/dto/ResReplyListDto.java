package com.streetreview.reply.dto;

import com.streetreview.member.dto.MemberProfileDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResReplyListDto {
    private Long replyId;
    private String replyContent;
    private List<String> photoList;
    private int replyLikey;
    private MemberProfileDto member;
    private Timestamp createDate;
    private Timestamp updateDate;

    @Builder
    public ResReplyListDto(Long replyId, String replyContent, List<String> photoList, int replyLikey, MemberProfileDto member, Timestamp createDate, Timestamp updateDate) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.member = member;
        this.photoList = photoList;
        this.replyLikey = replyLikey;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
