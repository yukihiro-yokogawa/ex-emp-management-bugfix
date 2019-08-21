package jp.co.sample.emp_management.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.sample.emp_management.service.JpaUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/img/**","/js/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 認証が必要となるURLを設定する
				.antMatchers("/").permitAll()// ログインフォームは認証不要
				.antMatchers("/login").permitAll()// ログイン処理は認証不要
				.antMatchers("/toInsert").permitAll()// 管理者登録フォームは認証不要
				.antMatchers("/insert").permitAll()// 管理者登録は認証不要
				.anyRequest().authenticated();//それ以外は認証された状態でなければアクセスできない
		http.formLogin()// ログインページに強制遷移
				.loginProcessingUrl("/login")// ログイン処理をするURL
				.loginPage("/")// ログインページのURL
				.failureUrl("/?error=true")
				.defaultSuccessUrl("/employee/showList",true)
				.usernameParameter("mailAddress")
				.passwordParameter("password");
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/employee/logout"))
		.logoutSuccessUrl("/");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	protected static class AuthenticationConfiguration
		extends GlobalAuthenticationConfigurerAdapter{
		
	@Autowired
	JpaUserDetailsServiceImpl userDetailsService;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	}

}