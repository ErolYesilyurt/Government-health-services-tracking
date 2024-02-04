package com.example.demo1.classveri;

import com.example.demo1.classveri.bilgiler;

public abstract class insan extends kontrol implements bilgiler {
    private String ad;
    private String soyad;
    public insan(String ad,String soyad)
    {this.ad=ad;
     this.soyad=soyad;}

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    public String bilgigoster()
    {
        return "Ad: "+getAd()+" Soyad: "+getSoyad() ;

    }
}
