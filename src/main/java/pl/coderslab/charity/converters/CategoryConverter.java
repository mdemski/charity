package pl.coderslab.charity.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.Category;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.repositories.CategoryRepository;
@Component
public class CategoryConverter implements Converter<String, Category> {

    private CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryConverter() {
    }

    @Override
    public Category convert(String s) {
        return (Category) categoryRepository.findById(Long.parseLong(s)).get();
    }
}
