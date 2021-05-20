package ru.job4j.pasport_control.controllers.restControllers;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.pasport_control.PassportControlApplication;
import ru.job4j.pasport_control.models.Passport;
import ru.job4j.pasport_control.service.PassportService;
import org.junit.Assert;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.05.2021.
 */
@SpringBootTest(classes = PassportControlApplication.class)
@AutoConfigureMockMvc
class FindRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PassportService ps;

    @Test
    void shouldReturnAllPassports() throws Exception {
        this.mockMvc.perform(get("/passport/find/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnDPassportWithId() throws Exception {
        List<Passport> passportList = this.ps.findAll();
        Passport passport = null;
        if (!passportList.isEmpty()) {
            passport = passportList.get(0);
        }
        MvcResult result = this.mockMvc.perform(get("/passport/find/{id}", passport.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String newPersonResult = result.getResponse().getContentAsString();
        JSONObject object = new JSONObject(newPersonResult);
        Assert.assertEquals(object.get("number"), passport.getNumber());
        Assert.assertEquals(object.get("id"), passport.getId());
    }
}