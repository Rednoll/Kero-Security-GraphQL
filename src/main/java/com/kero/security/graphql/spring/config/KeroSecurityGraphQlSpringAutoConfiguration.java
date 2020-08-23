package com.kero.security.graphql.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.kickstart.tools.boot.ClasspathResourceSchemaStringProvider;
import graphql.kickstart.tools.boot.GraphQLJavaToolsAutoConfiguration;
import graphql.kickstart.tools.boot.SchemaStringProvider;

@Configuration
public class KeroSecurityGraphQlSpringAutoConfiguration {

	@Autowired
	private GraphQLJavaToolsAutoConfiguration graphqlConfig;
	
	@Bean
	public SchemaStringProvider schemaStringProvider() throws Exception {
		
		Object props = graphqlConfig.getClass().getDeclaredField("props").get(graphqlConfig);
		String schemaLocationPattern = (String) props.getClass().getMethod("getSchemaLocationPattern").invoke(props);
		
		return new ClasspathResourceSchemaStringProvider(schemaLocationPattern);
	}
}
