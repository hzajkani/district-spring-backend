package com.coding.district.repository;

import com.coding.district.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByNameContains(String name);

    // These kinds of Query also usable
    @Query("SELECT v FROM Vote v WHERE v.name LIKE %:name%")
    List<Vote> findByNameLike(String name);

}
