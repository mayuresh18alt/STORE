package com.online.store.store.slider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class SliderServiceImpl implements SliderService {

    @Autowired
    private SliderRepository sliderRepository;


    @Override
    public Slider addProduct(Slider product, MultipartFile file) throws IOException {
        byte[] imageData = file.getBytes();
        product.setImage(imageData);
        return sliderRepository.save(product);
    }

//    @Override
//    public Page<SliderDTO> getSliders(Pageable pageable) {
//        Page<Slider> productPage = sliderRepository.findAll(pageable);
//        List<SliderDTO> sliderDTOs = sliderRepository.findAll(pageable)
//                .getContent().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList()); //productPage.stream()
//
//        return new PageImpl<>(sliderDTOs, pageable, sliderRepository.count());
//    }
//
//    private SliderDTO convertToDTO(Slider product) {
//        SliderDTO sliderDTO = new SliderDTO();
//        sliderDTO.setSid(sliderRepository.count() + 1);
//        sliderDTO.setSname(product.getSname());
//
//        // Convert the byte array to a Base64 encoded string
//        String base64Image = Base64.getEncoder().encodeToString(product.getImage());
//        sliderDTO.setImage(base64Image);
//
//        return sliderDTO;
//    }

    public Page<SliderDTO> getSliders(Pageable pageable) {
        return sliderRepository.findAll(pageable).map(this::convertToDTO);
    }

    private SliderDTO convertToDTO(Slider slider) {
        SliderDTO sliderDTO = new SliderDTO();
        sliderDTO.setSid(slider.getSid());
        sliderDTO.setSname(slider.getSname());
        sliderDTO.setImage(Base64.getEncoder().encodeToString(slider.getImage()));
        // map other fields as necessary
        return sliderDTO;
    }

    @Override
    public Slider updateSlider(Slider product, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Convert the file to a byte array and update the product's image
            byte[] imageBytes = file.getBytes();
            product.setImage(imageBytes);
        }
        // Save the product in the database
        return sliderRepository.save(product);
    }

    public Slider updateProduct(Slider product) {
        // Update other fields without modifying the image
        return sliderRepository.save(product);
    }


    @Override
    public Slider deleteSlider(Slider product) {
        if(sliderRepository.existsById(product.getSid())){
            sliderRepository.delete(product);
            return product;
        }else{
            return null;
        }
    }

    @Override
    public Slider getSliderById(Long id) {
        return sliderRepository.findById(id).get();
    }
}