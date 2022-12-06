package com.boot.board.springboot.service.posts;

import com.boot.board.springboot.domain.PostsRepository;
import com.boot.board.springboot.web.dto.PostsSavaRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSavaRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).
                                                getId();
    }
}
