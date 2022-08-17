package com.example.sbb.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class AnswerForm {

    @NotEmpty(message = "내용을 필수 항목입니다.")
    private String content;
}
