package my.app.finalAtt.service;

import my.app.finalAtt.model.Client;
import my.app.finalAtt.model.Commons;
import my.app.finalAtt.repository.CommonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommonsServiceImpl implements CommonsService {
    @Autowired
    CommonsRepository commonsRepository;

    @Override
    public void create(Commons commons) {
        commonsRepository.save(commons);
    }

    @Override
    public List<Commons> readAll() {
        return commonsRepository.findAll();
    }

    @Override
    public List<Commons> readByCategory(String category) {
        return commonsRepository.findByCategory(category);
    }

    @Override
    public Commons read(long id) {
        return commonsRepository.findById(id).orElse(null);
    }

    @Override
    public int shipmentFromWarehouse(Commons commons, long id) {
        if (commonsRepository.existsById(id)) {
            commons.setId(id);
            commonsRepository.save(commons);
            return 1;
        }
        return -1;
    }

    @Override
    public boolean delete(long id) {
        return commonsRepository.deleteById(id);
    }

    @Override
    public void shipmentFromWarehouse(HashMap<Long, Double> warehouse) {
        warehouse.forEach((k, v) -> {
            final Commons commons = commonsRepository.findById(k).orElse(null);
            commons.setCommonsVolume(v);
            commonsRepository.save(commons);
        });

    }
}
