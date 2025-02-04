package com.membermap.repository;

import com.membermap.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 지역별 회원 수를 집계하는 쿼리
    @Query("SELECT m.region AS region, COUNT(m) AS count FROM Member m GROUP BY m.region")
    List<RegionCount> countMembersByRegion();

    // Projection 인터페이스 정의
    interface RegionCount {
        String getRegion();
        Long getCount();
    }
}
