package formacion.bb2.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PriceReductionDto {

    private Long id;
    private String reducedPrice;
    private Date startDate;
    private Date endDate;
    private Set<ProductDto> products;

    public PriceReductionDto(Long id, String reducedPrice, Date startDate, Date endDate, Set<ProductDto> products) {
        this.id = id;
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.products = products;
    }
}
