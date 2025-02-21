package com.example.Sklep_z_ksiazkami.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShippingMethodId implements Serializable {
    @Column(name = "Id_klienta")
    private Integer clientId;

    @Column(name = "Sposob_wysylki")
    private String shippingMethod;

    public ShippingMethodId() {}

    public ShippingMethodId(Integer clientId, String shippingMethod) {
        this.clientId = clientId;
        this.shippingMethod = shippingMethod;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingMethodId that = (ShippingMethodId) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(shippingMethod, that.shippingMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, shippingMethod);
    }
}
