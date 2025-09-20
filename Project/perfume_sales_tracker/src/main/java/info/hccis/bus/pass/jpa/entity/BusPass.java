package info.hccis.bus.pass.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "buspass")
public class BusPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 1, max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Size(max = 20)
    @NotNull
    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Size(max = 10)
    @NotNull
    @Column(name = "preferredRoute", nullable = false, length = 10)
    private String preferredRoute;

    @NotNull
    @Column(name = "passType", nullable = false)
    private Integer passType;

    @NotNull
    @Column(name = "validForRuralRoute", nullable = false)
    private Boolean validForRuralRoute = false;

    @NotNull
    @Column(name = "lengthOfPass", nullable = false)
    private Integer lengthOfPass;

    @Size(max = 10)
    @NotNull
    @Column(name = "startDate", nullable = false, length = 10)
    private String startDate;

    @NotNull
    @Column(name = "cost", nullable = false, precision = 6, scale = 2)
    private BigDecimal cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPreferredRoute() {
        return preferredRoute;
    }

    public void setPreferredRoute(String preferredRoute) {
        this.preferredRoute = preferredRoute;
    }

    public Integer getPassType() {
        return passType;
    }

    public void setPassType(Integer passType) {
        this.passType = passType;
    }

    public Boolean getValidForRuralRoute() {
        return validForRuralRoute;
    }

    public void setValidForRuralRoute(Boolean validForRuralRoute) {
        this.validForRuralRoute = validForRuralRoute;
    }

    public Integer getLengthOfPass() {
        return lengthOfPass;
    }

    public void setLengthOfPass(Integer lengthOfPass) {
        this.lengthOfPass = lengthOfPass;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

}