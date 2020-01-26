package com.richard.food.api.assembler.restaurante;

import java.util.List;
import java.util.stream.Collectors;

import com.richard.food.api.model.RestauranteModel;
import com.richard.food.api.model.input.RestauranteInput;
import com.richard.food.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler {

    private final ModelMapper modelMapper;

    public RestauranteModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RestauranteModel toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public RestauranteInput toModelInput(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteInput.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
