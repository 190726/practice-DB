package com.example.demo.student.web;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;

public record EmpRequest(

        @NotBlank(message = "사번은 필수 입니다.")
        @Size(min = 4, message = "사번은 4자리 이상 이어야 합니다")
        String empno,
        @NotBlank(message = "이름은 필수 입니다. ")
        @Size(min=2, max=10, message = "이름 길이는 2자 이상 10자 이하여야 합니다.")
        String name,
        @Min(value = 20, message = "나이는 20 이상 이어야 합니다.")
        @Max(value = 60, message = "나이는 60이하어야 합니다.")
        int age,
        @NotBlank
        String deptcode) {
}
