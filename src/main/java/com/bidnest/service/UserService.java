package com.bidnest.service;

import com.bidnest.exception.ItemNotFoundException;
import com.bidnest.model.user.User;
import com.bidnest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ItemNotFoundException("User not found"));
    }
}
