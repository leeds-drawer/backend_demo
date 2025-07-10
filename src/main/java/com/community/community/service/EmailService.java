package com.community.community.service;

import com.community.community.domain.EmailVerification;
import com.community.community.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailVerificationRepository emailVerificationRepository;

    @Transactional
    public String sendVerificationCode(String email) {
        String code = createCode();
        EmailVerification verification = EmailVerification.builder()
                .email(email)
                .code(code)
                .expiryDate(LocalDateTime.now().plusMinutes(5))
                .build();
        emailVerificationRepository.save(verification);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(email);
            helper.setSubject("[커뮤니티] 회원가입 인증 코드입니다.");
            helper.setText(createEmailBody(code), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }

        return code;
    }

    public boolean verifyCode(String email, String code) {
        return emailVerificationRepository.findByEmail(email)
                .map(verification -> verification.getCode().equals(code) && verification.getExpiryDate().isAfter(LocalDateTime.now()))
                .orElse(false);
    }

    private String createCode() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    private String createEmailBody(String code) {
        return "<div style='font-family: Arial, sans-serif; text-align: center; color: #333;'>"
                + "<h2>회원가입 인증 코드</h2>"
                + "<p>안녕하세요! 커뮤니티에 가입해주셔서 감사합니다.</p>"
                + "<p>아래 인증 코드를 입력하여 회원가입을 완료해주세요.</p>"
                + "<div style='margin: 30px; padding: 20px; background-color: #f2f2f2; border-radius: 5px;'>"
                + "<h3 style='color: #007bff; font-size: 24px; letter-spacing: 2px;'>" + code + "</h3>"
                + "</div>"
                + "<p>이 코드는 5분 동안 유효합니다.</p>"
                + "<p>감사합니다.</p>"
                + "</div>";
    }
}
