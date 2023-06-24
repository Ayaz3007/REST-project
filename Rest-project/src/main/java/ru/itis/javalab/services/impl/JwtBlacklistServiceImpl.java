package ru.itis.javalab.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.javalab.repositories.BlacklistRepository;
import ru.itis.javalab.services.JwtBlacklistService;

@Service
@RequiredArgsConstructor
public class JwtBlacklistServiceImpl implements JwtBlacklistService {
    private final BlacklistRepository blacklistRepository;

    @Override
    public void add(String token) {
        blacklistRepository.save(token);
    }

    @Override
    public boolean isExists(String token) {
        return blacklistRepository.isExists(token);
    }
}
