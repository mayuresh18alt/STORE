package com.online.store.store.slider;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SliderService {
    Slider addProduct(Slider product, MultipartFile file) throws IOException;

    Page<SliderDTO> getSliders(Pageable pageable);

    Slider updateSlider(Slider product, MultipartFile file) throws IOException;

    Slider deleteSlider(Slider product);

    Slider getSliderById(Long id);
}
