package pl.coderslab.charity.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.Optional;

@Component
public class InstitutionConverter implements Converter<Long, Optional<Institution>> {

    private InstitutionRepository<pl.coderslab.charity.model.AbstractEntity, Number> institutionRepository;

    public InstitutionConverter(InstitutionRepository<pl.coderslab.charity.model.AbstractEntity, Number> institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public InstitutionConverter() {
    }


    @Override
    public Optional<Institution> convert(Long aLong) {
        return institutionRepository.findById(aLong);
    }
}
