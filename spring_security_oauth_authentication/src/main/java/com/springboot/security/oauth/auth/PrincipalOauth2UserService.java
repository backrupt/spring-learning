package com.springboot.security.oauth.auth;


import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.springboot.security.oauth.auth.userinfo.GoogleUserInfo;
import com.springboot.security.oauth.auth.userinfo.KakaoUserInfo;
import com.springboot.security.oauth.auth.userinfo.NaverUserInfo;
import com.springboot.security.oauth.auth.userinfo.Oauth2UserInfo;
import com.springboot.security.oauth.domain.entity.Member;
import com.springboot.security.oauth.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        System.out.println("인증유저" + oAuth2User);
        System.out.println("***********useRequest********");
        System.out.println(userRequest);
        System.out.println("clientRegistration : " +userRequest.getClientRegistration().getClientName());
        System.out.println("clientRegistration : " +userRequest.getClientRegistration().getClientId());
        System.out.println("accestoken : " +userRequest.getAccessToken().getTokenValue());
        System.out.println("additionaparameter : " +userRequest.getAdditionalParameters());
        System.out.println("***********END********");
        Oauth2UserInfo oauth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();    //google 또는 naver

        if(provider.equals("google")){
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(provider.equals("naver")){
            oauth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }else if(provider.equals("kakao")){
            oauth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String email = oauth2UserInfo.getEmail();                        //이메일
        String defaultNickname = oauth2UserInfo.getProvider() + "_user" + UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("password" + UUID.randomUUID().toString().substring(0, 6));
        // 비밀번호. 사용자가 입력한 적은 없지만 만들어준다.
        Member findMember = memberRepository.findByEmail(email).orElse(null);
        //DB에 없는 사용자라면 회원가입처리
        if(findMember == null){
            findMember = Member.JoinOAuth2()
                    .nickname(defaultNickname)
                    .password(password)
                    .email(email)
                    .provider(provider).build();
            memberRepository.save(findMember);
        }

        return new PrincipalDetails(findMember, oauth2UserInfo);
    }
}
