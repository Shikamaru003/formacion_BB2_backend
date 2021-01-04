package formacion.bb2.controllers;

import formacion.bb2.dtos.ProductDto;
import formacion.bb2.models.State;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerTest extends ControllerTest {

    @Test
    public void getAllProducts() throws Exception {
        String uri = "/api/products/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
        String content = mvcResult.getResponse().getContentAsString();
        ProductDto[] products = mapFromJson(content, ProductDto[].class);
        assertThat(products.length > 0).isTrue();
    }

    @Test
    public void getProducts() throws Exception {
        String uri = "/api/products";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("page", "0")
                .param("size", "10")
                .param("sortField", "")
                .param("sortOrder", "1"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void findProductById() throws Exception {
        String uri = "/api/products/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void newProduct() throws Exception {
        String uri = "/api/products";
        ProductDto product = new ProductDto(null, 4563, "description", new BigDecimal("30.00"), State.ACTIVE, null, null, new HashSet<>(),
                new HashSet<>(), new Date(), "admin");
        String inputJson = mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void updateProduct() throws Exception {
        String uri = "/api/products/2";
        ProductDto product = new ProductDto();
        product.setDescription("Description");
        String inputJson = mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteProduct() throws Exception {
        String uri = "/api/products/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

}