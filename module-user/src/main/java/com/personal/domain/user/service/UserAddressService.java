package com.personal.domain.user.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.entity.AuthUser;
import com.personal.common.exception.custom.ForbiddenException;
import com.personal.domain.user.dto.UserAddressRequest;
import com.personal.domain.user.repository.UserAddressRepository;
import com.personal.entity.user.User;
import com.personal.entity.user.UserAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserAddressService {

    private final UserCommonService userCommonService;
    private final UserAddressCommonService userAddressCommonService;
    private final UserAddressRepository userAddressRepository;

    @Transactional
    public void addUserAddress(AuthUser authUser, UserAddressRequest.UserAddress address) {
        User user = userCommonService.getUserById(authUser.getUserId());
        boolean check = userAddressRepository.existsByUserAndRepYNTrue(user , true);
        boolean repYN = !check;
        UserAddress userAddress = UserAddress.builder()
                .repYN(repYN)
                .zip(address.zip())
                .address(address.address())
                .addressDetail(address.addressDetail())
                .user(user)
                .build();
        userAddressRepository.save(userAddress);
    }

    @Transactional
    public void modUserAddress(AuthUser authUser , Long userAddressId , UserAddressRequest.UserAddress address) {
        UserAddress userAddress = userAddressCommonService.getUserAddress(userAddressId);
        if (!Objects.equals(userAddress.getUser().getId(), authUser.getUserId())) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN_ADDRESS_UPDATE);
        }
        userAddress.updateAddress(address.zip() , address.address() , address.addressDetail());
    }
}
