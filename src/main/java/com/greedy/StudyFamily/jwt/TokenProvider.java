package com.greedy.StudyFamily.jwt;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.dto.TokenDto;
import com.greedy.StudyFamily.exception.TokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider {
	
	private static final String AUTHORITIES_KEY = "auth";	/* 권한관련 - CRUD필수 */
	private static final String BEARER_TYPE = "bearer";		//jwt는 보편적으로 bearer타입 사용
						//ACCESS_TOKEN_EXPIRE_TIME : 밀리세컨즈 단위!
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;	//30분의 유효시간
	private final Key key;
	
	private final UserDetailsService userDetailsService;
	
						//BASE64로 encode 되어있는 문자열을 byte[]로 decode 처리 해주는 것
	public TokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.userDetailsService = userDetailsService;
	}
	
	
	public TokenDto generateTokenDto(LoginDto login) {
		
		log.info("[TokenProvider] generateTokenDTO Start =================");
		
		List<String> roles = Collections.singletonList(login.getMemberRole());	/* 권한관련 - CRUD필수 */
		
		/*Token에 데이터를 담을때 plan이라는 데이터 타입을 사용*/
		// Claims라고 불리우는 JWT body(payload)에 정보 담기
		Claims claims = Jwts
				.claims()
				.setSubject(login.getLoginId());	//map 형식임
		
		claims.put(AUTHORITIES_KEY, roles);	/* 권한관련 - CRUD필수 */
		
		// 유효시간 설정
		long now = (new Date()).getTime();
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);	//현재 시간 + 30분 => 토큰 만료시간
		
		// Access Token 생성
		String accessToken = Jwts.builder()
				.setClaims(claims)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS512)	//HS512 : 서명 알고리즘
				.compact();
		
							//(TYPE , NAME, TOKEN , 유효시간) - DTO에 선언한 모든 데이터를 반환한다.
		return new TokenDto(BEARER_TYPE, login.getLoginId(), accessToken, accessTokenExpiresIn.getTime());
	}


	//JwtFilter에서 validateToken으로 반환 받아온 메소드
	public boolean validateToken(String jwt) {

		try {
			//정의해 key로 서명한 권한 토큰에 대해서 가져와 확인하는 로직!
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
			return true;	//정상적으로 인증되면 true를 반환!
		} catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("[TokenProvider] 잘못  된 JWT 서명입니다.");
			throw new TokenException("잘못 된 JWT 서명입니다.");
		} catch(ExpiredJwtException e) {
			log.info("[TokenProvider] 만료 된 JWT 서명입니다.");
			throw new TokenException("만료 된 JWT 서명입니다.");
		} catch(UnsupportedJwtException e) {
			log.info("[TokenProvider] 지원되지 않는 JWT 서명입니다.");
			throw new TokenException("지원되지 않는 JWT 서명입니다.");
		} catch(IllegalArgumentException e) {
			log.info("[TokenProvider] JWT 토큰이 잘못 되었습니다.");
			throw new TokenException("JWT 토큰이 잘못 되었습니다.");
		}
		
	}


	// 토큰에서 권한 꺼내기
	public Authentication getAuthentication(String jwt) {

		Claims claims = parseClaims(jwt);
		
		//UserDetail 객체를 만들어서 Authentication 리턴
		UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
		
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	
	//parseClaims를 사용하기 위한 정의 메소드 (body 즉 payload를 꺼내온다.)
	private Claims parseClaims(String jwt) {
		
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
	}
	
}
