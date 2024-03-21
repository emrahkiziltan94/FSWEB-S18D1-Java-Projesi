package com.workintech.s18d1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@WebMvcTest(ApplicationPropertiesKeyTests.class)
class ApplicationPropertiesKeyTests {

    @Autowired
    private Environment env;

    @Test
    @DisplayName("application properties istenilenler eklendi mi?")
    void serverPortIsSetTo8585() {

        String serverPort = env.getProperty("server.port");
        assertThat(serverPort).isEqualTo("9000");



        String datasourceUrl = env.getProperty("spring.datasource.url");
        assertNotNull(datasourceUrl);

        String datasourceUsername = env.getProperty("spring.datasource.username");
        assertNotNull(datasourceUsername);

        String datasourcePassword = env.getProperty("spring.datasource.password");
        assertNotNull(datasourcePassword);

        String hibernateDdlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto");
        assertNotNull(hibernateDdlAuto);

        String hibernateSql = env.getProperty("logging.level.org.hibernate.SQL");
        assertNotNull(hibernateSql);

        String hibernateJdbcBind = env.getProperty("logging.level.org.hibernate.jdbc.bind");
        assertNotNull(hibernateJdbcBind);

    }
}


