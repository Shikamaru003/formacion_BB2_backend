package formacion.bb2.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "product_id", nullable = false)
    private Long id;

    @NotNull
    @Column(unique = true)
    private int itemCode;

    @NotBlank
    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    private String deactivateReason;

    private String deactivateUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_supplier", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<Supplier> suppliers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_price_reduction", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "price_reduction_id"))
    private Set<PriceReduction> priceReductions = new HashSet<>();

    private Date creationDate;

    private String creator;

    public Product() {
        super();
    }

    public Product(int itemCode, String description, BigDecimal price, State state, Set<Supplier> suppliers,
                   Set<PriceReduction> priceReductions, Date creationDate, String creator) {
        super();
        this.itemCode = itemCode;
        this.description = description;
        this.price = price;
        this.state = state;
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

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<PriceReduction> getPriceReductions() {
        return priceReductions;
    }

    public void setPriceReductions(Set<PriceReduction> priceReductions) {
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