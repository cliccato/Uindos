package app.indovina_immagini.source;
public class Data{

    private int g;		//giorno
    private int m;		//mese
    private int a;		//anno

    //metodo costruttore che costruisce la data ricevendo come parametri giorno, mese e anno
    public Data(int g, int m, int a){
        this.g = g;
        this.m = m;
        this.a = a;
    }

    public String toString(){
        return + g + "/" + m + "/" + a;
    }

    public void setGiorno(int g){
        this.g = g;
    }

    public int getGiorno(){
        return g;
    }

    public void setMese(int m){
        this.m = m;
    }

    public int getMese(){
        return m;
    }

    public void setAnno(int a){
        this.a = a;
    }

    public int getAnno(){
        return a;
    }
}