package com.example.demo.student.web;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpRequest(

        @NotBlank(message = "사번은 필수 입니다.")
        String empno,
        @NotBlank(message = "이름은 필수 입니다. ")
        String name,
        @Min(value = 20, message = "나이는 20 이상 이어야 합니다.")
        @Max(value = 60, message = "나이는 60이하어야 합니다.")
        int age,
        @NotBlank
        String deptcode) {
}
