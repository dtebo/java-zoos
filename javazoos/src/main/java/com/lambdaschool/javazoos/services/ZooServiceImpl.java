package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Telephone;
import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ZooServiceImpl implements ZooService {
    @Autowired
    ZooRepository zoorepos;

    @Autowired
    AnimalService animalService;

    public Zoo findByZooId(long id) throws
            EntityNotFoundException {
        return zoorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zoo " + id + " Not Found!"));
    }

    @Override
    public List<Zoo> findAll() {
        List<Zoo> list = new ArrayList<>();

        zoorepos.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<Zoo> findByNameContaining(String name) {
        return zoorepos.findByZoonameContainingIgnoreCase(name.toLowerCase());
    }

    @Override
    public Zoo findByName(String name) {
        Zoo zz = zoorepos.findByZooname(name.toLowerCase());

        if(zz == null){
            throw new EntityNotFoundException("Zoo " + zz.getZooid() + " Not Found!");
        }

        return zz;
    }

    @Transactional
    @Override
    public void delete(long id) {
        zoorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zoo " + id + " Not Found!"));

        zoorepos.deleteById(id);
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo) {
        Zoo newZoo = new Zoo();

        if(newZoo.getZooid() != 0){
            zoorepos.findById(newZoo.getZooid())
                    .orElseThrow(() -> new EntityNotFoundException("Zoo " + newZoo.getZooid() + " Not Found!"));

            newZoo.setZooid(zoo.getZooid());
        }

        newZoo.setZooname(zoo.getZooname());

        newZoo.getTelephones().clear();

        for(Telephone t : zoo.getTelephones()){
            Telephone newPhone = new Telephone();
            newPhone.setPhoneid(t.getPhoneid());
            newPhone.setPhonenumber(t.getPhonenumber());
            newPhone.setPhonetype(t.getPhonetype());
            newPhone.setZoo(t.getZoo());

            zoo.getTelephones().add(newPhone);
        }

        return zoorepos.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo update(Zoo zoo, long id) {
        Zoo currentZoo = findByZooId(id);

        if(zoo.getZooname() != null){
            currentZoo.setZooname(zoo.getZooname());
        }

        if(zoo.getTelephones().size() > 0){
            currentZoo.getTelephones().clear();

//            for(Telephone t : zoo.getTelephones()){
//                Telephone newTelphone = zooS;
//            }
        }

        return currentZoo;
    }

    @Override
    public void deleteAll() {
        zoorepos.deleteAll();
    }
}
