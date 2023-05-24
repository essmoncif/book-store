package com.store.catalog.app.services.common.configurations;


import com.store.catalog.app.services.common.storage.LocalStorageService;
import com.store.catalog.app.services.common.storage.S3StorageService;
import com.store.catalog.app.services.common.storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Value("${server.env}")
    private String env;
    @Bean
    public StorageService getStorageService() {
        if (env.equals("DEV")) {
            return new LocalStorageService();
        }else {
            return new S3StorageService();
        }
    }

}
