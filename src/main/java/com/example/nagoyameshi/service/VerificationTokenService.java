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

   @Transactional //token and user ID will be saaved in vr_tkns table
   public void createVerificationToken(User user, String token) {
       VerificationToken verificationToken = new VerificationToken();

       verificationToken.setUser(user);
       verificationToken.setToken(token);

       verificationTokenRepository.save(verificationToken);
   }

   // トークンの文字列で検索した結果を返す //get vrtkent matched specified token
   public VerificationToken findVerificationTokenByToken(String token) {
       return verificationTokenRepository.findByToken(token);
   }
}