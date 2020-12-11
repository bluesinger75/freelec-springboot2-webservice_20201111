package com.jongwon.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없으면 H2 테이터베이스를 자동으로 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Junit 에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        // 테이블 posts 에 insert/update 쿼리를 실행
        // id 가 있으면 update 없으면 insert 쿼리가 실행
        postsRepository.save(Posts.builder()
                                            .title(title)
                                            .content(content)
                                            .author("bluesinger75@gmail.com")
                                            .build());
        // when
        // 테이블 posts 에 있는 모든 데이터를 조회
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2020,11,17,0,0,0);
        postsRepository.save(Posts.builder()
                                    .title("title")
                                    .content("content")
                                    .author("author")
                                    .build());
        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>> createDate="+posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
