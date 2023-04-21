package com.kpo.springshaurma.repository;

import com.kpo.springshaurma.model.ShaurmaUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<ShaurmaUser, Long> {

    Optional<ShaurmaUser> findByUsername(String username);

    Optional<ShaurmaUser> findByActivationCode(String code);

    List<ShaurmaUser> findAll();

    Optional<ShaurmaUser> findByAccessTokenAndActive(String accessToken, Boolean active);

    Optional<ShaurmaUser> findByEmail(String email);
}
