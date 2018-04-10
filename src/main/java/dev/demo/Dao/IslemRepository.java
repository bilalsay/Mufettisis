package dev.demo.Dao;

import dev.demo.Entity.Isemri;
import dev.demo.Entity.Islem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface IslemRepository extends JpaRepository<Islem, Integer> {

	public List<Islem> findByIsemriOrderByYayinTarihAsc(Isemri isemri);
	
	public List<Islem> findByIsemriAndDurumOrderByYayinTarihAsc(Isemri isemri, int durum);
	
	public List<Islem> findByIsemriAndDurumAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, int durum, String icerik);
	
	public List<Islem> findByIsemriAndDurumAndKategoriOrderByYayinTarihAsc(Isemri isemri, int durum, int kategori);
	
	public List<Islem> findByIsemriAndDurumAndKategoriAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, int durum, int kategori, String icerik);
	
	
	@Transactional
    public List<Islem> removeById(int id);
}
