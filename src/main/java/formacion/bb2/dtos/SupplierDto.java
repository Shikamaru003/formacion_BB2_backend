package formacion.bb2.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SupplierDto {

    private Long id;
    private String name;
    private String country;
    private final Set<ProductDto> products = new HashSet<>();

    public SupplierDto(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}