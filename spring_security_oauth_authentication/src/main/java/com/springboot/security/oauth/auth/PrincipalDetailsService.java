package com.springboot.security.oauth.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.oauth.domain.entity.Member;
import com.springboot.security.oauth.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@Service    //반드시 기재해주자!!! loginProcessingUrl이 동작하지 않는다.
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(username).orElse(null);
        if(findMember != null){
            return new PrincipalDetails(findMember);
        }
        return null;
    }
}
