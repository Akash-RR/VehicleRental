package Service;

import Model.Booking;
import Model.Vehicle;
import Repository.VehicleRepository;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class VehicleService {

    VehicleRepository vehicleRepository;

    VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Object locateVehicle(Vehicle vehicle){
        AtomicBoolean booked = new AtomicBoolean(false);
        AtomicReference<Booking> booking = null;
        vehicleRepository.getBookingsInventory().get(vehicle).forEach(bookedItem ->{
            Date currentDate= new Date();
            Date bookItemEndDate = new Date();
            bookItemEndDate.setTime(bookedItem.getStartDate().getTime() + bookedItem.getDuration());
            if(!(currentDate.before(bookedItem.getStartDate()) || currentDate.after(bookItemEndDate))){
                booked.set(true);
                booking.set(bookedItem);
            }
        });
        if (booked.get()){
            return booking.get();
        } else
        {
            return vehicle.getParkingStall();
        }
    }

    public Set<Vehicle> getRentedVehicles(){
        return vehicleRepository.getBookingsInventory().keySet();
    }
}
