package ru.bashsu.jpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "poster")
@ApiModel(description = "Афиша")
public class Poster {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Наименование мероприятия")
    private String name;

    @Column(name = "date", nullable = false)
    @ApiModelProperty(value = "Дата мероприятия")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @ApiModelProperty(value = "Время мероприятия")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @Column(name = "cost", nullable = false)
    @ColumnDefault(value = "0")
    @ApiModelProperty(value = "Стоимость мероприятия")
    private Integer cost;

    @Column(name = "location", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Место мероприятия")
    private String location;

    @Column(name = "reason_cancellation", columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "Причина отмены мероприятия")
    private String reasonCancellation;
}
