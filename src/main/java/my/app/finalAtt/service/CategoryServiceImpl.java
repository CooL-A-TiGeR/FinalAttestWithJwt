package my.app.finalAtt.service;

import my.app.finalAtt.model.Category;
import my.app.finalAtt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void create(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> readAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category read(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public int update(Category category, long id) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            categoryRepository.save(category);
            return 1;
        }
        return -1;
    }

    @Override
    public boolean delete(long id) {
        return categoryRepository.deleteById(id);
    }
}
