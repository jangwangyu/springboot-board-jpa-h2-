package com.study.Controller;

import com.study.Service.BoardService;
import com.study.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {

        Page<Board> listPage = boardService.list(page); // 불러올 페이지의 데이터 1페이지는 0부터 시작

        int totalPage = listPage.getTotalPages(); // 총 페이지 수

        model.addAttribute("board", listPage.getContent());
        model.addAttribute("totalPage", totalPage);
        return "board/list";
    }

    @GetMapping("/detail/{idx}")
    public String detail(@PathVariable int idx, Model model) {

        model.addAttribute("board", boardService.detail(idx));
        return "board/detail";
    }

    @GetMapping("/register")
    public String registerGet() {

        return "board/register";
    }

    @PostMapping("/register")
    public String registerPost(Board board) {

        boardService.register(board);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{idx}")
    public String updateGet(@PathVariable int idx, Model model) {

        model.addAttribute("board", boardService.detail(idx));
        return "board/update";
    }

    @PostMapping("/update")
    public String updatePost(Board board) {

        boardService.update(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable int idx) {

        boardService.delete(idx);
        return "redirect:/board/list";
    }
}