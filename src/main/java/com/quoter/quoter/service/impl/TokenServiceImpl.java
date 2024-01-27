package com.quoter.quoter.service.impl;

import com.quoter.quoter.repository.TokenRepository;
import com.quoter.quoter.security.token.ConfirmationToken;
import com.quoter.quoter.service.TokenService;
import com.quoter.quoter.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    private final UserService userService;

    public TokenServiceImpl(TokenRepository tokenRepository, UserService userService) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
    }

    @Override
    public void saveToken(ConfirmationToken token){
        tokenRepository.save(token);
    }

    @Transactional
    @Override
    public String confirmToken(String tokenBody){
        ConfirmationToken token = tokenRepository.findByToken(tokenBody).isPresent() ? tokenRepository.findByToken(tokenBody).get() : null;
        if(Objects.isNull(token)) return "confirmation failed, no token found";
        if(token.getConfirmedAt() != null)
            throw new IllegalStateException("email already confirmed");

        LocalDateTime expiredAt = token.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        setConfirmedAt(tokenBody);
        userService.enableUser(token.getUser().getUsername());
        return "confirmed";
    }

    public void setConfirmedAt(String token) {
        tokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
