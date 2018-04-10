package dev.demo.Dao;

import dev.demo.Entity.Mukellef;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface MukellefRepository extends JpaRepository<Mukellef, Integer> {

    public List<Mukellef> findAllByOrderByKayitTarihDesc();
    
    public Page<Mukellef> findAllByOrderByKayitTarihDescMukellefIdDesc(Pageable pageable);
    
    public Page<Mukellef> findAllByUnvanContainingOrderByKayitTarihDescMukellefIdDesc(String unvan, Pageable pageable);
    
    public List<Mukellef> findAllByOrderByUnvanAsc();
    
    @Transactional
    public List<Mukellef> removeByMukellefId(int mukellefId);
    
    public Mukellef findByMukellefVergiKimlikNo(String mukellefVergiKimlikNo);

}
