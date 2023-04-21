package com.cpan282.clotheswarehouse.model;

import java.math.BigDecimal;
import java.time.LocalDate;


// import org.springframework.data.annotation.Id;           // we use this with jdbc dependency.
// import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Table
/*
 * By using @Table we are telling Spring data to map the table name to actual table.
 * We are telling we have a database with a Fighter Table and please consider this class a model for fighter table.
 * It is used when we have jdbc dependency in pom.xml.
 */
@Entity
/*
 * @Entity is a JPA annotation to make this object ready for storage in a JPA based data store(required).
 */

// Created model for cloth.
public class Cloth {

    @Id      // this annotation makes the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Min(2021)
    private int yearOfCreation;
    @Min(1000)
    private double price;
    @Min(1)
    private int quantity;
    private Brand brandFrom;
    
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public enum Brand{
        
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior");
        private String title;

        private Brand(String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }
    }
}
