package com.mooc.reader.controller;

import com.mooc.reader.service.EvaluationService;
import com.mooc.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;

    @GetMapping("/list")
    private ResponseUtils list(Long bookId) {
        ResponseUtils res = new ResponseUtils();
        try {
            List<Map> maps = evaluationService.selectByBookId(bookId);
            res.put("list", maps);
        } catch (Exception e) {
            res = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return res;
    }

}
