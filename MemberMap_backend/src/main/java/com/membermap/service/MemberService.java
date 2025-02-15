package com.membermap.service;

import com.membermap.repository.MemberRepository;
import com.membermap.repository.MemberRepository.RegionCount;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 지역별 회원 수 집계 반환
    public List<RegionCount> getMemberDistribution() {
        return memberRepository.countMembersByRegion();
    }
    
    // 필요에 따라 회원 추가, 수정, 삭제 등의 메소드도 구현
}
