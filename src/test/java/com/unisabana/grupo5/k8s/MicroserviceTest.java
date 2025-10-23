package com.unisabana.grupo5.k8s;

import com.unisabana.grupo5.k8s.service.MicroserviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class MicroserviceTest {

    private final MicroserviceService microserviceService;

    @Autowired
    MicroserviceTest(MicroserviceService microserviceService){
        this.microserviceService=microserviceService;
    }

    @Test
    void shouldReadFileContent() {
        String content = this.microserviceService.getAttachedText();
        assertNotNull(content);
    }
}
