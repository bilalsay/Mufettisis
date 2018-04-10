package dev.demo.Dao;

import dev.demo.Entity.Isemri;
import dev.demo.Entity.Mulakat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface MulakatRepository extends JpaRepository<Mulakat, Integer> {
	
	public List<Mulakat> findByIsemriOrderByYayinTarihAsc(Isemri isemri);
	
	public List<Mulakat> findByIsemriAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, String icerik);
	
	public List<Mulakat> findByIsemriAndKategoriOrderByYayinTarihAsc(Isemri isemri, int kategori);
	
	public List<Mulakat> findByIsemriAndKategoriAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, int kategori, String icerik);
	
	@Transactional
    public List<Mulakat> removeById(int id);

    public List<Mulakat> findByIsemriAndDurumAndKategoriAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, int durum, int kategori, String searchString);

	List<Mulakat> findByIsemriAndDurumAndKategoriOrderByYayinTarihAsc(Isemri isemri, int durum, int kategori);

	List<Mulakat> findByIsemriAndDurumAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, int durum, String searchString);

	List<Mulakat> findByIsemriAndDurumOrderByYayinTarihAsc(Isemri isemri, int durum);
}
