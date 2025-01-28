package com.example.Sklep_z_ksiazkami;

import com.example.Sklep_z_ksiazkami.Model.KlientDto;
import com.example.Sklep_z_ksiazkami.Repozytorium.Repozytorium;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.List;


/*
"Id", "Nazwa", "NIP", "Data Urodzenia", "Plec", "Rodzaj Klienta", "Status Klienta","Numer Rachunku", "Nazwa Konta"
 klient.nazwa(),klient.NIP(), klient.dataUrodzenia(), klient.plec(),
                klient.rodzajKlienta(), klient.statusKlienta(), klient.numerRachunku(), klient.nazwaKonta()
 */
@Route("")
@SpringComponent
@UIScope
public class WidokKlienci extends SplitLayout {
    private final Repozytorium repozytorium;
    private final EdytorWidok edytor;
    final Grid<KlientDto> grid;

    final TextField szukaj;

    private final Button nowyKlientButton;
    //private ChangeHandler changeHandler;

    public WidokKlienci(Repozytorium repozytorium, EdytorWidok edytor) {

        this.repozytorium = repozytorium;
        this.edytor = edytor;
        this.nowyKlientButton = new Button("Nowy klient ", VaadinIcon.PLUS.create());
        this.szukaj = new TextField();
        grid = new Grid<>(KlientDto.class, false);

        grid.addColumn(KlientDto::getNazwa).setHeader("Nazwa");
        grid.addColumn(KlientDto::getNIP).setHeader("NIP");
        grid.addColumn(KlientDto::getDataUrodzenia).setHeader("Data Urodzenia");
        grid.addColumn(KlientDto::getPlec).setHeader("Plec") .setWidth("12px");
        grid.addColumn(KlientDto::getRodzajKlienta).setHeader("Rodzaj Klienta");
        grid.addColumn(KlientDto::getNumerRachunku).setHeader("Numer Rachunku");
        grid.addColumn(KlientDto::getNazwaKonta).setHeader("Nazwa Konta Bankowego");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);


        HorizontalLayout akcje = new HorizontalLayout(szukaj, nowyKlientButton);

        szukaj.setPlaceholder("Szukaj po nazwie");
        szukaj.setPrefixComponent(VaadinIcon.SEARCH.create());
        szukaj.setValueChangeMode(ValueChangeMode.LAZY);
        szukaj.addValueChangeListener(e -> wypiszKlientow(e.getValue()));
        szukaj.setWidth("400px");

        var naglowek = new HorizontalLayout();
        var h = new H1("Klienci");
        Image image = new Image("Ikona.png", "Ikona");
        image.setWidth("12%");

        naglowek.add(image, h);
        var lewy = new VerticalLayout(naglowek, akcje, grid);
        lewy.setMargin(true);

        addToPrimary(lewy);
        addToSecondary(edytor);
        setSplitterPosition(72);
        addThemeVariants(SplitLayoutVariant.LUMO_SMALL);

        grid.asSingleSelect().addValueChangeListener(e->{
            edytor.edytuj(e.getValue());
        });
        nowyKlientButton.addClassName("nowy");

        nowyKlientButton.addClickListener(e->edytor.edytuj(new KlientDto()));

        edytor.setChangeHandler(()->{
            edytor.setVisible(false);
            wypiszKlientow(szukaj.getValue());
        });

        wypiszKlientow(null);

    }

    void wypiszKlientow (String nazwa){
        if (nazwa ==null || nazwa.isEmpty()){
            //grid.setItems(repozytorium.findAll().stream().collect(new KlientDto(Klient::)));
            List<KlientDto> klientDtoList = repozytorium.findAll().stream().map(KlientDto::KlientToKlientDto).toList(); // collect the result as a List
            grid.setItems(klientDtoList);
        } else {
            //grid.setItems(new KlientDto(repozytorium.findByNazwaKonta(nazwa)));
            List<KlientDto> klientDtoList = repozytorium.findByNazwaKonta(nazwa).stream().map(KlientDto::KlientToKlientDto).toList(); // collect the result as a List
            grid.setItems(klientDtoList);
        }
    }

}
