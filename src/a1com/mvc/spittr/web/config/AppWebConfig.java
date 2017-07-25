package a1com.mvc.spittr.web.config;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc // Enable Spring MVC
@ComponentScan(basePackages = { "a1com.mvc.spittr.web" })
// Packages will be scanned for components, the controllers you write will be
// annotated with @Controller, which will make them candidates for
// component-scanning.
public class AppWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	// ----------------------------JSP
	// CONFIG--------------------------------------------
	// Configure a JSP view resolver ViewResolver, Interface to be implemented
	// by objects that can resolve views by name.
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		logger.info("InternalResourceViewResolver instantiated");
		// it’s configured to look for JSP /WEB-INF/views/ files by wrapping
		// view names with a specific prefix and suffix (for example, a view
		// name of home will be resolved / WEB-INF /views/home.jsp).
		resolver.setPrefix("/WEB-INF/views/");
		logger.info("resolver setting prefix /WEB-INF/views/");
		resolver.setSuffix(".jsp");
		logger.info("resolver setting suffix .jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		logger.info("org.springframework.web.servlet.view.JstlView.class set as view class");
		return resolver;
	}

	
	
	// -----------------------------------TILES
	// CONFIG----------------------------------------------------
	/**
	 * 
	 * Next, let’s configure TilesViewResolver . As you can see, it’s a rather
	 * basic bean definition, with no properties to set: //
	 */
	// @Bean
	// public ViewResolver tilesViewResolver() {
	// logger.info("TilesViewResolver instantiated");
	// return new TilesViewResolver();
	// }
	//
	// /**
	// * to use Tiles with Spring
	// *
	// * @return
	// */
	// @Bean
	// public TilesConfigurer tilesConfigurer() {
	// TilesConfigurer tiles = new TilesConfigurer();
	// logger.info("TilesConfigurer instantiated");
	// // TilesConfigurer look for any file named tiles.xml anywhere under the
	// // / WEB-INF / directory
	// // using Ant-style wildcards ( ** ) When ** is used as the name of a
	// // directory in the pattern, it matches zero or more directories. F
	// tiles.setDefinitions(new String[] { "/WEB-INF/**/tiles.xml" });
	// logger.info("tiles setting definition @/WEB-INF/**/tiles.xml");
	// tiles.setCheckRefresh(true);
	// return tiles;
	// }

	
	
	// -------------------------Thymeleaf------------------------
	// @Bean
	// public SpringResourceTemplateResolver templateResolver() {
	// // SpringResourceTemplateResolver automatically integrates with Spring's
	// // own
	// // resource resolution infrastructure, which is highly recommended.
	// SpringResourceTemplateResolver templateResolver = new
	// SpringResourceTemplateResolver();
	// templateResolver.setApplicationContext(this.applicationContext);
	// // templateResolver.setPrefix("/WEB-INF/thymeleaftemplates/");
	// templateResolver.setPrefix("/WEB-INF/thymeleafviews/");
	// templateResolver.setSuffix(".html");
	// // HTML is the default value, added here for the sake of clarity.
	// templateResolver.setTemplateMode(TemplateMode.HTML);
	// // Template cache is true by default. Set to false if you want
	// // templates to be automatically updated when modified.
	// templateResolver.setCacheable(true);
	// logger.info("SpringResourceTemplateResolver instatiated");
	// return templateResolver;
	// }
	//
	// @Bean
	// public SpringTemplateEngine templateEngine() {
	// // SpringTemplateEngine automatically applies SpringStandardDialect and
	// // enables Spring's own MessageSource message resolution mechanisms.
	// SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	// templateEngine.setTemplateResolver(templateResolver());
	// // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	// // speed up execution in most scenarios, but might be incompatible
	// // with specific cases when expressions in one template are reused
	// // across different data types, so this flag is "false" by default
	// // for safer backwards compatibility.
	// templateEngine.setEnableSpringELCompiler(true);
	// logger.info("SpringTemplateEngine instatiated");
	// return templateEngine;
	// }
	//
	// @Bean
	// public ThymeleafViewResolver viewResolver() {
	// ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	// viewResolver.setTemplateEngine(templateEngine());
	// logger.info("ThymeleafViewResolver instatiated");
	// return viewResolver;
	// }

	
	
	// -------------------------Static Resource------------------------
	// Configures a request handler for serving static resources by forwarding
	// the request to the Servlet container's "default" Servlet
	// you’re asking DispatcherServlet to forward requests for static resources
	// to the servlet container’s default servlet and not to try to handle them
	// itself.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		logger.info("DefaultServletHandlerConfigurer instatiated");
	}

}
