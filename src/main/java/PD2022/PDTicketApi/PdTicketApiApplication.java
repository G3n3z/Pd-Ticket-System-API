package PD2022.PDTicketApi;

import PD2022.PDTicketApi.annotation.DateFormatAnnotationFormatterFactory;
import PD2022.PDTicketApi.constants.Constants;
import PD2022.PDTicketApi.security.RsaKeyProperties;
import PD2022.PDTicketApi.security.UserAuthenticationProvider;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PdTicketApiApplication {

	private final RsaKeyProperties rsaKeys;

	public PdTicketApiApplication(RsaKeyProperties rsaKeys)
	{
		this.rsaKeys = rsaKeys;
	}

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig
	{
		@Autowired
		private UserAuthenticationProvider authProvider;

		@Autowired
		public void configAuthentication(AuthenticationManagerBuilder auth)
		{
			auth.authenticationProvider(authProvider);
		}


		@Bean
		@Order(1)
		public SecurityFilterChain openFilterChain(HttpSecurity http) throws Exception
		{
			return http
					.csrf(csrf -> csrf.disable())
					.securityMatcher("/espectaculos")
					.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).build();
		}


		@Bean
		@Order(1)
		public SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception
		{
			return http
					.csrf(csrf -> csrf.disable())
					.securityMatcher("/login")
					.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.build();
		}

		@Bean
		public SecurityFilterChain genericFilterChain(HttpSecurity http) throws Exception
		{
			return http
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers("/users/**")
							.hasRole(Constants.ADMIN_ROLE_NAME)
							.anyRequest().authenticated())
					.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.build();
		}
	}

	@Bean
	JwtEncoder jwtEncoder()
	{
		JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
		JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwkSource);
	}

	@Bean
	JwtDecoder jwtDecoder()
	{
		return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		final JwtGrantedAuthoritiesConverter gac = new JwtGrantedAuthoritiesConverter();
		gac.setAuthoritiesClaimName("roles");
		gac.setAuthorityPrefix("ROLE_");

		final JwtAuthenticationConverter jac = new JwtAuthenticationConverter();
		jac.setJwtGrantedAuthoritiesConverter(gac);
		return jac;
	}

	@Configuration
	public class MvcConfig implements WebMvcConfigurer {
		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addFormatterForFieldAnnotation(new DateFormatAnnotationFormatterFactory());
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(PdTicketApiApplication.class, args);
	}

}
