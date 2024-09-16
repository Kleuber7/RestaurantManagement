package com.fiap.restaurant_management.config;

import com.fiap.restaurant_management.aplication.gateway.IBookingRepository;
import com.fiap.restaurant_management.aplication.gateway.ICustomerRepository;
import com.fiap.restaurant_management.aplication.gateway.IRestaurantRepository;
import com.fiap.restaurant_management.aplication.usecases.CreateBooking;
import com.fiap.restaurant_management.infra.controller.BookingController;
import com.fiap.restaurant_management.infra.gateways.BookingRepositoryImpl;
import com.fiap.restaurant_management.infra.mapper.BookingMapper;
import com.fiap.restaurant_management.infra.mapper.CustomerMapper;
import com.fiap.restaurant_management.infra.mapper.RestaurantMapper;
import com.fiap.restaurant_management.infra.persistence.repository.BookingRepository;
import com.fiap.restaurant_management.infra.persistence.repository.CustomerRepository;
import com.fiap.restaurant_management.infra.persistence.repository.RestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.fiap.restaurant_management.aplication.gateway")
public class BookingConfig {

    @Bean
    public BookingController bookingController(CreateBooking createBooking) {
        return new BookingController(createBooking);
    }

    @Bean
    public CreateBooking createBooking(
            IBookingRepository IBookingRepository,
            IRestaurantRepository IRestaurantRepository,
            ICustomerRepository ICustomerRepository) {

        return new CreateBooking(IBookingRepository, IRestaurantRepository, ICustomerRepository);
    }

    @Bean
    public BookingRepositoryImpl bookingsRepository(BookingRepository bookingRepository,
                                                   RestaurantRepository restaurantRepository,
                                                   CustomerRepository customerRepository,
                                                   BookingMapper bookingMapper) {
        return new BookingRepositoryImpl(bookingRepository, restaurantRepository, customerRepository, bookingMapper);
    }

    @Bean
    BookingMapper bookingMapper(CustomerMapper customerMapper, RestaurantMapper restaurantMapper) {
        return new BookingMapper(customerMapper, restaurantMapper);
    }


}
