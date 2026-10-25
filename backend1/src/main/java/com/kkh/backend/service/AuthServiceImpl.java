package com.kkh.backend.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkh.backend.dto.JoinDTO;
import com.kkh.backend.dto.LoginDTO;
import com.kkh.backend.entity.MemberEntity;
import com.kkh.backend.repository.MemberRepository;
import com.kkh.backend.security.UserDetailsImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthServiceImpl implements AuthService {

	private MemberRepository memberRepository;

	private PasswordEncoder passwordEncoder;

	private final SecretKey secretKey;

	public AuthServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
			@Value("${jwt.secret}") String secret) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	@Transactional
	@Override
	public boolean addMember(JoinDTO dto) {
		UUID uuid = UUID.randomUUID();

		MemberEntity member = MemberEntity
						.builder()
						.userPk(uuid.toString())
						.userId(dto.getUserId())
						.role("normal")
				.userPwd(passwordEncoder.encode(dto.getUserPwd())).build();

		return memberRepository.save(member) != null;
	}

	private String generateToken(String userId, int expirationMilliSecond) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationMilliSecond);

		return Jwts.builder().subject(userId) // 0.12.x부터 subject() 형태로 변경됨
				.issuedAt(now) // issuedAt()
				.expiration(expiryDate) // expiration()
				.signWith(secretKey, Jwts.SIG.HS256) // Jwts.SIG.HS256 사용
				.compact();
	}

	@Override
	public Map<String, String> login(LoginDTO dto) {
		MemberEntity entity = memberRepository.findByUserId(dto.getUserId());
		Map<String, String> map = new HashMap<String, String>();

		if (entity == null) {
			return null;
		}

		if (!passwordEncoder.matches(dto.getUserPwd(), entity.getUserPwd())) {
			return null;
		}

		String accessToken = generateToken(dto.getUserId(), 1000 * 60 * 5);
		String refreshToken = generateToken(dto.getUserId(), 1000 * 60 * 10);

		map.put("accessToken", accessToken);
		map.put("refreshToken", refreshToken);

		return map;
	}

	@Override
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			// 만료, 위조, 형식 오류 등
			return false;
		}
	}

	@Override
	public String getUserIdFromToken(String token) {
		return Jwts
					.parser()
					.verifyWith(secretKey) // 0.12.x 검증 방식 (setSigningKey 대신 verifyWith)
					.build()
					.parseSignedClaims(token) // parseClaimsJws 대신 parseSignedClaims
					.getPayload()
					.getSubject();
	}

	@Override
	public UserDetails loadUserDetails(String userId) {
		
		MemberEntity entity = memberRepository.findByUserId(userId);
		return UserDetailsImpl
					.builder()
					.userId(entity.getUserId())
					
					.build();
	}

}
