package com.quoter.quoter.service.impl;

import com.quoter.quoter.repository.TokenRepository;
import com.quoter.quoter.security.token.ConfirmationToken;
import com.quoter.quoter.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Override
    public void saveToken(ConfirmationToken token){
        tokenRepository.save(token);
    }


}
