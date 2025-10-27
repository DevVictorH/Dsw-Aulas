package br.com.carstore.dao;

import br.com.carstore.dto.CarDTO;
import br.com.carstore.entity.CarEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CarJpaDao {

    @PersistenceContext
    private EntityManager em;

    public List<CarDTO> findAll() {
        List<CarEntity> list = em.createQuery("SELECT c FROM CarEntity c", CarEntity.class).getResultList();
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public  void save(CarDTO dto){
        CarEntity e = new CarEntity();
        e.setName(dto.getName());
        e.setColor(dto.getColor());

        em.persist(e);

        dto.setId(e.getId() != null ? String.valueOf(e.getId()) : null);
    }

    public void deleteById(String id){
        Long pk = Long.valueOf(id);
        CarEntity e = em.find(CarEntity.class, pk);

        if(e != null){
            em.remove(e);
        }
    }

    public void update(String id, CarDTO dto){
        Long pk = Long.valueOf(id);
        CarEntity e = em.find(CarEntity.class, pk);

        if(e != null){
            e.setName(dto.getName());
            e.setColor(dto.getColor());
            em.merge(e);
        }
    }

    private CarDTO toDto(CarEntity e){
        CarDTO d = new CarDTO();
        d.setId(e.getId() != null ? String.valueOf(e.getId()) : null);
        d.setName(e.getName());
        d.setColor(e.getColor());

        return d;
    }

    public CarDTO findById(String id) {
        Long pk = Long.valueOf(id);
        CarEntity entity = em.find(CarEntity.class, pk);

        if (entity != null) {
            return toDto(entity);
        } else {
            return null;
        }
    }

}
