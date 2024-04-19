package br.pucrs.bruno.laitano;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;

    public Livro() {        
    }

    public Livro(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }


    @Override
    public String toString() {
        return "Livro [ id=" + id + "\n titulo=" + titulo + "\n autor=" + autor + "\n ano=" + ano + "]";
    }
}
