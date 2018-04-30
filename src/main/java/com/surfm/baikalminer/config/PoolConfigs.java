package com.surfm.baikalminer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "pool")
@Component
public class PoolConfigs {

}
