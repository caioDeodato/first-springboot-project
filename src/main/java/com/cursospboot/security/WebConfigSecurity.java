package com.cursospboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	// configura as requisições de acesso por http
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
			.disable() // desativa as config padrão de memoria do spring
			.authorizeRequests() // permitir restringir acessos
			.antMatchers(HttpMethod.GET, "/").permitAll() // qualquer usuario acessa a pagina inicial
			.anyRequest().authenticated()
			.and().formLogin().permitAll() // permite qualquer usuario
			.and().logout() // mapeia a url de logout e invalida usuario autenticado
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override // cria autenticação do usuario com banco de dados ou memoria (neste caso será memoria)
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
			.withUser("caio")
			.password("$2a$10$UYU/oyiEzvsIXczGL6PqWOJraM.MqaOal4PAUH95PpH227n/kBOb2")
			.roles("ADMIN");
	}
	// a seguinte instrução n será necessaria pois n tem esses tipos de arquivo no projeto
//	@Override // ignora url's especificas, como css, js
//	public void configure(WebSecurity web) throws Exception {
//
//		
//	}
}
