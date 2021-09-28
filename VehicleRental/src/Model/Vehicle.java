package Model;

import java.util.Date;
import java.util.UUID;

public abstract class Vehicle {

    UUID id;

    String barcodeNumber;

    Double rate;

    Integer parkingStall;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getParkingStall() {
        return parkingStall;
    }

    public void setParkingStall(Integer parkingStall) {
        this.parkingStall = parkingStall;
    }
}
