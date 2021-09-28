package Repository;

import Model.Booking;
import Model.Vehicle;

import java.util.*;

import static java.util.Objects.isNull;

public class VehicleRepository {

    Set<Booking> bookingList;

    Set<Vehicle> vehicles;

    Map<Vehicle, List<Booking>> bookingsInventory;

    public void addBooking(Booking booking){
        this.bookingList.add(booking);
        if(isNull(bookingsInventory.get(booking.getVehicle()))){
            bookingsInventory.put(booking.getVehicle(), Collections.singletonList(booking));
            return;
        }
        bookingsInventory.get(booking.getVehicle()).add(booking);
    }

    public Set<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(Set<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Map<Vehicle, List<Booking>> getBookingsInventory() {
        return bookingsInventory;
    }

    public void setBookingsInventory(Map<Vehicle, List<Booking>> bookingsInventory) {
        this.bookingsInventory = bookingsInventory;
    }
}
