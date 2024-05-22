package com.pillgood.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TokenStore {

    // 토큰을 저장할 리스트
    private List<String> tokens;

    // 생성자
    public TokenStore() {
        this.tokens = new ArrayList<>();
    }

    // 토큰 리스트를 받아서 내부 리스트에 저장하는 메서드
    public void addTokens(List<String> tokens) {
        if (tokens != null) {
            this.tokens.addAll(tokens);
        }
    }

    // 특정 키 값을 가진 토큰을 찾는 메서드
    public Optional<String> findToken(String token) {
        return tokens.stream()
                     .filter(t -> t.equals(token))
                     .findFirst();
    }
}