package br.pucrs.bruno.laitano.springboot;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Acervo {
    private List<Livro> livros;
    
    public Acervo(){
        livros = new LinkedList<>();
        livros.add(new Livro(110, "Aprendendo Java", "Maria da Silva", 2015));
        livros.add(new Livro(120, "Spring-Boot", "Jose de Souza", 2020));
        livros.add(new Livro(130, "Principios SOLID", "Pedro da Silva", 2023));
        livros.add(new Livro(140, "Padroes de Projeto", "Joana Moura", 2023));
        livros.add(new Livro(150, "Teste Unitario", "Pedro da Silva", 2024));
    }

    public List<Livro> getAll() {
        return livros;
    }
    
    public List<String> getTitulos() {
        return livros.stream()
               .map(livro->livro.getTitulo())
               .toList();
    }

    public boolean addLivro(Livro l){
        livros.add(l);
        return true;
    }
}