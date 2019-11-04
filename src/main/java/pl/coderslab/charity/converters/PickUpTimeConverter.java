package pl.coderslab.charity.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class PickUpTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        return LocalTime.parse(s);
    }
}
