package com.example.demo.member.service

import com.example.demo.member.dto.MemberDto
import com.example.demo.member.entity.Member
import com.example.demo.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(val memberRepository: MemberRepository) {
    /* 회원 가입 */
    fun signup(memberDto: MemberDto) : String {
        var member: Member? = memberRepository.findByLoginId(memberDto.loginId)

        if(member != null) {
            return "이미 등록된 ID입니다."
        }

        member = Member(
            null,
            memberDto.loginId,
            memberDto.password,
            memberDto.name,
            memberDto.birthDate,
            memberDto.gender,
            memberDto.email,
        )
        memberRepository.save(member)

        return "회원가입이 완료되었습니다."
    }
}