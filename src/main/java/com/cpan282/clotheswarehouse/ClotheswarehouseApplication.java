package com.cpan282.clotheswarehouse;

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
				.price(10000)
				.quantity(5)
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Jean")
				.yearOfCreation(2023)
				.price(2000)
				.quantity(6)
				.brandFrom(Brand.DIOR).build());

			clothRepository.save(Cloth.builder()
				.name("Jacket")
				.yearOfCreation(2021)
				.price(20000)
				.quantity(8)
				.brandFrom(Brand.STONE_ISLAND).build());

			clothRepository.save(Cloth.builder()
				.name("Shirt")
				.yearOfCreation(2024)
				.price(1000)
				.quantity(11)
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Formal Shirt")
				.yearOfCreation(2022)
				.price(10000)
				.quantity(12)
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Formal Pant")
				.yearOfCreation(2023)
				.price(2000)
				.quantity(4)
				.brandFrom(Brand.DIOR).build());

			clothRepository.save(Cloth.builder()
				.name("Dress")
				.yearOfCreation(2021)
				.price(20000)
				.quantity(20)
				.brandFrom(Brand.STONE_ISLAND).build());

			clothRepository.save(Cloth.builder()
				.name("Skirt")
				.yearOfCreation(2024)
				.price(1000)
				.quantity(11)
				.brandFrom(Brand.BALENCIAGA).build());
			
			clothRepository.save(Cloth.builder()
				.name("Kurta Pajama")
				.yearOfCreation(2022)
				.price(10000)
				.quantity(25)
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Jalsa")
				.yearOfCreation(2023)
				.price(2000)
				.quantity(8)
				.brandFrom(Brand.DIOR).build());

			clothRepository.save(Cloth.builder()
				.name("Snow Jacket")
				.yearOfCreation(2021)
				.price(20000)
				.quantity(13)
				.brandFrom(Brand.STONE_ISLAND).build());

			clothRepository.save(Cloth.builder()
				.name("Snow Shoes")
				.yearOfCreation(2024)
				.price(1000)
				.quantity(17)
				.brandFrom(Brand.BALENCIAGA).build());
			
			clothRepository.save(Cloth.builder()
				.name("Gloves")
				.yearOfCreation(2022)
				.price(10000)
				.quantity(19)
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Pant")
				.yearOfCreation(2023)
				.price(2000)
				.quantity(2)
				.brandFrom(Brand.DIOR).build());

			clothRepository.save(Cloth.builder()
				.name("Socks")
				.yearOfCreation(2021)
				.price(20000)
				.quantity(7)
				.brandFrom(Brand.STONE_ISLAND).build());

			clothRepository.save(Cloth.builder()
				.name("Snow Socks")
				.yearOfCreation(2024)
				.price(1000)
				.quantity(3)
				.brandFrom(Brand.BALENCIAGA).build());
			
			clothRepository.save(Cloth.builder()
				.name("Hoodie")
				.yearOfCreation(2022)
				.price(10000)
				.quantity(2)
				.brandFrom(Brand.BALENCIAGA).build());

			clothRepository.save(Cloth.builder()
				.name("Coat Pant")
				.yearOfCreation(2023)
				.price(2000)
				.quantity(9)
				.brandFrom(Brand.DIOR).build());

			clothRepository.save(Cloth.builder()
				.name("Blazer")
				.yearOfCreation(2021)
				.price(20000)
				.quantity(18)
				.brandFrom(Brand.STONE_ISLAND).build());

			clothRepository.save(Cloth.builder()
				.name("Basket")
				.yearOfCreation(2024)
				.price(1000)
				.quantity(21)
				.brandFrom(Brand.BALENCIAGA).build());
			
		};
	}
}
