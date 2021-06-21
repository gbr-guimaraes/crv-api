package com.gbr.crvapi.services.Implementations;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.gbr.crvapi.model.FipeApiModel;
import com.gbr.crvapi.services.FipeApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FipeApiImpl implements FipeApi{

	public String apiMarca(String marca) {
		RestTemplate template = new RestTemplate();
		String json = template.getForObject("https://parallelum.com.br/fipe/api/v1/carros/marcas", String.class);

		Gson gson = new Gson();
		Type fipeLista = new TypeToken<ArrayList<FipeApiModel>>(){}.getType();
		ArrayList<FipeApiModel> listaMarca = gson.fromJson(json, fipeLista);

		for(FipeApiModel item : listaMarca)
		{
			if(item.getNome().equals(marca))
			return item.getCodigo();
		}
		return null;
	}
		
	public String apiModelo(String marca, String modelo) {
		RestTemplate template = new RestTemplate();
		String json = template.getForObject("https://parallelum.com.br/fipe/api/v1/carros/marcas/{marca}/modelos", String.class, marca);
		json = json.replace("{\"modelos\":", "");
		json = json.split("}],")[0] + "}]";

		Gson gson = new Gson();
		Type fipeLista = new TypeToken<ArrayList<FipeApiModel>>(){}.getType();
		ArrayList<FipeApiModel> listaModelo = gson.fromJson(json, fipeLista);

		for(FipeApiModel item : listaModelo)
		{
			if(item.getNome().equals(modelo))
			return item.getCodigo();
		}
		return null;
	}

	public String apiAno(String marca, String modelo, String ano) {
		RestTemplate template = new RestTemplate();
		String json = template.getForObject("https://parallelum.com.br/fipe/api/v1/carros/marcas/{marca}/modelos/{modelo}/anos", String.class, marca, modelo);

		Gson gson = new Gson();
		Type fipeLista = new TypeToken<ArrayList<FipeApiModel>>(){}.getType();
		ArrayList<FipeApiModel> listaAno = gson.fromJson(json, fipeLista);

		for(FipeApiModel item : listaAno)
		{
			if(item.getNome().equals(ano))
			return item.getCodigo();
		}
		return null;
	
}

	public String apiValor(String marca, String modelo, String ano) {
		marca = this.apiMarca(marca);

		if(marca != null) modelo = this.apiModelo(marca, modelo);
		else return null;

		if(modelo != null) ano = this.apiAno(marca, modelo, ano);
		else return null;

		if(ano == null) return null;

		RestTemplate template = new RestTemplate();
		String json = template.getForObject("https://parallelum.com.br/fipe/api/v1/carros/marcas/{marca}/modelos/{modelo}/anos/{ano}", String.class, marca, modelo, ano);

		Gson gson = new Gson();
		FipeApiModel fipeApiModel = gson.fromJson(json, FipeApiModel.class);

		return fipeApiModel.getValor();

	}

}



