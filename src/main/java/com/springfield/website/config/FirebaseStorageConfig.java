package com.springfield.website.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class FirebaseStorageConfig {

    @Bean
    public Credentials firebaseCredentials(){
        try{
            Resource resource = new ClassPathResource("firebasekey.json");
            Credentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
            log.info("Successfully loaded and initialized firebase credentials key for file storage");
            return credentials;
        }catch (Exception exception){
            exception.printStackTrace();
            log.error("Failed to load firebase credentials for firebase storage");
            return null;
        }
    }

    @Bean
    public Storage getFilebaseStorageService(){
        try {
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(firebaseCredentials())
                    .build()
                    .getService();
            log.info("Successfully loaded firebase storage");
            return storage;
        }catch (Exception exception){
            exception.printStackTrace();
            log.error("Exception occurred while trying to get firebase storage service bean: {}", exception.getMessage());
            return null;
        }
    }
}
