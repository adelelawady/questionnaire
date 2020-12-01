package app.questionaire.org.service.impl;

import app.questionaire.org.service.QuestionService;
import app.questionaire.org.domain.Question;
import app.questionaire.org.repository.QuestionRepository;
import app.questionaire.org.service.dto.QuestionDTO;
import app.questionaire.org.service.mapper.QuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Question}.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {
        log.debug("Request to save Question : {}", questionDTO);
        Question question = questionMapper.toEntity(questionDTO);
        question = questionRepository.save(question);
        return questionMapper.toDto(question);
    }

    @Override
    public Page<QuestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Questions");
        return questionRepository.findAll(pageable)
            .map(questionMapper::toDto);
    }


    public Page<QuestionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return questionRepository.findAllWithEagerRelationships(pageable).map(questionMapper::toDto);
    }

    @Override
    public Optional<QuestionDTO> findOne(String id) {
        log.debug("Request to get Question : {}", id);
        return questionRepository.findOneWithEagerRelationships(id)
            .map(questionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Question : {}", id);
        questionRepository.deleteById(id);
    }
}
