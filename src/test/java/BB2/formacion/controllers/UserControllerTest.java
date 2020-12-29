package BB2.formacion.controllers;

import BB2.formacion.models.Role;
import BB2.formacion.models.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;


public class UserControllerTest extends ControllerTest {

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void getAllUsers() throws Exception {
        String uri = "/api/users/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(200).isEqualTo(status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] users = mapFromJson(content, User[].class);
        assertThat(users.length > 0);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void getUsers() throws Exception {
        String uri = "/api/users";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("page", "0")
                .param("size", "10")
                .param("sortField", "")
                .param("sortOrder", "1"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(200).isEqualTo(status);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void findUserById() throws Exception {
        String uri = "/api/users/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(200).isEqualTo(status);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void newUser() throws Exception {
        String uri = "/api/users";
        User user = new User("user2", "user2", Role.ROLE_USER);
        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(200).isEqualTo(status);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateUser() throws Exception {
        String uri = "/api/users/1";
        User user = new User();
        user.setRole(Role.ROLE_ADMIN);
        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(200).isEqualTo(status);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteUserById() throws Exception {
        String uri = "/api/users/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(204).isEqualTo(status);
    }
}