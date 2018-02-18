package br.com.dgcsistemas.senior_xpto.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.dgcsistemas.senior_xpto.model.Cidade;
import br.com.dgcsistemas.senior_xpto.model.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	 public final static String QUERY_ESTADO_MENOS_CIDADES = 
	         "SELECT new br.com.dgcsistemas.senior_xpto.model.Estado(cid.estado, count(cid.estado) AS contador) " + 
	         " FROM Cidade cid WHERE cid.estado <> 'DF' GROUP BY cid.estado ORDER BY contador ASC";
	 
	 public final static String QUERY_ESTADO_MAIS_CIDADES = 
	         "SELECT new br.com.dgcsistemas.senior_xpto.model.Estado(cid.estado, count(cid.estado) AS contador) " + 
	         " FROM Cidade cid GROUP BY cid.estado ORDER BY contador DESC";
	
	  @Query("select cid from #{#entityName} cid where capital is not " +
	          "null and capital <> '' order by nome")
	  List<Cidade> capitais(); 
	  
	  List<Cidade> findDistinctCapitalByCapitalOrderByNomeAsc(String capital);
	
	  @Query(QUERY_ESTADO_MENOS_CIDADES)
	  List<Estado> estadoMenosCidades();

	  @Query(QUERY_ESTADO_MAIS_CIDADES)
	  List<Estado> estadoMaisCidades();
	  
	  @Query(QUERY_ESTADO_MENOS_CIDADES)
	  List<Estado> quantidadePorEstado(); 
	  
	  List<Cidade> findByIdIbge(Integer idIbge);
	  
	  List<Cidade> findByEstado(String estado);
	  
	  @Query(QUERY_ESTADO_MENOS_CIDADES)
	  List<Estado> listaEstado(); 	
	  
	  @Query("SELECT cid FROM #{#entityName} cid" +
	         " WHERE ( ('id' = :nomeCampo AND CAST(cid.id as string) = :valorCampo )" +
			    " OR ('idIbge' = :nomeCampo AND CAST(cid.idIbge as string) = :valorCampo) " + 
			    " OR ('estado' = :nomeCampo AND cid.estado = :valorCampo) " +
			    " OR ('nome' = :nomeCampo AND cid.nome = :valorCampo) " + 
			    " OR ('nomeSemAcento' = :nomeCampo AND cid.nomeSemAcento = :valorCampo) " + 
			    " OR ('nomeAlternativo' = :nomeCampo AND cid.nomeAlternativo = :valorCampo) " + 
			    " OR ('capital' = :nomeCampo AND cid.capital = :valorCampo) " + 
			    " OR ('microregiao' = :nomeCampo AND cid.microregiao = :valorCampo) " +
			    " OR ('mesoregiao' = :nomeCampo AND cid.mesoregiao = :valorCampo) " +
			    " OR ('latitude' = :nomeCampo AND CAST(cid.latitude as string) = :valorCampo) " +
			    " OR ('longitude' = :nomeCampo AND CAST(cid.longitude as string) = :valorCampo) " +			    
	               " )")
	  List<Cidade> findByNomeCampo(@Param("nomeCampo") String nomeCampo,
	                               @Param("valorCampo") String valorCampo);

	  @Query(value = "SELECT count(distinct cid.idIbge) FROM #{#entityName} cid") 
	  Integer quantidadeRegistroIdIbge();
	  
	  @Query(value = "SELECT count(distinct cid.estado) FROM #{#entityName} cid") 
	  Integer quantidadeRegistroEstado();
	  
	  @Query(value = "SELECT count(distinct cid.nome) FROM #{#entityName} cid ") 
	  Integer quantidadeRegistroNome();
	  
	  @Query(value = "SELECT count(distinct cid.nomeSemAcento) FROM #{#entityName} cid ") 
	  Integer quantidadeRegistroNomeSemAcento();

	  @Query(value = "SELECT count(distinct cid.nomeAlternativo) FROM #{#entityName} cid ") 
	  Integer quantidadeRegistroNomeAlternativo();

	  @Query(value = "SELECT count(distinct cid.capital) FROM #{#entityName} cid ") 
	  Integer quantidadeRegistroCapital();

	  @Query(value = "SELECT count(distinct cid.mesoregiao) FROM #{#entityName} cid ")
	  Integer quantidadeRegistroMesoregiao();
	  
	  @Query(value = "SELECT count(distinct cid.microregiao) FROM #{#entityName} cid ")
	  Integer quantidadeRegistroMicroregiao();

	  @Query(value = "SELECT count(distinct cid.latitude) FROM #{#entityName} cid ") 
	  Integer quantidadeRegistroLatitude();

	  @Query(value = "SELECT count(distinct cid.longitude) FROM #{#entityName} cid ") 
	  Integer quantidadeRegistroLongitude();
}
