package br.victor.clientes.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convert(String value) {
        try {
            return new BigDecimal(value.replace(".", "").replace(",", "."));
        } catch (Exception e) {
            return null;
        }
    }
}
