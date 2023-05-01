package app.indovina_immagini.src;
public class Persona{

    protected String nome;          // nome
    protected Data dataNascita;     // data nascita
    protected String sesso;         // sesso

    // costruttore
    public Persona(String nome, Data dataNascita, String sesso){
        this.nome = nome;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
    }
}