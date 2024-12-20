package com.personal.domain.user.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.NotFoundException;
import com.personal.domain.user.repository.UserRepository;
import com.personal.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCommonService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_USER));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_USER));
    }
}
