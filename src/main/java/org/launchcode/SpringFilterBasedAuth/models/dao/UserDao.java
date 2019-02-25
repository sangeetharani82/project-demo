package org.launchcode.SpringFilterBasedAuth.models.dao;

import org.launchcode.SpringFilterBasedAuth.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
