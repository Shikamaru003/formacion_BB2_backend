package BB2.formacion.controllers;

import BB2.formacion.models.Supplier;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;


public class SupplierControllerTest extends ControllerTest {

    @Test
    public void getAllSuppliers() throws Exception {
        String uri = "/api/suppliers/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
        String content = mvcResult.getResponse().getContentAsString();
        Supplier[] suppliers = mapFromJson(content, Supplier[].class);
        assertThat(suppliers.length > 0).isTrue();
    }

    @Test
    public void getSupplierById() throws Exception {
        String uri = "/api/suppliers/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getAvailableSuppliers() throws Exception {
        String uri = "/api/products/1/available_suppliers";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getSuppliersWithPriceReduction() throws Exception {
        String uri = "/api/suppliers/with_price_reductions";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }
}