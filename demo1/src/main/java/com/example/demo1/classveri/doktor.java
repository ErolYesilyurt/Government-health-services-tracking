package com.example.demo1.classveri;

public class doktor extends insan implements bilgiler {
    com.example.demo1.classveri.hastane hastane;
    String uzmanlik_alani;
    public doktor(String ad, String soyad, hastane hastane, String uzmanlik_alani) throws emptyexception, alphabetexception {
        super(ad, soyad);
        this.hastane=hastane;
        this.uzmanlik_alani=uzmanlik_alani;
        if(ad.isEmpty()||soyad.isEmpty()||uzmanlik_alani.isEmpty())
        {throw new emptyexception();}
        if(ad.matches(".*\\d+.*") || soyad.matches(".*\\d+.*"))
        {throw  new alphabetexception();}
    }

    public com.example.demo1.classveri.hastane getHastane() {
        return hastane;
    }

    public void setHastane(com.example.demo1.classveri.hastane hastane) {
        this.hastane = hastane;
    }

    public String getUzmanlik_alani() {
        return uzmanlik_alani;
    }

    public void setUzmanlik_alani(String uzmanlik_alani) {
        this.uzmanlik_alani = uzmanlik_alani;
    }

    @Override
    public String bilgigoster() {
        return super.bilgigoster()+" Uzmanlik: "+getUzmanlik_alani()+"\n"+"Doktorun calıstıgı hastane bilgileri: \n "+ hastane.bilgigoster();


    }
    public String kaydet()
    {return getAd()+"\n"+getSoyad()+"\n"+getHastane().kaydet()+"\n"+getUzmanlik_alani();}
}
