package dev.demo.Dao;

import dev.demo.Entity.Isemri;
import dev.demo.Entity.Mukellef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface IsemriRepository extends JpaRepository<Isemri, Integer> {

    public List<Isemri> findAllByOrderByIsemriYayinTarihDesc();
    
    public Page<Isemri> findAllByOrderByIsemriYayinTarihDescIdDesc(Pageable pageable);
    
    public Page<Isemri> findAllByMukellefOrderByIsemriYayinTarihDescIdDesc(Mukellef mukellef, Pageable pageable);

    @Transactional
    public List<Isemri> removeById(int id);

	public Page<Isemri> findAllByIsemriNoContainingOrderByIsemriYayinTarihDescIdDesc(String searchString, Pageable pageable);

	public Page<Isemri> findAllByMukellefAndIsemriNoContainingOrderByIsemriYayinTarihDescIdDesc(Mukellef mukellef, String searchString, Pageable pageable);

}
