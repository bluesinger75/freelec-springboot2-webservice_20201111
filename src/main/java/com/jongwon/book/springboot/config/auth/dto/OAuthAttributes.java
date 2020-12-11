package com.jongwon.book.springboot.config.auth.dto;

import com.jongwon.book.springboot.domain.user.Role;
import com.jongwon.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 한다.
    public static OAuthAttributes of(String registrationId, String userNameAttributName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .nameAttributeKey(userNameAttributName)
                .build();
    }
    // User 엔티티를 생성
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}
