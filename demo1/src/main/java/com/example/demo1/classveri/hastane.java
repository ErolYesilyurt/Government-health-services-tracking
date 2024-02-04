package com.example.demo1.classveri;

import java.util.ArrayList;
public class hastane extends kontrol{
    private String ad;
    private String adres;
    private String hizmetler;

    public hastane(String ad, String adres, String hizmet) throws alphabetexception, emptyexception {
        this.ad = ad;
        this.adres = adres;
        this.hizmetler =  hizmet;
        if(ad.matches(".*\\d+.*") )
        {throw  new alphabetexception();}
        if(adres.isEmpty()|| hizmet.isEmpty() || ad.isEmpty())
        {throw new emptyexception();}
    }


    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getHizmetler() {
        return hizmetler;
    }

    public void setHizmetler(String hizmetler) {
        this.hizmetler = hizmetler;
    }


    public String  bilgigoster(){
        return "Hastaneni adı: "+getAd()+"\n"+"Hastanenin adresi: "+getAdres()+"\n"+"Hastanenin hizmetleri: "+getHizmetler()+"\n\n";

    }
    public String kaydet(){
        return getAd()+"\n"+getAdres()+"\n"+ getHizmetler();}
}

