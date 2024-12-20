package com.personal.domain.user.service;

import com.personal.common.code.ResponseCode;
import com.personal.common.entity.AuthUser;
import com.personal.common.exception.custom.ForbiddenException;
import com.personal.domain.user.dto.UserAddressRequest;
import com.personal.domain.user.dto.UserAddressResponse;
import com.personal.domain.user.repository.UserAddressRepository;
import com.personal.entity.user.User;
import com.personal.entity.user.UserAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<UserAddressResponse.UserAddress> getUserAddresses(AuthUser authUser) {
        User user = userCommonService.getUserById(authUser.getUserId());
        List<UserAddress> userAddressList = userAddressRepository.findAllByUser(user);
        return userAddressList.stream().map(userAddress -> new UserAddressResponse.UserAddress(userAddress.isRepYN(), userAddress.getZip(), userAddress.getAddress(), userAddress.getAddressDetail())).collect(Collectors.toList());
    }

    @Transactional
    public void setRepresentativeAddress(AuthUser authUser, UserAddressRequest.RepYN repYN) {
        // 사용자 ID 기반으로 주소 가져오기
        User user = userCommonService.getUserById(authUser.getUserId());
        List<UserAddress> userAddressList = userAddressRepository.findAllByUser(user);

        // 모든 주소 repYN를 false 처리
        userAddressList.forEach(address -> address.updateRepYN(false));

        // 특정 주소의 repYN을 true 처리
        UserAddress userAddress = userAddressCommonService.getUserAddress(repYN.userAddressId());
        userAddress.updateRepYN(true);
    }

    @Transactional
    public void deleteUserAddress(AuthUser authUser, Long userAddressId) {
        UserAddress userAddress = userAddressCommonService.getUserAddress(userAddressId);
        if (!Objects.equals(userAddress.getUser().getId(), authUser.getUserId())) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN_ADDRESS_DELETE);
        }

        if (userAddress.isRepYN()) {
            User user = userCommonService.getUserById(authUser.getUserId());
            List<UserAddress> userAddressList = userAddressRepository.findAllByUser(user);
            // 등록된 다른 주소를 대표 주소지로 설정!! (for문으로 돌리는 이유는 아에 없을수도 있기 때문!!)
            for (UserAddress address : userAddressList) {
                if (!address.equals(userAddress)) {
                    address.updateRepYN(true);
                    break;
                }
            }
        }
        // 삭제 처리
        userAddressRepository.delete(userAddress);
    }
}
