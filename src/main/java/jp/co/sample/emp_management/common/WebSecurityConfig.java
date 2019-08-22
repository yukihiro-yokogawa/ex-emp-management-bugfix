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
				.antMatchers("/","/toInsert","/insert").permitAll()// ログインフォーム、管理者登録フォーム、管理者登録処理は認証不要
				.anyRequest().authenticated();//それ以外は認証された状態でなければアクセスできない
		http.formLogin()// ログインページに強制遷移
				.loginProcessingUrl("/login")// ログイン処理をするURL
				.loginPage("/")// ログインページのURL
				.failureUrl("/?error=true")//ログイン失敗時のURL
				.defaultSuccessUrl("/employee/showList",true)
				.usernameParameter("mailAddress")//認証時に使用するユーザ名のリクエストパラメータ名
				.passwordParameter("password");//認証時に使用するパスワードのrクエストパラメータ名
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/employee/logout"))
		.logoutSuccessUrl("/")
		.deleteCookies("JSESSIONID")//ログアウト後にクッキーを削除
		.invalidateHttpSession(true);//セッションを削除
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