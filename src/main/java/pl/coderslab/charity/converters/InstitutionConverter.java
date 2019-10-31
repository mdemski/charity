package pl.coderslab.charity.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

@Component
public class InstitutionConverter implements Converter<String, Institution> {

    private InstitutionRepository institutionRepository;

    public InstitutionConverter(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public InstitutionConverter() {
    }

    @Override
    public Institution convert(String s) {
        return (Institution) institutionRepository.findById(Long.parseLong(s)).get();
    }
}
