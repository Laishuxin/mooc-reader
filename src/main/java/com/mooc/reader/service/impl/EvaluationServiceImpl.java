package com.mooc.reader.service.impl;

import com.mooc.reader.entity.Evaluation;
import com.mooc.reader.mapper.EvaluationMapper;
import com.mooc.reader.service.EvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    private EvaluationMapper evaluationMapper;


    @Override
    public List<Map> selectByBookId(Long bookId) {
        return evaluationMapper.selectByBookId(bookId);
    }

    @Override
    public Evaluation addEvaluation(Long memberId, Long bookId, Integer score, String content) {
        Evaluation evaluation = new Evaluation(content, score, memberId, bookId);
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    @Override
    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        if (evaluation != null) {
            evaluation.setEnjoy(evaluation.getEnjoy() + 1);
            evaluationMapper.updateById(evaluation);
            return  evaluation;
        }
        return null;
    }
}
