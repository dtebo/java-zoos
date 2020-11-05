package com.lambdaschool.javazoos.repositories;

import com.lambdaschool.javazoos.models.Animal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
    Animal findByNameIgnoringCase(String name);

    @Modifying
    @Query(value = "UPDATE animals SET animaltype = :type, lastmodifiedby = :uname, lastmodifieddate = CURRENT_TIMESTAMP" +
        "WHERE animalid = :animalid", nativeQuery = true)
    Animal updateTypeById(long animalid, String type, String uname);
}
