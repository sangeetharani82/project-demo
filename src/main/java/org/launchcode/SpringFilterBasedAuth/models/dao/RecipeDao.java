package org.launchcode.SpringFilterBasedAuth.models.dao;

import org.launchcode.SpringFilterBasedAuth.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RecipeDao extends CrudRepository<Recipe, Integer> {
}
