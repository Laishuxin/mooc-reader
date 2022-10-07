package com.mooc.reader.controller;

import com.mooc.reader.dto.AddEvaluationDto;
import com.mooc.reader.dto.EvaluationEnjoyDto;
import com.mooc.reader.entity.Evaluation;
import com.mooc.reader.service.EvaluationService;
import com.mooc.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;

    @GetMapping("/list")
    public ResponseUtils list(Long bookId) {
        ResponseUtils res = new ResponseUtils();
        try {
            List<Map> maps = evaluationService.selectByBookId(bookId);
            res.put("list", maps);
        } catch (Exception e) {
            res = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return res;
    }

    @PostMapping("/add")
    public ResponseUtils addEvaluation(@RequestBody AddEvaluationDto body) {
        try {
            Evaluation evaluation = evaluationService.addEvaluation(body.getMemberId(), body.getBookId(),
                    body.getScore()
                    , body.getContent());
            return new ResponseUtils().put("evaluation", evaluation);
        } catch (Exception e) {
            return new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @PostMapping("/enjoy")
    public ResponseUtils enjoy(@RequestBody EvaluationEnjoyDto body) {
        try {
            Evaluation evaluation = evaluationService.enjoy(body.getEvaluationId());
            return new ResponseUtils().put("evaluation", evaluation);
        } catch (Exception e) {
            return ResponseUtils.error(e);
        }
    }

}
