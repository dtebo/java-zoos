package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Animal;
import com.lambdaschool.javazoos.repositories.AnimalRepository;
import com.lambdaschool.javazoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    ZooRepository zooRepository;

    @Autowired
    ZooAuditing zooAuditing;

    @Override
    public List<Animal> findAll(){
        List<Animal> list = new ArrayList<>();

        animalRepository.findAll()
                .iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Animal findAnimalById(long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal " + id + " Not Found!"));
    }

    @Override
    public Animal findByType(String type){
        Animal aa = animalRepository.findByTypeIgnoringCase(type);

        if(aa != null){
            return aa;
        }
        else{
            throw new EntityNotFoundException(type);
        }
    }

    @Transactional
    @Override
    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        animalRepository.deleteAll();
    }

    @Transactional
    @Override
    public Animal update(long id, Animal animal) {
        //check name is not null
        //valid role id
        //etc...
        animalRepository.updateTypeById(id, animal.getAnimaltype(), zooAuditing.getCurrentAuditor().get());
        return findAnimalById(id);
    }
}
