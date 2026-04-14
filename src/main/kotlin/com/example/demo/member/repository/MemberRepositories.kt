package com.example.demo.member.repository

import com.example.demo.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

// Member 테이블을 다루는 DAO를 자동으로 만들어줌
interface MemberRepository : JpaRepository<Member, Long> {
    // 회원가입 시 ID 중복 검사를 위해 필요
    // SELECT * FROM member WHERE login_id = ?
    fun findByLoginId(loginId: String): Member?
}