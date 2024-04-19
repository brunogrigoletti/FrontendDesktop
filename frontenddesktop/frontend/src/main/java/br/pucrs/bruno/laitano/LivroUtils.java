package br.pucrs.bruno.laitano;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LivroUtils {

        public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();  
    
        private LivroUtils() {}  

        // Transforma JSON em um objeto
        public static Livro toObject(InputStream inputStream) {  
            try {  
                return OBJECT_MAPPER.readValue(inputStream, Livro.class);  
            }  
            catch (IOException exc) {  
                throw new UncheckedIOException(exc);  
            }  
        }  

        // Transforma JSON em uma lista de objetos
        public static List<Livro> toList(InputStream inputStream) {  
            try {  
                return OBJECT_MAPPER.readValue(inputStream, new  TypeReference<>() {});  
            }  
            catch (IOException exc) {  
                throw new UncheckedIOException(exc);  
            }  
        }  

        // Transforma um objeto em JSON
        public static String toJson(Livro livro) {  
            try {  
                return OBJECT_MAPPER.writeValueAsString(livro);  
            }  
            catch (JsonProcessingException exc) {  
                throw new UncheckedIOException(exc);  
            }
        }

        // Troca espaco da String por %20
        public static String ajustaString(String entrada) {
            String saida = "";
            saida = entrada.replaceAll(" ", "%20");
            return saida;
        }

    }