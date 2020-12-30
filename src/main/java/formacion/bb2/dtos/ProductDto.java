package formacion.bb2.dtos;

import formacion.bb2.models.State;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    public ProductDto() {
        super();
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDeactivateReason() {
        return deactivateReason;
    }

    public void setDeactivateReason(String deactivateReason) {
        this.deactivateReason = deactivateReason;
    }

    public String getDeactivateUser() {
        return deactivateUser;
    }

    public void setDeactivateUser(String deactivateUser) {
        this.deactivateUser = deactivateUser;
    }

    public Set<SupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<SupplierDto> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<PriceReductionDto> getPriceReductions() {
        return priceReductions;
    }

    public void setPriceReductions(Set<PriceReductionDto> priceReductions) {
        this.priceReductions = priceReductions;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}