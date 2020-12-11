package com.jongwon.book.springboot.config.auth;

import com.jongwon.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화시켜 줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // URL별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값들 이외의 나머지 URL들을 나타냄
                .anyRequest().authenticated()
                .and()
                .logout()
                // 로그아웃 성공 시 / 주소로 이동
                .logoutSuccessUrl("/")
                .and()
                // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .oauth2Login()
                .userInfoEndpoint()
                // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                .userService(customOAuth2UserService);
    }
}
