package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.entity.VerificationToken;
import com.example.nagoyameshi.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional // token and user ID will be saved in vr_tkns table
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);
    }

    // トークンの文字列で検索した結果を返す
    public VerificationToken findVerificationTokenByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    public void update(VerificationToken token) {
        verificationTokenRepository.save(token);
    }

    // ユーザーIDとトークンに基づいてトークンを作成または更新するメソッドを追加
    @Transactional
    public void createOrUpdateVerificationToken(User user, String token) {
        VerificationToken existingToken = verificationTokenRepository.findByUserId(user.getId());

        if (existingToken != null) {
            // 既存トークンがあれば更新
            existingToken.setToken(token);
            verificationTokenRepository.save(existingToken);
        } else {
            // 新規作成
            VerificationToken newToken = new VerificationToken();
            newToken.setUser(user);
            newToken.setToken(token);
            verificationTokenRepository.save(newToken);
        }
    }
}


