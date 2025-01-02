package com.example.Sklep_z_ksiazkami;

import com.example.Sklep_z_ksiazkami.Kontroler.Kontroler;
import com.example.Sklep_z_ksiazkami.Model.Klient;
import com.example.Sklep_z_ksiazkami.Model.KlientDto;
import com.example.Sklep_z_ksiazkami.Model.Rodzaj;
import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Repozytorium.Repozytorium;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;

import java.util.ArrayList;
import java.util.List;


//@Route("dodaj")
@SpringComponent
@UIScope
public class EdytorWidok extends VerticalLayout implements KeyNotifier {
    public interface ChangeHandler {
        void onChange();
    }

    private final Repozytorium repozytorium;
    //@Valid
    private KlientDto klient;

    Logger logger = LoggerFactory.getLogger(EdytorWidok.class);

    TextField nazwa = new TextField("Imie i nazwisko");
    TextField nip = new TextField("NIP");

    ComboBox<String> plec = new ComboBox<>("Płeć");
    DatePicker dataUrodzenia = new DatePicker("Data Urodzenia");
    ComboBox<Rodzaj> comboBox = new ComboBox<>("Rodzaj Klienta");
    ComboBox<Status> status = new ComboBox<>("Status Klienta");
    TextField numerRachunku = new TextField("Numer Rachunku");
    TextField nazwaKonta = new TextField("Nazwa Konta");
    H2 naglowek = new H2();
    Div naglowekDiv = new Div(naglowek);

    Button zapisz = new Button("zapisz", VaadinIcon.CHECK.create());
    Button anuluj = new Button("anuluj",VaadinIcon.CLOSE.create());

    Button usun = new Button("usun", VaadinIcon.TRASH.create());

    HorizontalLayout akcje = new HorizontalLayout(zapisz,usun, anuluj);


    BeanValidationBinder<KlientDto> binder = new BeanValidationBinder<>(KlientDto.class);

    //BeanValidationBinder<Klient> sprawdz = new BeanValidationBinder<>(Klient.class);

    private ChangeHandler changeHandler;
    @Autowired
    public EdytorWidok(Repozytorium repozytorium,SmartValidator validator) {
        this.repozytorium = repozytorium;
        akcje.addClassName("akcje");
        //this.validator = validator;

        add(    naglowekDiv,
                nazwa,
                nip,
                plec,
                dataUrodzenia,
                comboBox,
                status,
                numerRachunku,
                nazwaKonta,
                akcje);

        setAlignItems(FlexComponent.Alignment.CENTER);

        comboBox.setItems(Rodzaj.values());
        binder.bind(comboBox,KlientDto::getRodzajKlienta,KlientDto::setRodzajKlienta);
        status.setItems(Status.values());
        binder.bind(status,KlientDto::getStatusKlienta,KlientDto::setStatusKlienta);
        List plcie = new ArrayList<>();
        plcie.add("K");
        plcie.add("M");
        plcie.add("");
       // plec.setEmptySelectionAllowed(false);
        plec.setItems(plcie);
        //plec.setNullSelectionAllowed(true);
        //plec.setItems("K", "M");

        binder.bindInstanceFields(this);

        setSpacing(false);

        addKeyPressListener(Key.ENTER, e->zapisz());
        zapisz.addClickListener(e->zapisz());
        anuluj.addClickListener(e->edytuj(null));
        usun.addClickListener(e->usun());
        binder.addStatusChangeListener(e -> zapisz.setVisible(binder.isValid()));

        setVisible(false);
    }

    void zapisz(){
       // if (sprawdz(klient)) {
            //logger.info("poprawny klient");
            if (klient.getId() == null) {
                repozytorium.createContent(klient.getNazwa(), klient.getNIP(), klient.getDataUrodzenia(), klient.getPlec(), klient.getRodzajKlienta(), Status.AKTYWNY, klient.getNumerRachunku(), klient.getNazwaKonta());
                changeHandler.onChange();
            } else {
                repozytorium.updateContent(klient.getId(), klient.getNazwa(), klient.getNIP(), klient.getDataUrodzenia(), klient.getPlec(), klient.getRodzajKlienta(), klient.getStatusKlienta(), klient.getNumerRachunku(), klient.getNazwaKonta());
                changeHandler.onChange();
            }
            setVisible(false);
       // } else {
        //    throw new RuntimeException("nasz blad");
        //}
    }

    /*boolean sprawdz (@Valid KlientDto k){
        logger.info("w metodzie sprawdz");
        return true;
    }*/

    void usun(){
        repozytorium.deleteContent(klient.getId());
        changeHandler.onChange();
        setVisible(false);
    }

    public void setChangeHandler(ChangeHandler h){
        changeHandler = h;
    }

    public final void edytuj(KlientDto k){
    logger.info("poprawny klient");
        if (k==null){
            setVisible(false);
            return;
        }
        final boolean p = k.getId() != null;
        if (p){
            status.setVisible(true);
            naglowek.setText("Szczegóły klienta");
            var klientPom = repozytorium.findById(k.getId());//jeszcze get??????
            klient = new KlientDto(klientPom);
        }else{
            status.setVisible(false);
            naglowek.setText("Utwórz klienta");
            klient = k;
        }
        usun.setVisible(p);
        binder.setBean(klient);
        setVisible(true);
    }
}