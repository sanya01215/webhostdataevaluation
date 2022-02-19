package model.data.category;

import java.util.Objects;

public class CustomerServiceType {
    private int serviceId;
    private int variationId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVariationId() {
        return variationId;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerServiceType that = (CustomerServiceType) o;
        return serviceId == that.serviceId && variationId == that.variationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, variationId);
    }
}
