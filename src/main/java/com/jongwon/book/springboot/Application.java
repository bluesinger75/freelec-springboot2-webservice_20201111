package com.jongwon.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 메인 클래스
// @SpringBootApplication 으로 인해 스프링 부트의 자동 설정, 스프링Bean 일기와 생성을 모두 자동으로 설정됩니다.
// 특히나 @SpringBootApplication 이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 합니다.
@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
// main 메소드에서 실행하는 SpringApplication.run 으로 인해 내장 WAS 를 실행합니다.
        SpringApplication.run(Application.class, args);
    }
}
