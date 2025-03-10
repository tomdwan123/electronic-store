package com.be.electronic_store.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class })
@ActiveProfiles(profiles = "test")
public class BaseTest {
}