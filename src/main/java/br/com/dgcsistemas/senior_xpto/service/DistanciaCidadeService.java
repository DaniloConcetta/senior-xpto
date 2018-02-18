package br.com.dgcsistemas.senior_xpto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dgcsistemas.senior_xpto.model.Cidade;

@Service
public class DistanciaCidadeService {

	public DistanciaCidadeService() {
	}

	public List<Cidade> distanciaCidade(List<Cidade> listaCidade) {
		List<Cidade> listaCidadeRetorno;
		listaCidadeRetorno  = new ArrayList<>();
		//List<Cidade> listaCidadeInicial;
		//listaCidadeInicial = listaCidade;
		//List<Cidade> listaCidadeFinal;
		//listaCidadeFinal = listaCidade;

		double distancia = 0;
		double distanciaAnterior = 0;
		
		for (Cidade cidadeFinal : listaCidade) {
		  
	      for (Cidade cidadeInicial : listaCidade) {
	    	distancia = distance(cidadeInicial.getLatitude().doubleValue(), cidadeFinal.getLatitude().doubleValue(), cidadeInicial.getLongitude().doubleValue(), cidadeFinal.getLongitude().doubleValue()   );
	    	if (distancia >= distanciaAnterior) {
	    		distanciaAnterior = distancia;
	    		listaCidadeRetorno.clear();
	    		listaCidadeRetorno.add(cidadeInicial);
	    		listaCidadeRetorno.add(cidadeFinal);
	    	}
	      }
		}  
		return listaCidadeRetorno;
	}

	private double distance(double lat1, double lat2, double lon1, double lon2) {
	    final int R = 6371; // Radius of the earth

	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters
	    
	    distance = Math.pow(distance, 2) ;
	    return Math.sqrt(distance);
	}

	private double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}
}
