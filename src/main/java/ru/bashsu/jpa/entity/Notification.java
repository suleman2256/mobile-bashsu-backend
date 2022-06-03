package ru.bashsu.jpa.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "notification")
@ApiModel(description = "Студенты")
public class Notification {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    @ApiModelProperty(value = "Наименование оповещения")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    @ApiModelProperty(value = "Описание оповещения")
    private String description;

    @Type(type = "jsonb")
    @Column(name = "employees", columnDefinition = "jsonb", nullable = false)
    @JsonUnwrapped
    @ApiModelProperty(value = "Студенты которым идёт отправка")
    private EmployeeList employees = new EmployeeList();
}
