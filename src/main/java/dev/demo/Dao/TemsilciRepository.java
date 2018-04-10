package dev.demo.Dao;

import dev.demo.Entity.Mukellef;
import dev.demo.Entity.Smm;
import dev.demo.Entity.Temsilci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface TemsilciRepository extends JpaRepository<Temsilci, Integer> {

	 @Transactional
	 public List<Temsilci> removeById(int id);
	 
	 @Query("SELECT T FROM Temsilci T WHERE T.id = :id AND T.mukellef = :mukellef") // Bu query lerde Entity e sorgu yaziyoruz, veritabanindaki tabloya değil
	 public Smm findByIdAndMukellef(@Param("id") int id, @Param("mukellef") Mukellef mukellef);
}
