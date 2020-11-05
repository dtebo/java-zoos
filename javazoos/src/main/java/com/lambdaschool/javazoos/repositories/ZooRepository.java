package com.lambdaschool.javazoos.repositories;

import com.lambdaschool.javazoos.models.Zoo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZooRepository extends CrudRepository<Zoo, Long> {
    Zoo findByZooname(String name);

    List<Zoo> findByZoonameContainingIgnoreCase(String name);
}
