package com.example.Sklep_z_ksiazkami;

/*

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
    TextField nazwaKonta = new TextField("Nazwa Konta Bankowego");
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
        nazwa.setEnabled(false);
        nip.setEnabled(false);
        plec.setEnabled(false);
        dataUrodzenia.setEnabled(false);
        numerRachunku.setEnabled(false);
        nazwaKonta.setEnabled(false);
        comboBox.addClassName("rodzaj");


        add(
                naglowekDiv,
                comboBox,
                nazwa,
                nip,
                plec,
                dataUrodzenia,
                status,
                numerRachunku,
                nazwaKonta,
                akcje);

        setAlignItems(FlexComponent.Alignment.CENTER);

        comboBox.setItems(Rodzaj.values());
        binder.bind(comboBox,KlientDto::getRodzajKlienta,KlientDto::setRodzajKlienta);
        comboBox.addValueChangeListener(e ->{
            nazwa.setEnabled(e.getValue()!=null);
            nip.setEnabled(e.getValue()!=null);
            plec.setEnabled(e.getValue()!=null);
            dataUrodzenia.setEnabled(e.getValue()!=null);

            numerRachunku.setEnabled(e.getValue()!=null);
            nazwaKonta.setEnabled(e.getValue()!=null);

            if (e.getValue()!=null){
                nip.setVisible(e.getValue().toString()=="FIRMOWY");
                plec.setVisible(e.getValue().toString()=="INDYWIDUALNY");
                dataUrodzenia.setVisible(e.getValue().toString()=="INDYWIDUALNY");
                if(e.getValue().toString()=="INDYWIDUALNY"){
                    nazwa.setLabel("Imię i nazwisko");
                }else{
                    nazwa.setLabel("Nazwa");
                }
            }
        });

        status.setItems(Status.values());
        binder.bind(status,KlientDto::getStatusKlienta,KlientDto::setStatusKlienta);
        List plcie = new ArrayList<>();
        plcie.add("K");
        plcie.add("M");

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
            repozytorium.createContent(klient.getNazwa(), klient.getNIP(), klient.getDataUrodzenia(), klient.getPlec(), klient.getRodzajKlienta(), Status.ACTIVE, klient.getNumerRachunku(), klient.getNazwaKonta());
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
        comboBox.setEnabled(!p);
        usun.setVisible(p);
        binder.setBean(klient);
        setVisible(true);
    }
}*/