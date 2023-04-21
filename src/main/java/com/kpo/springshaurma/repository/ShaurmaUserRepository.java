package com.kpo.springshaurma.repository;

import com.kpo.springshaurma.model.ShaurmaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShaurmaUserRepository extends JpaRepository<ShaurmaUser, UUID> {

  Optional<ShaurmaUser> findByVkId(long vkId);

  Optional<ShaurmaUser> findByEmail(String email);

  Optional<ShaurmaUser> findByAppleId(String appleId);

  Optional<ShaurmaUser> findByGoogleId(String googleId);

  Optional<ShaurmaUser> findByDisplayName(String name);

  List<ShaurmaUser> findAllByActiveIsTrueAndBanIsFalse();
}
