package com.streetreview.mypage.controller;

import com.streetreview.common.dto.Message;
import com.streetreview.member.dto.MemberProfileDto;
import com.streetreview.member.handler.StatusCode;
import com.streetreview.mypage.service.MyPageService;
import com.streetreview.reply.dto.ResReplyListDto;
import com.streetreview.review.dto.ResReviewListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.streetreview.member.security.JwtInfoExtractor.getStrvMember;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/review")
    public ResponseEntity<Message> getMyReviews() {
        List<ResReviewListDto> myReviews = myPageService.getMyReview(getStrvMember());

        return ResponseEntity.ok(new Message(StatusCode.OK, myReviews));
    }

    @GetMapping("/reply")
    public ResponseEntity<Message> getMyReplies() {
        List<ResReplyListDto> myReplies = myPageService.getMyReply(getStrvMember());
        return ResponseEntity.ok(new Message(StatusCode.OK, myReplies));
    }


    @GetMapping("/member")
    public ResponseEntity<Message> getMemberProfile() {
        MemberProfileDto memberProfileDto = myPageService.getMemberProfile(getStrvMember());
        Message message = new Message(StatusCode.OK, memberProfileDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/reviewLike") //내가 좋아요 누른 리뷰
    public ResponseEntity<Message> getMyReviewLikes() {
        List<ResReviewListDto> myReviewLikes = myPageService.getMyReviewLikes(getStrvMember());
        return ResponseEntity.ok(new Message(StatusCode.OK, myReviewLikes));
    }
}

