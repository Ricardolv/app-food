package com.richard.food.core.validation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

    private BindingResult bindingResult;

}
