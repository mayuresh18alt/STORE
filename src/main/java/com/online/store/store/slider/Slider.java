package com.online.store.store.slider;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sliders")
public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
    @NotNull
    private String sname;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
