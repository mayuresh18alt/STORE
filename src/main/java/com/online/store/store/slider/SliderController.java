package com.online.store.store.slider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/slider")
public class SliderController {

    @Autowired
    private SliderService sliderService;

    @PostMapping("/add")
    ResponseEntity<?>addProduct(
                        @ModelAttribute("sname") String sname,
                        @RequestParam("image") MultipartFile file) throws IOException {

        Slider product = new Slider();
        product.setSname(sname);
        product = sliderService.addProduct(product, file);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Page<SliderDTO> getSliders(Pageable pageable) {
        return sliderService.getSliders(pageable);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                    @ModelAttribute("sname") String sname,
                                    @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {

        Slider product = sliderService.getSliderById(id);
        product.setSname(sname);

        // Update the image only if a new one is provided
        if (file != null && !file.isEmpty()) {
            product = sliderService.updateSlider(product, file);
        } else {
            product = sliderService.updateSlider(product, null);
        }

        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?>deleteProduct(@PathVariable("id") Long id){
        sliderService.deleteSlider(sliderService.getSliderById(id));
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }


}