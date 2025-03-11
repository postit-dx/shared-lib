package com.postit.sharedlibs.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.postit.sharedlibs.*"})
@EntityScan({"com.postit.sharedlibs.entity"})
public class AutoConfig {
}
