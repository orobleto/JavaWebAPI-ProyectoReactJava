package com.educacionit.utilidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface StringJson {

	static JsonNode getJson(String propiedad, String valor) {
		String comillas = "\"";

		String json_str = "{" + comillas + propiedad + comillas + ":" + comillas + valor + comillas + "}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(json_str);
			return node;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
