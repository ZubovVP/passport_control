package ru.job4j.pasport_control.controllers.restControllers;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.pasport_control.PassportControlApplication;
import ru.job4j.pasport_control.models.Passport;
import ru.job4j.pasport_control.service.PassportService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.05.2021.
 */
@ActiveProfiles("test")
@SpringBootTest(classes = PassportControlApplication.class)
@AutoConfigureMockMvc
class SaveUpdateRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PassportService ps;
    private Gson gson = new Gson();

    @Test
    void shouldReturnAllPassports() throws Exception {
        Passport passport = new Passport();
        passport.setName("Vitaly");
        passport.setSurname("Zubov");
        passport.setSeria(123456);
        passport.setNumber(654321);
        Mockito.when(ps.save(passport)).thenReturn(passport);
        MvcResult result = this.mockMvc.perform(post("/passport/save/").contentType(MediaType.APPLICATION_JSON).content(this.gson.toJson(passport)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(passport.getName()))
                .andExpect(jsonPath("$.surname").value(passport.getSurname())).andReturn();
        String newPersonResult = result.getResponse().getContentAsString();
        JSONObject object = new JSONObject(newPersonResult);
        Assert.assertEquals(passport.getNumber(), object.get("number"));
        Assert.assertEquals(passport.getSeria(), object.get("seria"));
        Assert.assertEquals(passport.getId(), object.get("id"));
    }
}