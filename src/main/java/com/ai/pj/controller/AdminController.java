package com.ai.pj.controller;


import com.ai.pj.domain.User;
import com.ai.pj.dto.BoardDTO;
import com.ai.pj.dto.UserDTO;
import com.ai.pj.service.AdminService;
import com.ai.pj.service.BoardService;
import com.ai.pj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final BoardService boardService;


    @GetMapping
    public String redirectToSlash() {
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String reqAdmin(Model model, Authentication authentication) {
        // 관리자 메모 ?
        // 현 관리자 이름
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("managerName", userDetails.getUsername());

        // 회원 현황
        List<UserDTO.Get> allUserList = adminService.getAllUsers();
        model.addAttribute("allUserList", allUserList);

        // 게시판 현황
        List<BoardDTO.Get> allBoardList = boardService.getAllBoards();
        model.addAttribute("allBoardList",allBoardList);

        // role = HOLD로 갖고 있는 USER 목록 및 USER 관련 companyName
        List<UserDTO.Get> userList = adminService.findByRole(User.UserRole.HOLD);
        model.addAttribute("userList", userList);

        return "admin/index";
    }
}
