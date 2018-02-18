package br.com.dgcsistemas.senior_xpto.resource;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.dgcsistemas.senior_xpto.model.Cidade;
import br.com.dgcsistemas.senior_xpto.model.Estado;
import br.com.dgcsistemas.senior_xpto.repository.CidadeRepository;
import br.com.dgcsistemas.senior_xpto.service.CsvReaderService;
import br.com.dgcsistemas.senior_xpto.service.DistanciaCidadeService;
//import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(CidadeResource.CIDADE_BASE_URI)
public class CidadeResource {
	public static final String CIDADE_BASE_URI = "empresa/xpto";
	
	@Autowired
	private CidadeRepository cidadeRepository;
	private CsvReaderService csvReaderService;
	
	@PostConstruct
	public void init() {
	   csvReaderService = new CsvReaderService();
	   cidadeRepository.deleteAll();
	   csvReaderService.reader(cidadeRepository);
	}
		
	@GetMapping("/cidades")
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/cidades/capitais")
	public List<Cidade> capitais() {
	//	return cidadeRepository.capitais();
		return cidadeRepository.findDistinctCapitalByCapitalOrderByNomeAsc("true");
	}	

	@GetMapping("/cidades/estadoMenosCidades")
	public List<Estado> estadoMenosCidades() {
		List<Estado> listaEstado;
		
		listaEstado = cidadeRepository.estadoMenosCidades();
		Estado estado = listaEstado.get(0);
		
		listaEstado.clear();
		listaEstado.add(estado);
				 
		return listaEstado;
	}	

	@GetMapping("/cidades/estadoMaisCidades")
	public List<Estado> estadoMaisCidades() {
		List<Estado> listaEstado;
		
		listaEstado = cidadeRepository.estadoMaisCidades();
		Estado estado = listaEstado.get(0);
		
		listaEstado.clear();
		listaEstado.add(estado);
				 
		return listaEstado;
	}	
	
	@GetMapping("/cidades/quantidadePorEstado")
	public List<Estado> quantidadePorEstado() {
		return cidadeRepository.quantidadePorEstado();
	}	
		
	@RequestMapping(value = "/cidades/" + "{idIbge}", method = RequestMethod.POST)
	public List<Cidade> buscarIdIbge(@PathVariable final Integer idIbge) {
		return cidadeRepository.findByIdIbge(idIbge); 
	}

	@GetMapping("/estados")
	public List<Estado> estado() {
		return cidadeRepository.listaEstado();
	}	
	
	@RequestMapping(value = "/estados/" + "{estado}")
	public List<Cidade> cidadePorEstado(@PathVariable final String estado) {
		return cidadeRepository.findByEstado(estado);
	}		
	
	@PostMapping("/cidades")
	public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	  
    @RequestMapping(value = "/cidades/excluir/{idIbge}", method = RequestMethod.DELETE)
    public ResponseEntity<Cidade> excluirCidade(@PathVariable("idIbge") Integer idIbge) {
        System.out.println("Fetching & Deleting User with id " + idIbge);
 
        Cidade cidadeSelecionada = cidadeRepository.findByIdIbge(idIbge).get(0);
        if (cidadeSelecionada == null) {
            System.out.println("Unable to delete. User with id " + idIbge + " not found");
            return new ResponseEntity<Cidade>(HttpStatus.NOT_FOUND);
        }

        cidadeRepository.delete(cidadeSelecionada);
        return new ResponseEntity<Cidade>(HttpStatus.NO_CONTENT);
    }
 
 	@RequestMapping(value = "/cidades/" + "{nomeCampo}" + "/" + "{valorCampo}", method = RequestMethod.GET)
	public List<Cidade> pesquisaPorNomeCampoValorCampo(@PathVariable final String nomeCampo, @PathVariable final String valorCampo) {
		return cidadeRepository.findByNomeCampo(nomeCampo, valorCampo);
	}	

 	@RequestMapping(value = "/cidades/" + "{nomeCampo}", method = RequestMethod.GET)
	public Integer pesquisaPorNomeCampo(@PathVariable final String nomeCampo) {
		Integer quantidadeRegistro = 0;
		
		if (nomeCampo.equals("idIbge")) {
		quantidadeRegistro = cidadeRepository.quantidadeRegistroIdIbge();
		}
		if (nomeCampo.equals("estado")) {
		quantidadeRegistro = cidadeRepository.quantidadeRegistroEstado();
		}
		if (nomeCampo.equals("nome")) {
		quantidadeRegistro = cidadeRepository.quantidadeRegistroNome();
		}
		if (nomeCampo.equals( "nomeSemAcento")) {
		    quantidadeRegistro = cidadeRepository.quantidadeRegistroNomeSemAcento();
		}
		if (nomeCampo.equals( "nomeAlternativo")) {
		    quantidadeRegistro = cidadeRepository.quantidadeRegistroNomeAlternativo();
		}
		if (nomeCampo.equals( "latitude")) {
		    quantidadeRegistro = cidadeRepository.quantidadeRegistroLatitude();
		}
		if (nomeCampo.equals( "capital")) {
		    quantidadeRegistro = cidadeRepository.quantidadeRegistroCapital();
		}		          
		if (nomeCampo.equals("mesoregiao")) {
			quantidadeRegistro = cidadeRepository.quantidadeRegistroMesoregiao();
		}
		if (nomeCampo.equals("microregiao")) {
			quantidadeRegistro = cidadeRepository.quantidadeRegistroMicroregiao();
		}		
		if (nomeCampo.equals( "longitude")) {
		    quantidadeRegistro = cidadeRepository.quantidadeRegistroLongitude();
		}
		
		return quantidadeRegistro;
	}	
 	
	@GetMapping("/cidades/quantidadeRegistro")
	public Long quantidadeRegistro() {
		return cidadeRepository.count();
	}
		
	@GetMapping("/cidades/maisDistantes")
	public List<Cidade> CidadesMaisDistantes() {
		DistanciaCidadeService distancia = new DistanciaCidadeService();
		return distancia.distanciaCidade(cidadeRepository.findAll());
	}
}
