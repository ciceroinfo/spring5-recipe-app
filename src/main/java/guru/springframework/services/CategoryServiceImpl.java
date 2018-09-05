package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Set<Category> getCategories() {
		Set<Category> categories = new HashSet<>();
		categoryRepository.findAll().iterator().forEachRemaining(categories::add);
		return categories;
	}
}
