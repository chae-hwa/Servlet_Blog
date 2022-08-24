package com.example.sbb.question;

import com.example.sbb.DataNotFoundException;
import com.example.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(String kw, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));

        if ( kw == null || kw.trim().length() == 0 ) { // 파라미터를 받지 않을 땐 전체 질문 리스트 출력
            return questionRepository.findAll(pageable);
        }

        return questionRepository.findBySubjectContains(kw, pageable);
    }

    public Question getQuestion(long id){
        Optional<Question> oq = this.questionRepository.findById(id);
        if(oq.isPresent()){
            return oq.get();
        } else{
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUser author){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(author);
        questionRepository.save(q);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);

        questionRepository.save(question);
    }
}
