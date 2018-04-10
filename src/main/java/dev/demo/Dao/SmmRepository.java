package dev.demo.Dao;

import dev.demo.Entity.Mukellef;
import dev.demo.Entity.Smm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface SmmRepository extends JpaRepository<Smm, Integer> {

	 @Transactional
	 public List<Smm> removeById(int id);
	 
	 @Query("SELECT S FROM Smm S WHERE S.id = :id AND S.mukellef = :mukellef") // Bu query lerde Entity e sorgu yaziyoruz, veritabanindaki tabloya değil
	 public Smm findByIdAndMukellef(@Param("id") int id, @Param("mukellef") Mukellef mukellef);
}
