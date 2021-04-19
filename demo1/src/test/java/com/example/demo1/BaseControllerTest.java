package com.example.demo1;

import org.apache.commons.collections4.MapUtils;
import org.shoulder.core.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    private static final String UTF_8 = "UTF-8";

    public void doGetTest(String getUrl, String resultContains) throws Exception {
        ResultActions resultActions = mockMvc.perform(get(getUrl).characterEncoding(UTF_8));
        resultActions.andReturn().getResponse().setCharacterEncoding(UTF_8);
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resultContains)));
    }

    public void doPostTest(String postUrl, String resultContains) throws Exception {
        doPostTest(postUrl, Collections.emptyMap(), resultContains);
    }

    public void doPostTest(String postUrl, Map<String, Object> params, String resultContains) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post(postUrl)
                .characterEncoding(UTF_8)
                .contentType(MediaType.APPLICATION_JSON);
        if (MapUtils.isNotEmpty(params)) {
            requestBuilder.content(JsonUtils.toJson(params));
        }

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andReturn().getResponse().setCharacterEncoding(UTF_8);
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(resultContains)));
    }

}
