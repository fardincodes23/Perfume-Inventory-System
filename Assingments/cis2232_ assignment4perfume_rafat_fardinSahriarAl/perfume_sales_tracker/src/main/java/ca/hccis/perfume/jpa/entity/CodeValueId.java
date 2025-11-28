package ca.hccis.perfume.jpa.entity; // Change package to match yours

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodeValueId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "codeTypeId", nullable = false)
    private Integer codeTypeId;

    @Column(name = "codeValueSequence", nullable = false)
    private Integer codeValueSequence;

    // Getters, Setters, HashCode, Equals (Paste instructor's code here)
    public Integer getCodeTypeId() { return codeTypeId; }
    public void setCodeTypeId(Integer codeTypeId) { this.codeTypeId = codeTypeId; }
    public Integer getCodeValueSequence() { return codeValueSequence; }
    public void setCodeValueSequence(Integer codeValueSequence) { this.codeValueSequence = codeValueSequence; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeValueId that = (CodeValueId) o;
        return Objects.equals(codeTypeId, that.codeTypeId) &&
                Objects.equals(codeValueSequence, that.codeValueSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeTypeId, codeValueSequence);
    }
}