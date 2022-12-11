package com.boot.board.springboot.service.posts;


import com.boot.board.springboot.domain.Posts;
import com.boot.board.springboot.domain.PostsRepository;
import com.boot.board.springboot.web.dto.PostsListResponseDto;
import com.boot.board.springboot.web.dto.PostsResponseDto;
import com.boot.board.springboot.web.dto.PostsSavaRequestDto;
import com.boot.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //등록
    @Transactional
    public Long save(PostsSavaRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).
                getId();
    }

    //수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    //조회
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    //전체 조회
    @Transactional(readOnly = true) // readOnly - 조회 기능만 남겨두어 조회 속도가 개선됨
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    //삭제
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        postsRepository.delete(posts);
    }
}
