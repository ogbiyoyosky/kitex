package com.kitex.kitex.auth;
import com.kitex.kitex.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerIntegrationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private  UserRepository userRepository;

    @Test
    public void ItShouldCreateDriver() throws Exception {
        // Set up the request body

        String json_ownerToCreate = "{\"email\":\"nuel@nueljoe1.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Emmanuel\"," +
                "\"lastName\": \"Ogbiyoyo\"," +
                "\"phone\": \"08075662787\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";


        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        this.userRepository.deleteAll();
    }

    @Test
    public void ItShouldCreateCustomer() throws Exception {
        // Set up the request body
        String json_ownerToCreate = "{\"email\":\"nuel@nueljoe2.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Freeman\"," +
                "\"lastName\": \"Joe\"," +
                "\"phone\": \"08075662781\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";


        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        this.userRepository.deleteAll();


    }

    @Test
    public void ItShouldCreateKitchenAdmin() throws Exception {
        // Set up the request body

        String json_ownerToCreate = "{\"email\":\"nuel@nueljoe3.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Freeman\"," +
                "\"lastName\": \"Joe\"," +
                "\"phone\": \"08075662784\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";

        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        this.userRepository.deleteAll();

    }

    @Test
    public void ItShouldLoginAUser() throws Exception {
        // Set up the request body
        String json_userToCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Freeman\"," +
                "\"lastName\": \"Joe\"," +
                "\"phone\": \"08075662784\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";

        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON));


        String json_ownerLoginCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"}";

        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerLoginCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.userRepository.deleteAll();
    }
    
}




