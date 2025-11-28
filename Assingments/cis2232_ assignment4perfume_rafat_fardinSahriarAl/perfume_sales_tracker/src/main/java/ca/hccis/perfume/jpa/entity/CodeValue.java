/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.hccis.perfume.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bjmaclean
 */
@Entity
@Table(name = "CodeValue")
public class CodeValue implements Serializable {

    // 1. PRIMARY KEY DEFINITION
    // This tells JPA the key is complex and defined in CodeValueId
    @EmbeddedId
    private CodeValueId id; // <-- MUST use the CodeValueId class

    // 2. REGULAR COLUMNS
    // Note: codeTypeId and codeValueSequence are NOT defined here,
    // as they are part of the 'id' object.

    // ... other columns like englishDescription, sortOrder, etc., go here ...





    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "englishDescription")
    private String englishDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "englishDescriptionShort")
    private String englishDescriptionShort;
    @Size(max = 100)
    @Column(name = "frenchDescription")
    private String frenchDescription;
    @Size(max = 20)
    @Column(name = "frenchDescriptionShort")
    private String frenchDescriptionShort;
    @Column(name = "sortOrder")
    private Integer sortOrder;
    @Column(name = "createdDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;
    @Size(max = 20)
    @Column(name = "createdUserId")
    private String createdUserId;
    @Column(name = "updatedDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDateTime;
    @Size(max = 20)
    @Column(name = "updatedUserId")
    private String updatedUserId;

    public CodeValue() {
    }
    public CodeValueId getId() { return id; }
    public void setId(CodeValueId id) { this.id = id; }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getEnglishDescriptionShort() {
        return englishDescriptionShort;
    }

    public void setEnglishDescriptionShort(String englishDescriptionShort) {
        this.englishDescriptionShort = englishDescriptionShort;
    }

    public String getFrenchDescription() {
        return frenchDescription;
    }

    public void setFrenchDescription(String frenchDescription) {
        this.frenchDescription = frenchDescription;
    }

    public String getFrenchDescriptionShort() {
        return frenchDescriptionShort;
    }

    public void setFrenchDescriptionShort(String frenchDescriptionShort) {
        this.frenchDescriptionShort = frenchDescriptionShort;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
}

