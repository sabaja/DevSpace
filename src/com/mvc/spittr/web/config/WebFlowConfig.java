package com.mvc.spittr.web.config;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

/**
 * https://docs.spring.io/spring-webflow/docs/current/reference/htmlsingle/#system-setup
 * 
 * @author sabaja
 *
 */
@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * 
	 * https://docs.spring.io/spring-webflow/docs/current/api/org/springframework/webflow/config/FlowDefinitionRegistryBuilder.html#addFlowLocation(java.lang.String,%20java.lang.String)
	 * FlowRegistry Register your flows in a FlowRegistry in Java:
	 * 
	 * @return FlowDefinitionRegistry’s job is to load flow definitions and make
	 *         them available to the flow exec- utor.
	 *         FlowDefinitionRegistryBuilder.addFlowLocation("/WEB-INF/**").build()
	 */
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		logger.info("flowRegistry config - WEB-FLOW -");
		logger.info("BasePath: /WEB-INF\tFlowLocation: /**/*-flow.xml");
		return getFlowDefinitionRegistryBuilder().setBasePath("/WEB-INF").addFlowLocation("/**/*-flow.xml").build();
	}

	/**
	 * https://docs.spring.io/spring-webflow/docs/current/api/org/springframework/webflow/config/FlowDefinitionRegistryBuilder.html#addFlowLocation(java.lang.String,%20java.lang.String)
	 * FlowExecutor: Deploy a FlowExecutor, the central service for executing
	 * The flow executor drives the execution of a flow. When a user enters a
	 * flow, the flow executor creates and launches an instance of the flow
	 * execution for that user. When the flow pauses (such as when a view is
	 * presented to the user), the flow executor also resumes the flow once the
	 * user has taken some action.
	 */

	@Bean
	public FlowExecutor flowExecutor() {
		logger.info("flowExecutor config - WEB-FLOW -");
		return getFlowExecutorBuilder(flowRegistry()).build();
	}

	/**
	 * @Bean public FlowExecutor flowExecutor() { return
	 *       getFlowExecutorBuilder(flowRegistry()).setMaxFlowExecutions(5).setMaxFlowExecutionSnapshots(30).build();
	 *       }
	 * 
	 *       max-executions
	 * 
	 *       Tune the max-executions attribute to place a cap on the number of
	 *       flow executions that can be created per user session. When the
	 *       maximum number of executions is exceeded, the oldest execution is
	 *       removed.
	 * 
	 *       [Note] The max-executions attribute is per user session, i.e. it
	 *       works across instances of any flow definition.
	 *       max-execution-snapshots
	 * 
	 *       Tune the max-execution-snapshots attribute to place a cap on the
	 *       number of history snapshots that can be taken per flow execution.
	 *       To disable snapshotting, set this value to 0. To enable an
	 *       unlimited number of snapshots, set this value to -1.
	 * 
	 * 
	 */

	/**
	 * https://docs.spring.io/spring-webflow/docs/current/api/org/springframework/webflow/config/FlowDefinitionRegistryBuilder.html#addFlowLocation(java.lang.String,%20java.lang.String)
	 * Use the flow-builder-services attribute to customize the services and
	 * settings used to build flows in a flow-registry. If no
	 * flow-builder-services tag is specified, the default service
	 * implementations are used. When the tag is defined, you only need to
	 * reference the services you want to customize.
	 */
	@Bean
	public FlowBuilderServices flowBuilderServices() {
		logger.info("FlowBuilderServices config - WEB-FLOW -");
		return getFlowBuilderServicesBuilder().build();
	}

	/**
	 * the FlowHandlerMapping is wired with the flow registry so
	 * it knows when a request’s URL maps to a flow. For example, if you have a
	 * flow whose ID is pizza , then FlowHandlerMapping will know to map a
	 * request to that flow if the request’s URL pattern (relative to the
	 * application context path) is /pizza.
	 * 
	 */
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setFlowRegistry(flowRegistry());
		logger.info("FlowHandlerMapping config - WEB-FLOW -");
		return handlerMapping;
	}

	/**
	 * 
	 * the FlowHandlerMapping ’s job is to direct flow requests to Spring Web
	 * Flow, it’s the job of a FlowHandlerAdapter to answer that call. A
	 * FlowHandlerAdapter is equivalent to a Spring MVC controller in that it
	 * handles requests coming in for a flow and processes those requests. This
	 * handler adapter is the bridge between DispatcherServlet and Spring Web
	 * Flow. It handles flow requests and manipulates the flow based on those
	 * requests. Here, it’s wired with a reference to the flow executor to
	 * execute the flows for which it handles requests.
	 */
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		logger.info("FlowHandlerAdapter config - WEB-FLOW -");
		return handlerAdapter;
	}
}
