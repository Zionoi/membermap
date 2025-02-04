package com.membermap.controller;

import com.membermap.repository.MemberRepository.RegionCount;
import com.membermap.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // GET /api/members/distribution
    // 지역별 회원 분포(예: { region: "서울", count: 50 }) 데이터를 반환
    @GetMapping("/distribution")
    public ResponseEntity<List<RegionCount>> getMemberDistribution() {
        List<RegionCount> distribution = memberService.getMemberDistribution();
        return ResponseEntity.ok(distribution);
    }
}
