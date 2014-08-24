package com.adarrivi.multi.integration.rest;

import com.adarrivi.multi.config.WebConfiguration;
import com.adarrivi.multi.integration.GenericIntegrationTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class})
public abstract class GenericWebIntegrationTest extends GenericIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private IntegrationTestHttpRequest integrationTestHttpRequest;

    @Before
    public void setUp() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        integrationTestHttpRequest = new IntegrationTestHttpRequest(mockMvc);
    }

    protected IntegrationTestHttpRequest getIntegrationTestHttpRequest() {
        return integrationTestHttpRequest;
    }
}
