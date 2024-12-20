package com.personal.domain.user.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.NotFoundException;
import com.personal.domain.user.repository.UserAddressRepository;
import com.personal.entity.user.UserAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAddressCommonService {

    private final UserAddressRepository userAddressRepository;

    public UserAddress getRepUserAddress(long id) {
        return userAddressRepository.findByIdAndRepYNTrue(id).orElse(new UserAddress());
    }

    public UserAddress getUserAddress(long id) {
        return userAddressRepository.findById(id).orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_USER_ADDRESS));
    }
}
