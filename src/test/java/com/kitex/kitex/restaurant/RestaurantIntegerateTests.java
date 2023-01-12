package com.kitex.kitex.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitex.kitex.auth.dto.DataDto;
import com.kitex.kitex.auth.dto.JwtResponse;
import com.kitex.kitex.profile.repository.ProfileRepository;
import com.kitex.kitex.restaurants.repository.IRestaurantRepository;
import com.kitex.kitex.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RestaurantIntegerateTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Test
    public void CreateRestaurant() throws Exception {

        String json_ownerToCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Freeman\"," +
                "\"lastName\": \"Joe\"," +
                "\"phone\": \"08075662781\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";


        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        String json_ownerLoginCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"}";

        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerLoginCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JwtResponse jwtResponse = mapper.readValue(response, JwtResponse.class);
        String jwtToken = jwtResponse.getData().getToken();

        // Save the token in a variable
        String savedJwtToken = jwtToken;

        System.out.println(savedJwtToken);

        String json_ownerToCreateRestaurant = "{\"cityId\":1," +
                "\"name\": \"EMBER LOUNGE\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";


        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/restaurants")
                        .header("Authorization", "Bearer " + savedJwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerLoginCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        this.profileRepository.deleteAll();
        this.userRepository.deleteAll();
        this.restaurantRepository.deleteAll();


    }



    @Test
    public void ListKitchens() throws Exception {

        String json_ownerToCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Freeman\"," +
                "\"lastName\": \"Joe\"," +
                "\"phone\": \"08075662781\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";


        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        String json_ownerLoginCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"}";

        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerLoginCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JwtResponse jwtResponse = mapper.readValue(response, JwtResponse.class);
        String jwtToken = jwtResponse.getData().getToken();

        // Save the token in a variable
        String savedJwtToken = jwtToken;

        System.out.println(savedJwtToken);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurants")
                        .header("Authorization", "Bearer " + savedJwtToken))
                .andExpect(status().isOk());

        this.profileRepository.deleteAll();
        this.userRepository.deleteAll();
        this.restaurantRepository.deleteAll();



    }

}
