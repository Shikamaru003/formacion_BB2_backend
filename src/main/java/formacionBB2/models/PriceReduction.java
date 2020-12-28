package formacionBB2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PRICE_REDUCTIONS")
public class PriceReduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "price_reduction_id", nullable = false)
    private Long id;

    private String reducedPrice;

    private Date startDate;

    private Date endDate;

    @ManyToMany(mappedBy = "priceReductions", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products;

    public PriceReduction() {
        super();
    }

    public PriceReduction(String reducedPrice, Date startDate, Date endDate) {
        super();
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
