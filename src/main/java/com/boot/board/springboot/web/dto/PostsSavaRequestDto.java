package com.boot.board.springboot.web.dto;

import com.boot.board.springboot.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSavaRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSavaRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
