package hu.webuni.logistic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//OLD one, from memory
//		auth.inMemoryAuthentication()
//		.passwordEncoder(passwordEncoder())
//			.withUser("user").authorities("user").password(passwordEncoder().encode("pass"))
//			.and()
//			.withUser("admin").authorities("user","admin").password(passwordEncoder().encode("pass"));

//NEW one, persistence
		auth.authenticationProvider(authenticateionProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.csrf().disable() //cross side attach elleni v??delem kikapcsolva
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //REST-API eset??n ez a leggyakoribb, mindig kell user+jelsz??
			.and()
			.authorizeRequests()
			.antMatchers("/api/login/**").permitAll()
			.antMatchers(HttpMethod.POST,   "/api/transportPlans/**").hasAuthority("TM")
			
			.antMatchers(HttpMethod.PUT,    "/api/addresses/**").hasAuthority("AM")
			.antMatchers(HttpMethod.DELETE, "/api/addresses/**").hasAuthority("AM")
			
			.antMatchers(HttpMethod.POST,   "/api/addresses/**").hasAuthority("AM")
			
			.anyRequest().authenticated();
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationProvider authenticateionProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		return daoAuthenticationProvider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
}
