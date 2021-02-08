package backend.controller;

import backend.domain.Hashtag;
import lombok.Data;

import java.util.List;

// 챌린지 생성
// TODO: 인증샷 예시, 챌린지 썸네일 기능 추가시 변경 필요
@Data
public class ChallengeRequest {
    String challengeName; // 챌린지 이름
    String challengeDesc; // 챌린지 설명
    String userEmail;   // 챌린지 개설자
    String categoryName; // 선택한 카테고리 이름
    String authWay; // 인증 방법
    String authFrequency; // 인증 횟수
    boolean authHoliday; // 공휴일 인증 여부
    String authStarttime; // 인증 가능 시작 시간
    String authEndtime; // 인증 가능 종료 시간
    String challengeStartdate; // 챌린지 시작일
    String challengeEnddate; // 챌린지 종료일
    List<Hashtag> hashtags; // 챌린지 해시태그 리스트
}