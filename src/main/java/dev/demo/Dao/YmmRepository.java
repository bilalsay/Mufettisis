package dev.demo.Dao;

import dev.demo.Entity.Mukellef;
import dev.demo.Entity.Ymm;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface YmmRepository extends JpaRepository<Ymm, Integer> {

	 @Transactional
	 public List<Ymm> removeById(int id);
	 
	 @Query("SELECT Y FROM Ymm Y WHERE Y.id = :id AND Y.mukellef = :mukellef") // Bu query lerde Entity e sorgu yaziyoruz, veritabanindaki tabloya değil
	 public Ymm findByIdAndMukellef(@Param("id") int id, @Param("mukellef") Mukellef mukellef);
}
