package br.com.livraria.livraria.repository;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.livraria.livraria.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>{
	
	@Query("update livro l set titulo = :titulo, data_lancamento = :datalancamento, descricao = :descricao, "
			+ "paginas = :paginas, preco = :preco, sumario_path = :sumariopath where id=:id")
	public Optional<Livro> editarLivro(@Param("id") Long id, @Param("titulo") String titulo,
			@Param("datalancamento") Calendar datalancamento,@Param("descricao") String descricao,
			@Param("paginas") int paginas, @Param("preco") double preco, @Param("sumariopath") String sumariopath);
}
