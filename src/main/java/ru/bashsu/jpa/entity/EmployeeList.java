package ru.bashsu.jpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Студенты которым идёт отправка")
public class EmployeeList implements Serializable {

    @ApiModelProperty(value = "Студенты")
    List<Employee> employees = new ArrayList<>();
}
