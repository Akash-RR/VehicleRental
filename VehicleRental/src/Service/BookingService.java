package Service;

import Exceptions.BookingOverlappingException;
import Exceptions.NoBookingFoundException;
import Model.Booking;
import Model.Vehicle;
import Repository.VehicleRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class BookingService {

    VehicleRepository vehicleRepository;

    BookingService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Booking createBooking(Vehicle vehicle, Date startDate, long duration){
        Booking booking =  new Booking();
        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + duration);
        vehicleRepository.getBookingsInventory().get(vehicle).forEach(bookedItem ->{
            Date bookItemEndDate = new Date();
            bookItemEndDate.setTime(bookedItem.getStartDate().getTime() + bookedItem.getDuration());
            if(!(startDate.after(bookItemEndDate)
                    || (startDate.before(bookedItem.getStartDate()) && endDate.before(bookedItem.getStartDate())))){
                throw new BookingOverlappingException("Booking Already present For Duration");
            }
        });
        booking.setId(UUID.randomUUID());
        booking.setDuration(duration);
        booking.setVehicle(vehicle);
        booking.setStartDate(startDate);
        vehicleRepository.addBooking(booking);
        return booking;
    }

    public double calculateFare(UUID bookingId){
        Optional<Booking> booking = vehicleRepository.getBookingList().stream().filter(bookingItem -> bookingItem.getId().equals(bookingId)).findFirst();
        if (booking.isPresent()){
            Booking book = booking.get();
            return book.getDuration() * book.getVehicle().getRate();
        }else{
            throw new NoBookingFoundException("No Such Booking Found");
        }
    }
}
