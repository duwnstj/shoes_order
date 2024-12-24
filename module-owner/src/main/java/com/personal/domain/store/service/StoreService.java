package com.personal.domain.store.service;

import com.personal.common.entity.AuthUser;
import com.personal.domain.owner.service.OwnerCommonService;
import com.personal.domain.store.dto.StoreRequest;
import com.personal.domain.store.repository.StoreRepository;
import com.personal.entity.store.Store;
import com.personal.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final OwnerCommonService ownerCommonService;


    public void saveStores(StoreRequest.SaveStores saveStores, AuthUser authUser) {
        User user = ownerCommonService.getUserById(authUser.getUserId());
        Store store = Store.builder()
                .user(user)
                .name(saveStores.name())
                .tel(saveStores.tel())
                .zip(saveStores.zip())
                .address(saveStores.address())
                .addressDetail(saveStores.addressDetail())
                .description(saveStores.description())
                .build();
        storeRepository.save(store);
    }
}
