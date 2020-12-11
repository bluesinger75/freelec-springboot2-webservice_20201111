package com.jongwon.book.springboot.domain.posts;

import com.jongwon.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드의  Getter 메소드를 자동생성 (롬복)
@NoArgsConstructor // 기본 생성자 자동 추가 public Posts(){} 와 같은 효과 (롬복)
@Entity // ex) SalesManager.java -> sales_manager TABLE
public class Posts extends BaseTimeEntity {
// Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이다. (이런 클래스를 Entity 클래스라고도 한다.)

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 의 생성규칙을 나타냄
    private Long id;
// 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가 해야만 auto_increment 가 된다.

//    테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함 (롬복)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

