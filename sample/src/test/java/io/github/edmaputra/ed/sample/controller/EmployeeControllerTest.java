package io.github.edmaputra.ed.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataEmptyException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.sample.DataInit;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.service.EmployeeService;
import io.github.edmaputra.ed.edbase.util.ResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {EmployeeController.class})
class EmployeeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  EmployeeService employeeService;

  Page<Employee> employeePage;

  Employee e0, e1, e2, e3;

  @BeforeEach
  void init() {
    DataInit data = new DataInit();
    e0 = data.getEmployee(0);
    e1 = data.getEmployee(1);
    e2 = data.getEmployee(2);
    e3 = data.getEmployee(3);
    employeePage = new PageImpl<>(List.of(e0, e1, e2, e3), Pageable.unpaged(), 4);
  }

  @Test
  void givenAnyPageable_whenGet_thenResponseOK() throws Exception {
    doReturn(employeePage).when(employeeService).get(any(Pageable.class));

    MvcResult mvcResult = mockMvc.perform(get("/employees"))
        .andExpect(status().isOk())
        .andReturn();

    verify(employeeService, times(1)).get(any(Pageable.class), anyString());
  }

  @Test
  void givenNullPageable_whenGet_thenThrowCrudOperationExceptionK() throws Exception {
    CrudOperationException e = new CrudOperationException("Pageable is Null");
    doThrow(e).when(employeeService).get(any(Pageable.class), anyString());

    MvcResult mvcResult = mockMvc.perform(get("/employees"))
        .andExpect(status().isBadRequest()).andReturn();

    verify(employeeService, times(1)).get(any(Pageable.class), anyString());
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse(e, HttpStatus.BAD_REQUEST).getBody());
    assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(expectedResponse);
  }

  @Test
  void givenAnyPageable_whenGet_thenThrowDataEmptyExceptionK() throws Exception {
    DataEmptyException e = new DataEmptyException("Data is Empty");
    doThrow(e).when(employeeService).get(any(Pageable.class), anyString());

    MvcResult mvcResult = mockMvc.perform(get("/employees"))
        .andExpect(status().isNotFound()).andReturn();


    verify(employeeService, times(1)).get(any(Pageable.class), anyString());
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse(e, HttpStatus.NOT_FOUND).getBody());
    assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(expectedResponse);
  }

  @Test
  void givenEmployeeId_whenFind_thenReturnExpectedObjectAndResponse() throws Exception {
    doReturn(e3).when(employeeService).getOne(anyLong());

    MvcResult mvcResult = mockMvc.perform(get("/employees/3"))
        .andExpect(status().isOk())
        .andReturn();

    ArgumentCaptor<Long> acId = ArgumentCaptor.forClass(Long.class);
    verify(employeeService, times(1)).getOne(acId.capture());
    assertThat(acId.getValue()).isEqualTo(3);

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(objectMapper.writeValueAsString(e3)).isEqualToIgnoringWhitespace(actualResponseBody);
  }

  @Test
  void givenEmployeeId_whenFind_thenThrowDataNotFoundException() throws Exception {
    DataNotFoundException ex = new DataNotFoundException("Data Not Found");
    doThrow(ex).when(employeeService).getOne(anyLong());

    MvcResult mvcResult = mockMvc.perform(get("/employees/3"))
        .andExpect(status().isNotFound())
        .andReturn();

    verify(employeeService, times(1)).getOne(anyLong());
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse(ex, HttpStatus.NOT_FOUND).getBody());
    assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(expectedResponse);
  }

  @Test
  void givenNullEmployeeId_whenFind_thenArgumentTypeMismatchException() throws Exception {
    mockMvc.perform(get("/employees/null"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void givenEmployee_whenAdd_thenReturn201() throws Exception {
    doReturn(e0).when(employeeService).add(any(Employee.class));

    MvcResult mvcResult = mockMvc.perform(post("/employees")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(e0)))
        .andExpect(status().isCreated())
        .andReturn();

    ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
    verify(employeeService, times(1)).add(employeeArgumentCaptor.capture());
    assertThat(employeeArgumentCaptor.getValue().getFirstName()).isEqualTo(e0.getFirstName());

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(objectMapper.writeValueAsString(e0)).isEqualToIgnoringWhitespace(actualResponseBody);
  }

  @Test
  void givenEmployeeWithErrorValidation_whenAdd_thenThrowConstaintViolationException() throws Exception {
    e0.setFirstName("a");
    MvcResult mvcResult = mockMvc.perform(post("/employees")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(e0)))
        .andExpect(status().isBadRequest())
        .andReturn();

  }

  @Test
  void givenEmployee_whenEdit_thenReturn200() throws Exception {
    e0.setId(1L);
    doReturn(e1).when(employeeService).update(any(Employee.class));

    MvcResult mvcResult = mockMvc.perform(put("/employees")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(e0)))
        .andExpect(status().isOk())
        .andReturn();

    ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
    verify(employeeService, times(1)).update(employeeArgumentCaptor.capture());
    assertThat(employeeArgumentCaptor.getValue().getFirstName()).isEqualTo(e0.getFirstName());

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(objectMapper.writeValueAsString(e1)).isEqualToIgnoringWhitespace(actualResponseBody);
  }

  @Test
  void givenEmployee_whenDelete_thenReturn200() throws Exception {
    e0.setId(1L);
    doReturn(e2).when(employeeService).delete(any(Employee.class));

    MvcResult mvcResult = mockMvc.perform(delete("/employees")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(e0)))
        .andExpect(status().isOk())
        .andReturn();

    ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
    verify(employeeService, times(1)).delete(employeeArgumentCaptor.capture());
    assertThat(employeeArgumentCaptor.getValue().getFirstName()).isEqualTo(e0.getFirstName());

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(objectMapper.writeValueAsString(e2)).isEqualToIgnoringWhitespace(actualResponseBody);
  }

}
