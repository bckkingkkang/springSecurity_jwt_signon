package com.example.demo.member.controller

import com.example.demo.member.dto.MemberDto
import com.example.demo.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class MemberController(private val memberService: MemberService) {
    /* 회원 가입 */
    @PostMapping("/signup")
    fun signup(@RequestBody memberDto: MemberDto) : String {
        return memberService.signup(memberDto)
    }
}