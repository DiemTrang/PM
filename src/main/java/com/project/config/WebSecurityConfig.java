package com.project.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 
 * @author
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// region -- Fields --

// end

// region -- Methods --

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Get environment variable
// String mod = System.getenv(Const.Mode.DEV);

// if (mod != null && "Y".equals(mod)) {
// http.cors().and().csrf().disable();
// } else {
		http.csrf().disable();
		http.cors().and().csrf().disable();
		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
// }

		http.authorizeRequests()
				.antMatchers("/", "/*", "/project/search", "/project/get-project-detail", "/project/create", "/account/search", "/account", "/account/sign-in",
						"/account/read", "/account/get-accounts-detail", "/account/create-account", "/task/search", "/file/read", 
						"/common/generate-model", "/common/encrypt", "/common/decrypt")
				.permitAll().anyRequest().authenticated().and().exceptionHandling();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Get environment variable
// String mod = System.getenv(Const.Mode.DEV);

// if (mod != null && "Y".equals(mod)) {
		web.ignoring().antMatchers("/*.html", "/*.css", "/*.js", "/*.png", "/*.ico", "/*.jpg", "/*.jpeg", "/*.gif",
				"/*.bmp", "/assets/**", "/*.ttf", "/*.woff", "/*.woff2", "/*.eot", "/*.svg", "/webjars/**",
				"/v2/api-docs", "/swagger-resources", "/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/security", "/swagger-ui.html");
// } else {
// web.ignoring().antMatchers("/*.html", "/*.css", "/*.js", "/*.png", "/*.ico",
// "/*.jpg", "/*.jpeg", "/*.gif",
// "/*.bmp", "/assets/**", "/*.ttf", "/*.woff", "/*.woff2", "/*.eot", "/*.svg",
// "/webjars/**");
// }
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		// Get environment variable
// String mod = System.getenv(Const.Mode.DEV);

// if (mod != null && "Y".equals(mod)) {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		source.registerCorsConfiguration("/**", config);
// }

		return source;
	}

// end
}