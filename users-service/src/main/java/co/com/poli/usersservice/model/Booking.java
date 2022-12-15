package co.com.poli.usersservice.model;

import lombok.Data;

import java.util.Collection;

@Data
public class Booking {

    private Long id;
    private Long userid;
    private Long showtimeid;
    private Collection<Long> movies;

}
