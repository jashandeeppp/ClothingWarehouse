package com.cpan282.clotheswarehouse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cpan282.clotheswarehouse.model.Cloth;
import com.cpan282.clotheswarehouse.model.Cloth.Brand;

/*
 * JPA provides us an option to symentically describe query by name without using real sql query.
 * So we described the query below.
 */

public interface ClothRepository extends CrudRepository<Cloth, Long>{

    List<Cloth> findByBrandFromAndYearOfCreation(Brand brand, String year);

}
