package com.alura.literatura.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {
    public static String obtenerTraduccion(String texto){
        //definir modelo de la APi utilizaremos
        String modelo = "gemini-2.0-flash-lite";
        // definimos el prompt
        String prompt = "resume el siguiente texto como maximo en 1000 caracteres :"+ texto;
        //configuramos el cliente de la API
        Client cliente = new Client.Builder().apiKey("AIzaSyD3JmFL7r5oJ9pUnd6JD3beoDeEq7pdrLE").build();
        //Realizamos la solicitud de traduccion con el modelo y prompt definidos
        try {
            //null , parametro para configuraciones adicionales
            GenerateContentResponse respuesta = cliente.models.generateContent(modelo,prompt,null);
            if (!respuesta.text().isEmpty()){
                return respuesta.text();
            }
        }catch(Exception e){
            System.out.println("Error al llamar a la API de Gemini para traducir texto : "+ e.getMessage());
        }finally{
            return texto;
        }
    }
}
