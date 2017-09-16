package com.sunshineapp.backend;

import com.sunshineapp.jmsintegrationadapter.converters.BuildTaskDtoConverter2Bytes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = "com.sunshineapp.backend")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public ConversionService conversionService() {
		final ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters( getConverters() );
		bean.afterPropertiesSet();
		final ConversionService object = bean.getObject();
		return object;
	}

	private Set<Converter<?, ?>> getConverters() {
		final Set<Converter<?, ?>> converters = new HashSet<>();

		converters.add(new BuildTaskDtoConverter2Bytes());

		return converters;
	}

}
