package a1com.mvc.spittr.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * 
 * @author sabaja The only significant thing to note in AppRootConfig is that
 *         it’s annotated with @Component- Scan . There will be plenty of
 *         opportunities throughout this book to flesh out Root- Config with
 *         non-web components. You’re almost ready to start building a web
 *         application with Spring MVC . The big question at this point is what
 *         application you’ll build.
 */

@Configuration
@ComponentScan(basePackages = { "a1com.mvc.spittr.DAO" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class AppRootConfig {

	/**
	 * To use tag message <s:message code="spittr.home" /> with
	 * messages.properties
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}




}