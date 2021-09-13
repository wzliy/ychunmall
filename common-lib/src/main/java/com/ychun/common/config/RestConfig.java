package com.ychun.common.config;

import com.ychun.common.error.GlobalExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {GlobalExceptionTranslator.class})
public class RestConfig {
}
