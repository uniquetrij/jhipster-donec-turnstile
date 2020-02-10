package org.jhipster.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Turnstile.
 */
@Entity
@Table(name = "turnstile")
public class Turnstile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "tb_controller_id")
    private String tbControllerId;

    @Column(name = "tb_display_id")
    private String tbDisplayId;

    @Column(name = "camera_id")
    private String cameraId;

    @Column(name = "x_1")
    private Double x1;

    @Column(name = "y_1")
    private Double y1;

    @Column(name = "x_2")
    private Double x2;

    @Column(name = "y_2")
    private Double y2;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Turnstile identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTbControllerId() {
        return tbControllerId;
    }

    public Turnstile tbControllerId(String tbControllerId) {
        this.tbControllerId = tbControllerId;
        return this;
    }

    public void setTbControllerId(String tbControllerId) {
        this.tbControllerId = tbControllerId;
    }

    public String getTbDisplayId() {
        return tbDisplayId;
    }

    public Turnstile tbDisplayId(String tbDisplayId) {
        this.tbDisplayId = tbDisplayId;
        return this;
    }

    public void setTbDisplayId(String tbDisplayId) {
        this.tbDisplayId = tbDisplayId;
    }

    public String getCameraId() {
        return cameraId;
    }

    public Turnstile cameraId(String cameraId) {
        this.cameraId = cameraId;
        return this;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public Double getx1() {
        return x1;
    }

    public Turnstile x1(Double x1) {
        this.x1 = x1;
        return this;
    }

    public void setx1(Double x1) {
        this.x1 = x1;
    }

    public Double gety1() {
        return y1;
    }

    public Turnstile y1(Double y1) {
        this.y1 = y1;
        return this;
    }

    public void sety1(Double y1) {
        this.y1 = y1;
    }

    public Double getx2() {
        return x2;
    }

    public Turnstile x2(Double x2) {
        this.x2 = x2;
        return this;
    }

    public void setx2(Double x2) {
        this.x2 = x2;
    }

    public Double gety2() {
        return y2;
    }

    public Turnstile y2(Double y2) {
        this.y2 = y2;
        return this;
    }

    public void sety2(Double y2) {
        this.y2 = y2;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Turnstile)) {
            return false;
        }
        return id != null && id.equals(((Turnstile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Turnstile{" +
            "id=" + getId() +
            ", identifier='" + getIdentifier() + "'" +
            ", tbControllerId='" + getTbControllerId() + "'" +
            ", tbDisplayId='" + getTbDisplayId() + "'" +
            ", cameraId='" + getCameraId() + "'" +
            ", x1=" + getx1() +
            ", y1=" + gety1() +
            ", x2=" + getx2() +
            ", y2=" + gety2() +
            "}";
    }
}
