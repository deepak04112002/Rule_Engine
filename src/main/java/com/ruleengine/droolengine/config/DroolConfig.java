package com.ruleengine.droolengine.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DroolConfig {

    private final KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("KieRule/offer.drl"));
        return kieFileSystem;
    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    @Bean
    public KieContainer getKieContainer() {
        log.info("Container created...");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll(); // this will parse and compile rules

        // Check for errors
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Rule compilation errors: " + kb.getResults().toString());
        }

        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession getKieSession() {
        log.info("session created...");
        return getKieContainer().newKieSession();
    }
}
