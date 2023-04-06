package com.cpan282.clotheswarehouse.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cpan282.clotheswarehouse.model.Cloth;

/*
 * This interface will extend PagingAndSortingRepository, which will allow us to retrieve clothes in pages.
 * We need to implement PageRequest( we provide how many elements we want to query and show it on the screen so we don't do filtering.)
 */
public interface ClothRepositoryPaginated extends PagingAndSortingRepository<Cloth, Long> {
    
}
