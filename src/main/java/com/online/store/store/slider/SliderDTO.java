package com.online.store.store.slider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SliderDTO {
    private Long sid;
    private String sname;

    private String image; // Base64 encoded string
}

