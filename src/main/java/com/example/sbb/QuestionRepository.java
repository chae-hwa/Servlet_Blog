package com.example.sbb;

import com.example.sbb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer>, RepositoryUtil {

    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String s);

    @Transactional
    @Modifying
    @Query(value = "truncate question", nativeQuery = true)
    void truncate();

}