package com.ychun.account.repo;

import com.ychun.account.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Users, String> {
    List<Users> findAll();

    Users findUserByUsername(String name);

}
