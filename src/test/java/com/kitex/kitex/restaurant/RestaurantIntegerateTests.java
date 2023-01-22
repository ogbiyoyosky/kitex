package com.kitex.kitex.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitex.kitex.auth.dto.DataDto;
import com.kitex.kitex.auth.dto.JwtResponse;
import com.kitex.kitex.menu.repository.IMenuRepository;
import com.kitex.kitex.profile.repository.ProfileRepository;
import com.kitex.kitex.restaurant.dto.RestaurantResponseDto;
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

    @Autowired
    private IMenuRepository menuRepository;


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
                .perform(MockMvcRequestBuilders.post("/api/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        String json_ownerLoginCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"}";

        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/login")
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
                        .content(json_ownerToCreateRestaurant)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        this.restaurantRepository.deleteAll();
        this.userRepository.deleteAll();
        this.profileRepository.deleteAll();

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
                .perform(MockMvcRequestBuilders.post("/api/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String json_ownerLoginCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"}";

        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/login")
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

    @Test
    public void createMenuForRestaurant() throws Exception {
        String json_ownerToCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"," +
                "\"firstName\": \"Freeman\"," +
                "\"lastName\": \"Joe\"," +
                "\"phone\": \"08075662781\"," +
                "\"address\": \"1 North street Staffordshire ST47FA\"}";


        // Send the POST request
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        String json_ownerLoginCreate = "{\"email\":\"nuel@nueljoe9.com\"," +
                "\"password\": \"miracle123\"}";

        String response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/auth/login")
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


        String restaurantResponseMvc = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/restaurants")
                        .header("Authorization", "Bearer " + savedJwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreateRestaurant)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper restaurantMapper = new ObjectMapper();
        RestaurantResponseDto restaurantResponse = restaurantMapper.readValue(restaurantResponseMvc, RestaurantResponseDto.class);
        Integer id = restaurantResponse.getData().getId();

        String crate_menu_item = "{\"itemName\":\"Hambuger\"," +
                "\"description\": \"Bread and salad\"," +
                "\"ingredients\": \"Bread and salad\"," +
                "\"categoryId\": 1," +
                "\"recipe\": \"Water yeast flour salt suger and butter\"," +
                "\"price\": 100" + "}";

          mockMvc
                .perform(MockMvcRequestBuilders.post("/api/menus/resturants/" + id + "/create" )
                        .header("Authorization", "Bearer " + savedJwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(crate_menu_item)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());



        this.menuRepository.deleteAll();
        this.restaurantRepository.deleteAll();
        this.userRepository.deleteAll();
        this.profileRepository.deleteAll();

    }



}
