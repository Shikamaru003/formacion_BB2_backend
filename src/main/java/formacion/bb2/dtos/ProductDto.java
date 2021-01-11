package formacion.bb2.dtos;

import formacion.bb2.models.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private int itemCode;
    private String description;
    private BigDecimal price;
    private State state;
    private String deactivateReason;
    private String deactivateUser;
    private Set<SupplierDto> suppliers = new HashSet<>();
    private Set<PriceReductionDto> priceReductions = new HashSet<>();
    private Date creationDate;
    private String creator;

    public ProductDto(Long id, int itemCode, String description, BigDecimal price, State state, String deactivateReason, String deactivateUser, Set<SupplierDto> suppliers, Set<PriceReductionDto> priceReductions, Date creationDate, String creator) {
        this.id = id;
        this.itemCode = itemCode;
        this.description = description;
        this.price = price;
        this.state = state;
        this.deactivateReason = deactivateReason;
        this.deactivateUser = deactivateUser;
        this.suppliers = suppliers;
        this.priceReductions = priceReductions;
        this.creationDate = creationDate;
        this.creator = creator;
    }


}