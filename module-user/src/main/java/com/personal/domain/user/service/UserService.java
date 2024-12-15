package com.personal.domain.user.service;

import com.personal.common.config.JwtUtil;
import com.personal.common.entity.AuthUser;
import com.personal.common.enums.UserRole;
import com.personal.domain.user.dto.UserRequest;
import com.personal.domain.user.repository.UserRepository;
import com.personal.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public String login(UserRequest.Login login) {
        User user = userRepository.findByEmail(login.email()).orElseThrow(() -> new RuntimeException("User not found"));

        // 비밀번호 검증
        if (!passwordEncoder.matches(login.password(), user.getPassword())) {
            throw new RuntimeException("비밀번호 틀림");
        }

        // 삭제 여부 검증
        if (user.isDeleted()) {
            throw new RuntimeException("사용 불가 아이디");
        }

        // 토큰 생성
        String refreshToken = jwtUtil.createToken(user.getId() , user.getEmail() , user.getRole() , JwtUtil.REFRESH);
        return jwtUtil.createToken(user.getId() , user.getEmail() , user.getRole() , JwtUtil.ACCESS);
    }

    @Transactional
    public void register(UserRequest.Register register) {
        // 중복된 이메일 확인
        if (userRepository.findByEmail(register.email()).isPresent()) {
            throw new RuntimeException("이메일 중복");
        }

        // 비밀번호 정규식 검증
        if (!passwordVerification(register.password())) {
            throw new RuntimeException("비밀번호 정규식 검증에 걸림");
        }

        // 비밀번호 인코딩
        String encryptPassword = passwordEncoder.encode(register.password());
        User user = new User(register.email() , encryptPassword , register.name() , UserRole.ROLE_USER);

        userRepository.save(user);
    }

    public void logout(AuthUser authUser) {
        // TODO : 추후 redis로 관리하게 될 refreshToken 제거용으로 작성!
    }

    // 비밀번호 정규식 검증 ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>]).{8,}$
    private boolean passwordVerification(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$");
        return pattern.matcher(password).matches();
    }
}
