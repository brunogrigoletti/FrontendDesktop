package br.pucrs.bruno.laitano.springboot;

import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/biblioteca")
public class ExemploController {

    private Acervo acervo;

    @Autowired
    public ExemploController(Acervo acervo) {
        this.acervo = acervo;
    }

    @GetMapping("/")
    public String getMensagemInicial() {
        return "Funcionando!";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return acervo.getAll();
    }
    
    @GetMapping("/titulos")
    public List<String> getTitulos() {
        return acervo.getAll().stream()
               .map(livro->livro.getTitulo())
               .toList();
    }

    @GetMapping("/autores")
    public List<String> getListaAutores() {
        return acervo.getAll().stream()
                .map(l -> l.getAutor())
                .distinct()
                .toList();
    }

    @GetMapping("/livrosautor")
    public List<Livro> getLivrosDoAutor(@RequestParam(value = "autor") String autor) {
        return acervo.getAll().stream()
                .filter(livro -> livro.getAutor().equals(autor))
                .toList();
    }

    @GetMapping("/livrosautorano/{autor}/ano/{ano}")
    public List<Livro> getLivrosDoAutor(@PathVariable(value = "autor") String autor, @PathVariable(value = "ano") int ano) {
        return acervo.getAll().stream()
                .filter(livro -> livro.getAutor().equals(autor))
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    @PostMapping("/novolivro")
    public boolean cadastraLivroNovo(@RequestBody final Livro livro) {
        acervo.addLivro(livro);
        return true;
    }

    @GetMapping("/livrotitulo/{titulo}")
    public ResponseEntity<Livro> getLivroTitulo(@PathVariable("titulo") String titulo) {
        Livro resp = acervo.getAll().stream()
                .filter(livro -> livro.getTitulo().equals(titulo))
                .findFirst()
                .orElse(null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resp);
    }
}