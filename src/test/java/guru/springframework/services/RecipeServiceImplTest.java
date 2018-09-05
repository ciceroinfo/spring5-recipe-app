package guru.springframework.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void getRecipeByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Optional<Recipe> optionalRecipe = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

		Recipe recipeReturned = recipeService.findById(1L);

		assertNotNull("Null recipe returned", recipeReturned);

		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	public void getRecipesTest() throws Exception {
		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(recipe);

		when(recipeService.getRecipes()).thenReturn(recipesData);

		Set<Recipe> recipes = recipeService.getRecipes();

		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
		verify(recipeRepository, never()).findById(anyLong());
	}
}