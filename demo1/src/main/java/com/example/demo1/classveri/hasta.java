package com.example.demo1.classveri;

public class hasta extends insan {
    private String id;

    public hasta(String ad, String soyad, String id) throws numberexception, alphabetexception, emptyexception {
        super(ad, soyad);
        this.id = id;
        if(ad.isEmpty()||soyad.isEmpty()||id.isEmpty())
        {throw new emptyexception();}
        if(ad.matches(".*\\d+.*") || soyad.matches(".*\\d+.*"))
        {throw  new alphabetexception();}
        if (!id.matches("-?\\d+(\\.\\d+)?"))
        {throw new numberexception();}




    }

    public hasta(String ad, String soyad) {
        super(ad, soyad);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String bilgigoster() {
        return super.bilgigoster()+" Id: "+getId()+"\n\n";


    }
    public String hatagoster() {
        return super.bilgigoster()+"\n"+"icin girilen id hatalıdır";


    }
    public String kaydet(){
        return getAd()+"\n"+getSoyad()+"\n"+getId();}
}
