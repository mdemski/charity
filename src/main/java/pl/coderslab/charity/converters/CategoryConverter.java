package pl.coderslab.charity.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.Category;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.repositories.CategoryRepository;

import java.util.Optional;

@Component
public class CategoryConverter implements Converter<Long, Optional<Category>> {

    private CategoryRepository<pl.coderslab.charity.model.AbstractEntity, Number> categoryRepository;

    public CategoryConverter(CategoryRepository<pl.coderslab.charity.model.AbstractEntity, Number> categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryConverter() {
    }

    @Override
    public Optional<Category> convert(Long s) {
        return categoryRepository.findById(s);
    }
}
