package com.example.reqru2.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;



@Data
@Entity
@Table(name="localization")
public class Localization {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name="device_id")

        @Min(value = 5, message = "Device id must have  5 characters")
        @NotNull
        private  Long deviceId;

        @NotNull(message = "Latitiude id is required")

@Min(value = 6, message = "Device id must have min  5 characters")

 @Column(name="latitiude")
        private int latitiude;
       @Min(value = 6, message = "Device id must have 6 characters")

      @NotNull(message = "Latitiude id is required")
        @Column(name="longitude")
        private int longitude;

    @CreationTimestamp
    private Timestamp time;


}
