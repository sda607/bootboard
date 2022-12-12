package com.boot.board.springboot.config.auth;
import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //1
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //2. h2-console화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests() //3. URL 별 권한 관리를 설정하는 옵션의 시작점.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //4. 권한 관리 대상을 지정 // "/" 등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한
                .anyRequest().authenticated()//5. 설정된 값들 이외 나머지 URL들을 나타낸다
                .and()
                .logout()
                .logoutSuccessUrl("/")//6. 로그아웃 성공시 /주소로 이동
                .and()
                .oauth2Login()//7. oAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint()//8
                .userService(customOAuth2UserService);//9
    }
}