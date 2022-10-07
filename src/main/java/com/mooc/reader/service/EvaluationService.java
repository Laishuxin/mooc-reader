package com.mooc.reader.service;

import com.mooc.reader.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    Evaluation enjoy(Long evaluationId);

    List<Map> selectByBookId(Long bookId);

    Evaluation addEvaluation(Long memberId, Long bookId, Integer score, String content);
}
