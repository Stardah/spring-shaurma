package com.kpo.springshaurma.service;

import com.kpo.springshaurma.api.models.ResponseStatus;
import com.kpo.springshaurma.api.models.ShaurmaUserObject;
import com.kpo.springshaurma.api.models.SimpleResponse;
import com.kpo.springshaurma.api.models.reponse.ShaurmaUserResponse;
import com.kpo.springshaurma.mapper.ShaurmaUserMapper;
import com.kpo.springshaurma.model.ShaurmaUser;
import com.kpo.springshaurma.repository.ShaurmaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Lazy
public class ShaurmaUserService {

    private final ShaurmaUserRepository shaurmaUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ShaurmaUserMapper shaurmaUserMapper;

    public ShaurmaUserResponse getById(final UUID id) {
        ShaurmaUser shaurmaUser = shaurmaUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ShaurmaUserResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .body(shaurmaUserMapper.shaurmaUserModel2ShaurmaUserObject(shaurmaUser))
                .build();
    }

    public ShaurmaUser getByEmail(final String email) {
        return shaurmaUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Not found " + email));
    }

    public ShaurmaUser getByNickname(final String nickname) {
        return shaurmaUserRepository.findByDisplayName(nickname)
                .orElseThrow(() -> new RuntimeException("Not found " + nickname));
    }

    public SimpleResponse register(ShaurmaUserObject shaurmaUserObject) {
        ShaurmaUser ShaurmaUser = shaurmaUserMapper.shaurmaUserObject2ShaurmaUserModel(shaurmaUserObject);
        ShaurmaUser.setPassword(bCryptPasswordEncoder.encode(ShaurmaUser.getPassword()));

        shaurmaUserRepository.save(ShaurmaUser);

        return SimpleResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .body(true)
                .build();
    }
}
