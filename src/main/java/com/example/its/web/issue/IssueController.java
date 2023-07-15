package com.example.its.web.issue;

import com.example.its.domain.issue.IssueEntity;
import com.example.its.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;


import java.util.List;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute IssueForm issueForm) {
//        model.addAttribute("issueForm", new IssueForm()); <- ハンドラの引数に@ModelAttributeを指定してるので不要
        return "issues/creationFor　m";
    }

    @PostMapping
    public String create(IssueForm form,Model model) {
        issueService.create(form.getSummary(), form.getDescription());
        // return showList(model); <- 二重サブミットされてしまうので、下記のように実装する
        return "redirect:/issues";
    }
}
