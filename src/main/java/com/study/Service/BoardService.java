package com.study.Service;

import com.study.Repository.BoardRepository;
import com.study.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void register(Board board) {
        boardRepository.save(board);
    }

    public Page<Board> list(int page) {
        return boardRepository.findAll(PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "idx")));
    }

    public Board detail(int idx) {
        return boardRepository.findById(idx).orElse(null);
    }

    public void update(Board board) {
        boardRepository.save(board);
    }

    public void delete(int idx) {
        boardRepository.deleteById(idx);
    }
}