package guru.springframework.services;

import java.util.Set;

import guru.springframework.domain.Category;

public interface CategoryService {
	Set<Category> getCategories();
}
