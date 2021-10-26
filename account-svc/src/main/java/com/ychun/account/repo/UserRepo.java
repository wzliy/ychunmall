package com.ychun.account.repo;

import com.ychun.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, String> {

    User findUserByUsername(String name);

    User findUsersByUsernameAndPassword(String name, String password);

}
