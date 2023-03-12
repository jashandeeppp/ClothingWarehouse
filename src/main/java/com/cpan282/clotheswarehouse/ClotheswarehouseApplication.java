package com.cpan282.clotheswarehouse;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cpan282.clotheswarehouse.model.Cloth;
import com.cpan282.clotheswarehouse.model.Cloth.Brand;
import com.cpan282.clotheswarehouse.repository.ClothRepository;

@SpringBootApplication
public class ClotheswarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClotheswarehouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataloader(ClothRepository clothRepository){
		return args -> {
			clothRepository.save(Cloth.builder()
				.name("Tshirt")
				.yearOfCreation(2022)
				.price(new BigDecimal(10000))
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Jean")
				.yearOfCreation(2023)
				.price(new BigDecimal(2000))
				.brandFrom(Brand.DIOR).build());

			clothRepository.save(Cloth.builder()
				.name("Jacket")
				.yearOfCreation(2021)
				.price(new BigDecimal(20000))
				.brandFrom(Brand.STONE_ISLAND).build());

			clothRepository.save(Cloth.builder()
				.name("Shirt")
				.yearOfCreation(2024)
				.price(new BigDecimal(1000))
				.brandFrom(Brand.BALENCIAGA).build());
		};
	}
}
