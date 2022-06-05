package ru.bashsu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bashsu.jpa.entity.Poster;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosterDto {

    private Long id;
    private String name;
    private String date;
    private String time;
    private Integer cost;
    private String location;
    private String reasonCancellation;

    public PosterDto(Poster p) {
        this.id = p.getId();
        this.name = p.getName();
        this.cost = p.getCost();
        this.location = p.getLocation();
        this.reasonCancellation = p.getReasonCancellation();
        this.date = p.getDate().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
        this.time = p.getTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
