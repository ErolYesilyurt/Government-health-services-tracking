package com.example.demo1.classveri;

import com.example.demo1.classveri.bilgiler;
import com.example.demo1.classveri.doktor;
import com.example.demo1.classveri.hasta;

public class randevu implements bilgiler {
    private com.example.demo1.classveri.doktor doktor;
    private com.example.demo1.classveri.hasta hasta ;

    private String tarih;

    public randevu(doktor doktor, hasta hasta, String tarih) throws emptyexception {
        this.doktor = doktor;
        this.hasta = hasta;
        this.tarih = tarih;
        if(tarih.isEmpty())
        throw new emptyexception();
    }


    @Override
    public String bilgigoster() {
        return"Hasta bilgileri:"+"\n"+hasta.bilgigoster()+"Doktor ve hastane bilgileri"+"\n"+ doktor.bilgigoster()+"Tarih: "+tarih+"\n \n \n";


    }
    public String kaydet()
    {return doktor.kaydet()+"\n"+hasta.kaydet()+"\n"+tarih;}
}
