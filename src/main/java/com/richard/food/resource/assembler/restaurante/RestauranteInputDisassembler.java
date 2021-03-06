package com.richard.food.resource.assembler.restaurante;

import com.richard.food.domain.model.Cidade;
import com.richard.food.domain.model.Cozinha;
import com.richard.food.domain.model.Restaurante;
import com.richard.food.resource.model.input.RestauranteInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class RestauranteInputDisassembler {

    private final ModelMapper modelMapper;

    public RestauranteInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.richard.food.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        if (nonNull(restaurante.getEndereco())) {
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput, restaurante);
    }
}
