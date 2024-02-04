package com.example.demo1;

import com.example.demo1.classveri.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloApplication extends Application {
    static ArrayList<hasta> hastalist=new ArrayList<>();
    static ArrayList<hastane> hastanelist=new ArrayList<>();

    static ArrayList<doktor> doktorlist=new ArrayList<>();

    static ArrayList<randevu> randevulist=new ArrayList<>();



    @Override
    public void start(Stage window) throws IOException, numberexception, alphabetexception, emptyexception {

        loadh(hastalist);
        loadha(hastanelist);
        loadd(doktorlist);
        loadr(randevulist);




        GridPane pane =new GridPane();

            Button button1=new Button("Hasta Yonetim Sekmesi ");
        Button button2=new Button ("Hastane Yonetim Sekmesi");
        Button button3=new Button("Doktor Yönetim Sekmesi");
        Button button4=new Button("Randevu Yönetim Sekmesi");

        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(10);
        pane.setHgap(40);
        GridPane.setConstraints(button1,3,1);
        GridPane.setConstraints(button2,3,2);
        GridPane.setConstraints(button3,3,3);
        GridPane.setConstraints(button4,3,4);
        pane.getChildren().addAll(button1,button2,button3,button4);


        window.setTitle("Devlet Saglık Yönetim Sistemi");
        Scene scene = new Scene(pane, 450, 300);
        window.setScene(scene);



//hasta sekmesi



        VBox content = new VBox();
        for (hasta hasta : hastalist) {
            HBox hboxh = new HBox(10);
            Label hastaBilgiLabel = new Label(hasta.bilgigoster());
            Button silButton = new Button("Sil");
            silButton.setOnAction(e -> {
                content.getChildren().remove(hboxh);
                hastalist.remove(hasta);
                try {
                    saveh(hastalist);
                } catch (FileNotFoundException ex) {

                }
            });

            hboxh.getChildren().addAll(hastaBilgiLabel, silButton);
            content.getChildren().addAll(hboxh);
        }


        ScrollPane scrollPaneh = new ScrollPane();
        scrollPaneh.setContent(content);
        Button hekle=new Button("Hasta Ekle");
        Button hgeri=new Button("Geri Dön");

        BorderPane rooth=new BorderPane();
        rooth.setTop(new Label("                   Kayıtlı Hastalar"));
        rooth.setCenter(scrollPaneh);
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(hekle, hgeri);
        rooth.setBottom(hbox);
        Scene scenehmain=new Scene(rooth,450, 300);
        hgeri.setOnAction(e ->window.setScene(scene));


// hasta ekle

        GridPane paneh=new GridPane();
        paneh.setPadding(new Insets(10,10,10,10));
        paneh.setVgap(10);
        paneh.setHgap(10);



        TextField adh=new TextField(),soyadh=new TextField(),idh=new TextField();
        adh.setPromptText("Ad");
        soyadh.setPromptText("Soyad");
        idh.setPromptText("Id");


        Button butonh=new Button("Kaydet");
        Button butonhg=new Button("Geri dön");

        Label labelhuyari=new Label("");
        GridPane.setConstraints(labelhuyari,1,4);

        butonh.setOnAction(e -> {
            HBox hboxhb = new HBox(10);

            try {
                hasta yeniHasta = new hasta(adh.getText(), soyadh.getText(), idh.getText());
                hastalist.add(yeniHasta);

                Label hastaBilgiLabel = new Label(yeniHasta.bilgigoster());
                Button silButton = new Button("Sil");
                silButton.setOnAction(event -> {
                    hastalist.remove(yeniHasta);
                    content.getChildren().remove(hboxhb);
                    try {
                        saveh(hastalist);
                    } catch (FileNotFoundException ignored) {

                    }
                });

                hboxhb.getChildren().addAll(hastaBilgiLabel, silButton);
                content.getChildren().add(hboxhb);
                labelhuyari.setText("Kaydedildi");
                saveh(hastalist);
            }
            catch (alphabetexception ignored)
            {labelhuyari.setText("Ad ve soyad sadece harflerden olusmalıdır");}
            catch (numberexception ex) {
                kontrol kontrol1;
                kontrol1 = new hasta(adh.getText(), soyadh.getText());
                labelhuyari.setText(kontrol1.hatagoster());
            }
            catch (emptyexception ex)
            {labelhuyari.setText("Girilen bölgeler bos olmamalı");}
            catch (FileNotFoundException ex)
            {}

        });


        GridPane.setConstraints(adh,1,0);
        GridPane.setConstraints(soyadh,1,1);
        GridPane.setConstraints(idh,1,2);
        GridPane.setConstraints(butonh,1,3);
        GridPane.setConstraints(butonhg,0,3);
        paneh.getChildren().addAll(butonh,butonhg,adh,soyadh,idh,labelhuyari);


        Scene sceneh=new Scene(paneh,450, 300);
        button1.setOnAction(e-> window.setScene(scenehmain));
        butonhg.setOnAction(e->window.setScene(scenehmain));
        hekle.setOnAction(e -> {
            window.setScene(sceneh);
            adh.clear();
            soyadh.clear();
            idh.clear();
        });





//hastane sekmesi
        VBox contenth = new VBox();

        for (hastane a : hastanelist) {
            HBox hboxha = new HBox(10);
            Label hastaneBilgiLabel = new Label(a.bilgigoster());
            Button silButton = new Button("Sil");
            silButton.setOnAction(e -> {
                contenth.getChildren().remove(hboxha);
                hastanelist.remove(a);
                try {
                    saveha(hastanelist);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            hboxha.getChildren().addAll(hastaneBilgiLabel, silButton);
            contenth.getChildren().addAll(hboxha);
        }

        ScrollPane scrollPaneha = new ScrollPane();
        scrollPaneha.setContent(contenth);
        Button haekle=new Button("Hastane Ekle");
        Button hageri=new Button("Geri Dön");

        BorderPane rootha=new BorderPane();
        rootha.setTop(new Label("                   Kayıtlı Hastaneler"));
        rootha.setCenter(scrollPaneha);

        HBox habox = new HBox(10);
        habox.setAlignment(Pos.CENTER);
        habox.getChildren().addAll(haekle, hageri);

        rootha.setBottom(habox);
        Scene scenehamain=new Scene(rootha,450, 300);
        hageri.setOnAction(e ->window.setScene(scene));


        // hastane ekleme

        GridPane paneha=new GridPane();
        paneha.setPadding(new Insets(10,10,10,10));
        paneha.setVgap(10);
        paneha.setHgap(10);



        TextField adha=new TextField(),adres=new TextField(),hizmet=new TextField();
        adha.setPromptText("Hastane Adı");
        adres.setPromptText("Adres");
        hizmet.setPromptText("Hizmet");


        Button butonha=new Button("Kaydet");
        Button butonhag=new Button("Geri dön");

        Label labelhauyari=new Label("");
        GridPane.setConstraints(labelhauyari,1,4);

        butonha.setOnAction(e -> {
           try {
                HBox hboxhab = new HBox(10);
                hastane yeniHastane = new hastane(adha.getText(), adres.getText(), hizmet.getText());
                hastanelist.add(yeniHastane);

                Label hastaneBilgiLabel = new Label(yeniHastane.bilgigoster());
                Button silButton = new Button("Sil");
                silButton.setOnAction(event -> {
                    contenth.getChildren().remove(hboxhab);
                    hastanelist.remove(yeniHastane);
                    try {
                        saveha(hastanelist);
                    } catch (FileNotFoundException ex) {

                    }
                });

                hboxhab.getChildren().addAll(hastaneBilgiLabel, silButton);
                contenth.getChildren().add(hboxhab);
                labelhauyari.setText("kaydedildi");
                saveha(hastanelist);
            }
           catch (alphabetexception ex)
           {labelhauyari.setText("Ad sadece harflerden olusmalıdır");

           }
           catch (emptyexception ex)
           {labelhauyari.setText("Girilen bölge bos olmamalıdır");}

            catch (FileNotFoundException ex) {

           }
        });

        GridPane.setConstraints(adha,1,0);
        GridPane.setConstraints(adres,1,1);
        GridPane.setConstraints(hizmet,1,2);
        GridPane.setConstraints(butonha,1,3);
        GridPane.setConstraints(butonhag,0,3);
        paneha.getChildren().addAll(butonha,butonhag,adha,adres,hizmet,labelhauyari);

        Scene sceneha=new Scene(paneha,450, 300);
        button2.setOnAction(e-> window.setScene(scenehamain));
        butonhag.setOnAction(e->window.setScene(scenehamain));
        haekle.setOnAction(e ->{window.setScene(sceneha);
            adha.clear();
            adres.clear();
            hizmet.clear();
            });


//
        VBox contentd = new VBox();

        for (doktor a : doktorlist) {
            HBox hboxd = new HBox(10);
            Label doktorBilgiLabel = new Label(a.bilgigoster());
            Button silButton = new Button("Sil");
            silButton.setOnAction(e -> {
                contentd.getChildren().remove(hboxd);
                doktorlist.remove(a);
                try {
                    saved(doktorlist);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            hboxd.getChildren().addAll(doktorBilgiLabel, silButton);
            contentd.getChildren().addAll(hboxd);
        }

        ScrollPane scrollPaned = new ScrollPane();
        scrollPaned.setContent(contentd);
        Button dekle=new Button("Doktor Ekle");
        Button dgeri=new Button("Geri Dön");
        BorderPane rootd=new BorderPane();
        rootd.setCenter(scrollPaned);
        rootd.setTop(new Label("                   Kayıtlı Doktorlar"));

        HBox dbox = new HBox(10);
        dbox.setAlignment(Pos.CENTER);
        dbox.getChildren().addAll(dekle, dgeri);


        rootd.setBottom(dbox);
        Scene scenedmain= new Scene(rootd,450, 300);


        button3.setOnAction(e->window.setScene(scenedmain));

// doktor ekle


        GridPane paned=new GridPane();
        paned.setPadding(new Insets(10,10,10,10));
        paned.setVgap(10);
        paned.setHgap(10);
        TextField add=new TextField(),soyadd=new TextField(),uzmh=new TextField();
        add.setPromptText("Ad");
        soyadd.setPromptText("Soyad");
        uzmh.setPromptText("Uzmanlık Alanı");
        Button butond=new Button("Kaydet");
        Button butondg=new Button("Geri Dön");
        ComboBox<String> hastanebox=new ComboBox<>();

        Label labelduyari=new Label("");
        GridPane.setConstraints(labelduyari,1,5);

        for(hastane a:hastanelist) hastanebox.getItems().add(a.getAd());
        hastanebox.setVisible(true);
        hastanebox.setPromptText("Hastane");

        GridPane.setConstraints(add,1,0);
        GridPane.setConstraints(soyadd,1,1);
        GridPane.setConstraints(uzmh,1,2);
        GridPane.setConstraints(hastanebox,1,3);
        GridPane.setConstraints(butond,1,4);
        GridPane.setConstraints(butondg,0,4);
        paned.getChildren().addAll(butond,butondg,add,soyadd,uzmh,hastanebox,labelduyari);

        Scene scened=new Scene(paned,450, 300);

        dgeri.setOnAction(e-> {
            window.setScene(scene);

        });

        dekle.setOnAction(e-> {window.setScene(scened);
            hastanebox.getItems().clear();
            for(hastane a:hastanelist) hastanebox.getItems().add(a.getAd());
            add.clear();
            soyadd.clear();
            uzmh.clear();});


        butondg.setOnAction(e-> window.setScene(scenedmain));


        butond.setOnAction(e-> {


           try {
                HBox hboxdb = new HBox(10);
                doktor yenidoktor = new doktor(add.getText(), soyadd.getText(), getchoiceh(hastanebox, hastanelist), uzmh.getText());
                 doktorlist.add(yenidoktor);
                Label doktorBilgiLabel = new Label(yenidoktor.bilgigoster());
                Button silButton = new Button("Sil");
                silButton.setOnAction(event -> {
                    doktorlist.remove(yenidoktor);
                    contentd.getChildren().remove(hboxdb);
                    try {
                        saved(doktorlist);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                hboxdb.getChildren().addAll(doktorBilgiLabel, silButton);
                contentd.getChildren().add(hboxdb);
                labelduyari.setText("kaydedildi");
                saved(doktorlist);
            }
           catch(alphabetexception ex)
           {labelduyari.setText("Ad ve soyad sadece harflerden olusmalıdır");}
           catch (emptyexception ex)
           {labelduyari.setText("Girilen bölge bos olmamalı");}
           catch (Exception ex)
           {labelduyari.setText("Hastane bölgesi bos olmamalı");}
        });


//

        VBox contentr=new VBox();

        for (randevu a : randevulist) {
            HBox hboxr = new HBox(10);
            Label randevuBilgiLabel = new Label(a.bilgigoster());
            Button silButton = new Button("Sil");
            silButton.setOnAction(e -> {
                contentr.getChildren().remove(hboxr);
                randevulist.remove(a);
                try {
                    saver(randevulist);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            hboxr.getChildren().addAll(randevuBilgiLabel, silButton);
            contentr.getChildren().addAll(hboxr);
        }
        ScrollPane scrollPaner=new ScrollPane();
        scrollPaner.setContent(contentr);
        Button rekle=new Button("Randevu Ekle");
        Button rgeri=new Button("Geri Dön");





        BorderPane rootr=new BorderPane();
        rootr.setTop(new Label("                   Randevular"));
        rootr.setCenter(scrollPaner);

        HBox rbox = new HBox(10);
        rbox.setAlignment(Pos.CENTER);
       rbox.getChildren().addAll(rekle, rgeri);

        rootr.setBottom(rbox);
        Scene scenermain=new Scene(rootr,450, 300);
        rgeri.setOnAction(e ->window.setScene(scene));
        button4.setOnAction(e->window.setScene(scenermain));

        // randevu ekleme
        GridPane paner=new GridPane();
        paner.setPadding(new Insets(10,10,10,10));
        paner.setVgap(10);
        paner.setHgap(10);
        TextField tarih=new TextField();
        tarih.setPromptText("Tarih");
        Button butonr=new Button("Kaydet");
        Button butonrg=new Button("Geri Dön");
        ComboBox<String> doktorbox=new ComboBox<>();

        Label labelruyari=new Label("");
        GridPane.setConstraints(labelruyari,1,5);

        for(doktor a:doktorlist) doktorbox.getItems().add(a.getAd());
        doktorbox.setVisible(true);
        doktorbox.setPromptText("Doktor");

        ComboBox<String> hastabox=new ComboBox<>();

        for(hasta a:hastalist) doktorbox.getItems().add(a.getId());
        hastabox.setVisible(true);
        hastabox.setPromptText("Hasta");

        GridPane.setConstraints(hastabox,1,0);
        GridPane.setConstraints(doktorbox,1,1);
        GridPane.setConstraints(tarih,1,3);
        GridPane.setConstraints(butonr,1,4);
        GridPane.setConstraints(butonrg,0,4);
        paner.getChildren().addAll(butonr,butonrg,hastabox,doktorbox,tarih,labelruyari);

        Scene scener=new Scene(paner,450, 300);

        butonrg.setOnAction(e->window.setScene(scenermain));

        rekle.setOnAction(e-> {window.setScene(scener);
            doktorbox.getItems().clear();
            for(doktor a:doktorlist) doktorbox.getItems().add(a.getAd());

            hastabox.getItems().clear();
            for(hasta a:hastalist) hastabox.getItems().add(a.getAd());

            tarih.clear();
            });

        butonr.setOnAction(e-> {

            try{
                HBox hboxrb = new HBox(10);
                randevu yenirandevu = new randevu(getchoiced(doktorbox, doktorlist), getchoicehasta(hastabox, hastalist), tarih.getText());
                randevulist.add(yenirandevu);
                Label randevuBilgiLabel = new Label(yenirandevu.bilgigoster());
                Button silButton = new Button("Sil");
                silButton.setOnAction(event -> {
                    randevulist.remove(yenirandevu);
                    contentr.getChildren().remove(hboxrb);
                    try {
                        saver(randevulist);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                hboxrb.getChildren().addAll(randevuBilgiLabel, silButton);
                contentr.getChildren().add(hboxrb);
                labelruyari.setText("Kaydedildi");
                saver(randevulist);
            }
            catch (NullPointerException ex)
            {labelruyari.setText("Hasta ve Doktorun bölgeleri bos bırakılmamalı");}
            catch(emptyexception ex)
            {labelruyari.setText("Bölgeler bos bırakılmamalı");} catch (FileNotFoundException ex) {

            }

        });














        window.show();
    }
    public hastane getchoiceh(ComboBox<String> combobox, ArrayList<hastane> list)
    {
      String  name=combobox.getValue();
      hastane hastane = null;
      for(hastane a:list)
          if(a.getAd().equals(name))
              hastane=a;
        return hastane;
    }

    public doktor getchoiced(ComboBox<String> combobox, ArrayList<doktor> list)
    {
        String  name=combobox.getValue();
        doktor doktor = null;
        for(doktor a:list)
            if(a.getAd().equals(name))
                doktor=a;
        return doktor;
    }

    public hasta getchoicehasta(ComboBox<String> combobox, ArrayList<hasta> list)
    {
        String  name=combobox.getValue();
        hasta hasta = null;
        for(hasta a:list)
            if(a.getAd().equals(name))
                hasta=a;
        return hasta;
    }

    public void saveh(ArrayList<hasta> hastalist) throws FileNotFoundException
            
    { PrintWriter pw = new PrintWriter(new FileOutputStream("hastalar.txt"));
        String hastatxt = "";
        for(hasta a:hastalist)
        { hastatxt=hastatxt +a.kaydet()+"\n";
                }
        hastatxt = hastatxt.substring(0, hastatxt.length() - 1);
        pw.write(hastatxt);
        pw.close();}

    public void loadh(ArrayList<hasta> hastalist) throws FileNotFoundException, numberexception, alphabetexception, emptyexception {
        FileInputStream fileIn = new FileInputStream("hastalar.txt");
        Scanner scan = new Scanner(fileIn);
        while (scan.hasNextLine()) {
            String ad = scan.nextLine();
            String soyad = scan.nextLine();
            String id = scan.nextLine();

            hastalist.add(new hasta(ad, soyad, id));
        }

        scan.close();
    }
    public void saveha(ArrayList<hastane> hastanelist) throws FileNotFoundException

    { PrintWriter pw = new PrintWriter(new FileOutputStream("hastaneler.txt"));
        String hastanetxt = "";
        for(hastane a:hastanelist)
        { hastanetxt=hastanetxt +a.kaydet() +"\n";
        }
        hastanetxt = hastanetxt.substring(0, hastanetxt.length() - 1);
        pw.write(hastanetxt);
        pw.close();}

    public void loadha(ArrayList<hastane> hastanelist) throws FileNotFoundException, numberexception, alphabetexception, emptyexception {
        FileInputStream fileIn = new FileInputStream("hastaneler.txt");
        Scanner scan = new Scanner(fileIn);
        while (scan.hasNextLine()) {
            String ad = scan.nextLine();
            String adres = scan.nextLine();
            String hizmet = scan.nextLine();

            hastanelist.add(new hastane(ad, adres, hizmet));
        }

        scan.close();
    }

    public void saved(ArrayList<doktor> doktorlist) throws FileNotFoundException

    { PrintWriter pw = new PrintWriter(new FileOutputStream("doktorlar.txt"));
        String doktortxt = "";
        for(doktor a:doktorlist)
        { doktortxt=doktortxt +a.kaydet()+"\n" ;
        }
        doktortxt = doktortxt.substring(0, doktortxt.length() - 1);
        pw.write(doktortxt);
        pw.close();}

    public void loadd(ArrayList<doktor> doktorlist) throws FileNotFoundException, alphabetexception, emptyexception, numberexception {
        FileInputStream fileIn = new FileInputStream("doktorlar.txt");
        Scanner scan = new Scanner(fileIn);
        while (scan.hasNextLine()) {
            String ad = scan.nextLine();
            String soyad = scan.nextLine();
            String hastanead = scan.nextLine();
            String hastaneadres = scan.nextLine();
            String hastanehizmet =scan.nextLine();
            String uzmanlikalani=scan.nextLine();




            doktorlist.add(new doktor(ad, soyad, new hastane(hastanead,hastaneadres,hastanehizmet),uzmanlikalani));
        }

        scan.close();
        scan.close();
    }

    public void saver(ArrayList<randevu> randevulist) throws FileNotFoundException

    { PrintWriter pw = new PrintWriter(new FileOutputStream("randevular.txt"));
        String randevutxt = "";
        for(randevu a:randevulist)
        { randevutxt=randevutxt  +a.kaydet()+"\n";
        }
        randevutxt = randevutxt.substring(0, randevutxt.length() - 1);
        pw.write(randevutxt);
        pw.close();}

    public void loadr(ArrayList<randevu> randevulist) throws FileNotFoundException, alphabetexception, emptyexception, numberexception {
        FileInputStream fileIn = new FileInputStream("randevular.txt");
        Scanner scan = new Scanner(fileIn);
        while (scan.hasNextLine()) {
            String doktorad = scan.nextLine();
            String doktorsoyad = scan.nextLine();
            String hastanead = scan.nextLine();
            String hastaneadres = scan.nextLine();
            String hastanehizmet =scan.nextLine();
            String doktoruzmanlikalani=scan.nextLine();

            String hastaad = scan.nextLine();
            String hastasoyad = scan.nextLine();
            String hastaid = scan.nextLine();

            String tarih=scan.nextLine();


            randevulist.add(new randevu(new doktor(doktorad,doktorsoyad,new hastane(hastanead,hastaneadres,hastanehizmet),doktoruzmanlikalani),new hasta(hastaad,hastasoyad,hastaid),tarih));
        }


        scan.close();
    }

    public static void main(String[] args) {
        launch();
    }
}