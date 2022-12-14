package com.greedy.StudyFamily.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.greedy.StudyFamily.jwt.JwtAccessDeniedHandler;
import com.greedy.StudyFamily.jwt.JwtAuthenticationEntryPoint;
import com.greedy.StudyFamily.jwt.TokenProvider;


@EnableWebSecurity
public class SecurityConfig {
	
	// 인증 실패 핸들러
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	// 인가 실패 핸들러
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	// 토큰 제공 클래스
	private final TokenProvider tokenProvider;
	
	//인증, 인가, 토큰 핸들러 의존성 주입
	public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint
			, JwtAccessDeniedHandler jwtAccessDeniedHandler
			, TokenProvider tokenProvider) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
		this.tokenProvider = tokenProvider;
	}
	
	
	// 외부에서 이미지 파일에 접근 가능 하도록 설정
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers("/files/**");
	}
	
	// 비밀번호 암호화를 위한 빈 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	//BCryptPasswordEncoder : 정해진 encoder 타입!
	}
	
	//해당 필터가 먼저 가로채서 인증/인가에 대해서 판단해주기 때문에 내부에 해당 로직 작성하면 된다!
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		 return http
		         // CSRF 설정 Disable
		         .csrf()
		         	.disable()
		         // exception handling 설정 - 인증/인가 실패 핸들링~
		         	.exceptionHandling()
		         	// FilterChain에 직접 추가
		         		.authenticationEntryPoint(jwtAuthenticationEntryPoint)	//CustomFilter를 사용한다.(정의된 필터가 아닌 개발자가 직접 정의하는 필터)
		         		.accessDeniedHandler(jwtAccessDeniedHandler)			//CustomFilter를 사용한다.(정의된 필터가 아닌 개발자가 직접 정의하는 필터)
		        // .and()를 쓰기전에는 HttpSecurity 라는 설정 용도의 객체로 반환되지 않고 실패시의 exception handling 형태로 남아있음. 그래서 .and()를 통해 Http 형식으로 변환함
		        //exception 핸들링 설정 후 다른 설정을 위해서는 .and()로 바꿔줘야 다음 설정이 오류나지 않음!!중요!!!
		         .and()
		         // 시큐리티는 기본적으로 세션을 사용하지만 API 서버에선 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
		         // 세션대신 토큰을 이용할 것이기 때문에 세션을 사용하지 않겠다 설정한 것이다. 
		         .sessionManagement()
		             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		         .and()
		         	 // 요청에 대한 권한 체크
		             .authorizeRequests()
		             /* 클라이언트가 외부 도메인을 요청하는 경우 웹 브라우저에서 자체적으로 사전 요청(preflight)이 일어남
		              * 이 때 OPTIONS 메서드로 서버에 사전 요청을 보내 요청 권한이 있는지 확인 */
		             .antMatchers(HttpMethod.OPTIONS, "/**").hasAnyRole("STUDENT")
		             .antMatchers("/auth/**").permitAll()
		             .antMatchers("/professorauth/**").permitAll()
		             .antMatchers("/studentauth/**").permitAll()
		             .antMatchers("/layout/calendarView/**").permitAll()
		             .antMatchers("/api/v1/lectures/**").hasAnyRole("PROFESSOR")
		             .antMatchers("/api/v1/**").hasAnyRole("STUDENT", "PROFESSOR", "ADMIN")
		             .antMatchers("/api/info/**").hasAnyRole("STUDENT", "PROFESSOR", "ADMIN")
		             .antMatchers("/api/v1/student/**").hasAnyRole("STUDENT")
		             .antMatchers("/api/v1/professor/**").hasAnyRole("PROFESSOR", "ADMIN")
		             .antMatchers("/api/v1/tasks/**").hasAnyRole("STUDENT")
		             .antMatchers("/api/v1/message/**").hasAnyRole("STUDENT", "PROFESSOR")
		             //학생만 수강신청 가능 코드
		             .antMatchers("/api/v1/appClass/**").hasAnyRole("STUDENT")
		             //교수만 수업계획서 작성 가능 코드
		             .antMatchers("/api/v1/subPlan/make").hasAnyRole("PROFESSOR")
		             //교수만 강좌 공지 작성, 수정 가능 코드
		             .antMatchers("/board/subNotices/make").hasAnyRole("PROFESSOR")
		             .antMatchers("/board/subNotices/delete").hasAnyRole("PROFESSOR")
		             /* SpringSecurity를 사용하려면 여기서 사용하는것이 맞다. */
		             //.antMatchers(HttpMethod.GET, "/api/v1/reviews/**").permitAll()	//GET방식 외에는 AthenticationPrincipal이 필요하다.
		             //.antMatchers("/api/**").hasAnyRole("STUDENT", "ADMIN", "PROFESSOR")  // 나머지 API 는 전부 인증 필요
		         .and()
		         	.cors()
		         .and()
		         	.apply(new JwtSecurityConfig(tokenProvider))
		         .and()
		         	.build();
		 
	}
	
	/* CORS(cross-origin-resource-sharing) : 교차 출처 자원 공유
	 * 예전에는 자원 저장 서버와 웹 페이지가 하나의 서버에서 만들어졌기 때문에 해당 서버의 자원을 해당 도메인에서만 요청함
	 * 보안상 웹 브라우저는 다른 도메인에서 서버의 자원을 요청할 경우 막아 놓음
	 * 자원과 웹 페이지를 분리하는 경우, 다른 서버의 자원을 요청하는 경우가 점점 많아지면서 웹 브라우저는 외부 도메인과 통신하기 위한
	 * 표준인 CORS를 만듦
	 * 기본적으로 서버에서 클라이언트를 대상으로 리소스의 허용 여부를 결정함.
	 *  */
	
	
    //빈으로 생성한 객체로 통신에 필요한 부분을 허용하는 구문
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        // 로컬 React에서 오는 요청은 CORS 허용해준다.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000" ));	//들어올 서버 포트 관련
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "FETCH"));	//메소드	//아래는 헤더
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
