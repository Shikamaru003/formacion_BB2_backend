package formacion.bb2.dtos;


import java.util.HashSet;
import java.util.Set;


public class SupplierDto {
    private Long id;

    private String name;

    private String country;

    private final Set<ProductDto> products = new HashSet<>();

    public SupplierDto() {
        super();
    }

    public SupplierDto(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }
}