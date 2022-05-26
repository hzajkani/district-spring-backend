package com.coding.district.repository;

import com.coding.district.domain.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DistrictRepository extends PagingAndSortingRepository<District, Long> {

    Page<District> findAll(Pageable pageable);

    List<District> findAll();

    List<District> findAll(Sort sort);
}
