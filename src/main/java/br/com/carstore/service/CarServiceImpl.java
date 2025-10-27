package br.com.carstore.service;

import br.com.carstore.dao.CarJpaDao;
import br.com.carstore.dto.CarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarJpaDao carJpaDao;

    public CarServiceImpl(CarJpaDao carJpaDao) {
        this.carJpaDao = carJpaDao;
    }

    @Override
    public List<CarDTO> findAll() {
        return carJpaDao.findAll();
    }

    @Override
    public void save(CarDTO carDTO) {
        carJpaDao.save(carDTO);
    }

    @Override
    public void deleteById(String id) {
        carJpaDao.deleteById(id);
    }

    @Override
    public void update(String id, CarDTO carDTO) {
        carJpaDao.update(id,carDTO);
    }

    @Override
    public CarDTO findById(String id) {
        return carJpaDao.findById(id);
    }
}
