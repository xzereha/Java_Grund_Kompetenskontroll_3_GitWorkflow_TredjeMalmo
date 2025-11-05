package com.example;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class.getName());
    private final BookingRepository bookingRepository;
    private final PriceList priceList = new PriceList();
    //private final EmailService emailService;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public boolean bookInspection(String regNr, String vehicleModel, int yearModel, String email, LocalDate date) {
        var regNr_ = new RegNr(regNr); // NOTE can throw
        var email_ = new Email(email); // NOTE can throw
        var vehicle = new Vehicle(regNr_, vehicleModel, yearModel);
        var price_ = priceList.getInspectionPrice();
        var booking = new Booking.Inspection(vehicle, email_, date, price_);

        return registerBooking(booking);
    }

    public boolean bookService(String regNr, String vehicleModel, int yearModel, String email, LocalDate date) {
        var regNr_ = new RegNr(regNr); // NOTE can throw
        var email_ = new Email(email); // NOTE can throw
        var vehicle = new Vehicle(regNr_, vehicleModel, yearModel);
        var price_ = priceList.getServicePrice(yearModel);
        var booking = new Booking.Service(vehicle, email_, date, price_);

        return registerBooking(booking);
    }

    public boolean bookRepair(String regNr, String vehicleModel, int yearModel, String email, LocalDate date, String repairDescription) {
        var regNr_ = new RegNr(regNr); // NOTE can throw
        var email_ = new Email(email); // NOTE can throw
        var vehicle = new Vehicle(regNr_, vehicleModel, yearModel);
        var booking = new Booking.Repair(vehicle, email_, date, repairDescription);

        return registerBooking(booking);
    }

    private boolean registerBooking(Booking booking) {
        boolean wasAdded = bookingRepository.addBooking(booking);

        return wasAdded;
    }


    private List<Booking> findBookingByRegNr(String regNr) {
        List<Booking> foundBooking = bookingRepository.getBookingList().stream()
                .filter(b -> b.getVehicle().getRegNr().getRegNr().equals(regNr))
                .toList();

        if (foundBooking.isEmpty()) {
            logger.info("No booking found for RegNr: {}", regNr);
        } else {
            logger.info("Booking found for RegNr: {}", regNr);
        }

        return foundBooking;
    }

    public boolean removeBooking(Booking booking) {
        return bookingRepository.removeBooking(booking);
    }

    /**
     * If the booking is a repair it needs a price to be set before it can be completed.
     * @param booking
     */
    public void changeBookingStatusToComplete(Booking booking){
        if((booking instanceof Booking.Repair)){
            throw new IllegalArgumentException("Booking is a repair and needs a price");
        }
        booking.setStatus(Booking.Status.COMPLETED);
    }
    public void changeBookingStatusToComplete(Booking booking, float price){
        if(!(booking instanceof Booking.Repair repair)){
            throw new IllegalArgumentException("Booking is not a repair booking");
        }
        repair.setStatus(Booking.Status.COMPLETED);
        repair.setPrice(price);
    }

    public void changeBookingStatusToPending(Booking booking){
        booking.setStatus(Booking.Status.PENDING);
    }
}
