package br.com.dgcsistemas.senior_xpto.service;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import br.com.dgcsistemas.senior_xpto.model.Cidade;
import br.com.dgcsistemas.senior_xpto.repository.CidadeRepository;

@Service
public class CsvReaderService {
	
	public void reader(CidadeRepository cidadeRepository) {
    	System.out.println("read()1");
    	Reader csvFileReader = null;
    	String nomeArquivoCsv = "Cidades.csv";
    	Cidade cidade;
    	
        try {
        	csvFileReader = new FileReader(getClass().getClassLoader().getResource(nomeArquivoCsv).getFile() );
        	Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvFileReader);
    
            for (CSVRecord record : records) {
                cidade = new Cidade();
                cidade.setIdIbge(new Integer(record.get("ibge_id")));
                cidade.setEstado(record.get("uf"));
                cidade.setNome(record.get("name"));
                cidade.setCapital(record.get("capital"));
                cidade.setLatitude(Float.parseFloat(record.get("lat")));
                cidade.setLongitude(Float.parseFloat(record.get("lon")));
                cidade.setNomeSemAcento(record.get("no_accents"));
                cidade.setNomeAlternativo(record.get("alternative_names"));
                cidade.setMicroregiao(record.get("microregion"));
                cidade.setMesoregiao(record.get("mesoregion"));
                cidade = cidadeRepository.save(cidade);
            }

            System.out.println("read()2");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvFileReader != null) {
                try {
                	csvFileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}