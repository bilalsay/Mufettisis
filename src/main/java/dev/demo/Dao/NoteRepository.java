package dev.demo.Dao;

import dev.demo.Entity.Isemri;
import dev.demo.Entity.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bilalsay on 21/09/2017.
 */

public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	public List<Note> findByIsemriOrderByYayinTarihAsc(Isemri isemri);
	
	public List<Note> findByIsemriAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, String icerik);
	
	public List<Note> findByIsemriAndKategoriOrderByYayinTarihAsc(Isemri isemri, int kategori);
	
	public List<Note> findByIsemriAndKategoriAndIcerikContainingOrderByYayinTarihAsc(Isemri isemri, int kategori, String icerik);
	
	@Transactional
    public List<Note> removeById(int id);
	
}
