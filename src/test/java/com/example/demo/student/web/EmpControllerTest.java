package com.example.demo.student.web;

import com.example.demo.student.Emp;
import com.example.demo.student.persistence.DeptRepository;
import com.example.demo.student.persistence.EmpRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(EmpController.class)
class EmpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmpRepository empRepository;

    @MockBean
    DeptRepository deptRepository;

    @DisplayName("EMP 등록 하기")
    @Test
    void createRequest() throws Exception {

        when(empRepository.save(any())).thenReturn(Emp.builder().empNo("9999").build());

        EmpRequest empRequest = new EmpRequest("9999", "이상국",44, "A001");

        System.out.println(empRepository);
        mockMvc.perform(post("/emp")
                    .content(objectMapper.writeValueAsString(empRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empNo").value("9999"));
    }

    @Test
    void find() throws Exception {
        EmpResponse empResponse1 = new EmpResponse("0001", "이상국", "인사팀");
        EmpResponse empResponse2 = new EmpResponse("0002", "김상국", "인사팀");
        when(empRepository.findByAgeAndRegion(any(), any()))
                .thenReturn(List.of(empResponse1, empResponse2));

        mockMvc.perform(get("/emp/where")
                        .param("age", "42")
                        .param("region", "서울"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].empId").value("0001"));
    }
}