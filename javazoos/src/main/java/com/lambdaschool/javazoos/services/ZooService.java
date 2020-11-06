package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Zoo;

import java.util.List;

public interface ZooService {
    List<Zoo> findAll();

    List<Zoo> findByNameContaining(String name);

    Zoo findByZooId(long id);

    Zoo findByName(String name);

    void delete(long id);

    Zoo save(Zoo zoo);

    Zoo update(Zoo zoo, long id);

    public void deleteAll();
}
