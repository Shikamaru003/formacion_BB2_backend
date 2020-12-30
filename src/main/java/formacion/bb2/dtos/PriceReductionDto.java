package formacion.bb2.dtos;

import java.util.Date;
import java.util.Set;

public class PriceReductionDto {

    private Long id;

    private String reducedPrice;

    private Date startDate;

    private Date endDate;

    private Set<ProductDto> products;

    public PriceReductionDto() {
        super();
    }

    public PriceReductionDto(Long id, String reducedPrice, Date startDate, Date endDate, Set<ProductDto> products) {
        this.id = id;
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(String reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
