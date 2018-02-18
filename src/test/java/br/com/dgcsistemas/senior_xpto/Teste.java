package br.com.dgcsistemas.senior_xpto;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.dgcsistemas.senior_xpto.model.Cidade;
import br.com.dgcsistemas.senior_xpto.repository.CidadeRepository;
import br.com.dgcsistemas.senior_xpto.service.CsvReaderService;

public class Teste {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	private List<Cidade> cidades; 
	private CsvReaderService csvReader;
	
	@BeforeClass 
	public static void init() {
		System.out.println("init() ");
	}
	
	@Before 
	public void setUp() {
		System.out.println("setUp() ");
	}
	
	@After 
	public void tearDown() {
		System.out.println("tearDown()");
	}
	
	@Test 
	public void projecoesTest() { 
		System.out.println("projecoesTest()");
    	
    	csvReader = new CsvReaderService();
    	csvReader.reader(cidadeRepository);
    	    	
		cidades = cidadeRepository.findAll();
		for (Cidade c : cidades) {
			System.out.println(c.getNome());
		}
	}
}