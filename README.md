# Travel Planner - Reiseplanlegger
## 📋 Prosjektoversikt
Dette er et objektorientert Java-prosjekt utviklet som del av TDT4100-kurset våren 2023. Applikasjonen lar brukere søke etter tilgjengelige reiser basert på avreisested, destinasjon og transportmiddel, samt booke disse reisene.

### Hovedfunksjonalitet
- 🔍 Søke etter tilgjengelige reiser
- ✈️ Støtte for flere transportmidler (fly, bil, båt)
- 💺 Håndtering av kapasitet og booking
- 💰 Dynamisk prisberegning basert på destinasjon og transportmiddel
- 💾 Persistent datalagring med filhåndtering

## 🏗️ Arkitektur og Modell

Prosjektet følger Model-View-Controller (MVC) arkitekturen, og er strukturert som følger:

### Modellklasser
- **`Vehicle`**: Representerer et transportmiddel med avreise, destinasjon, type og kapasitet
- **`Hub`**: Hovedklasse som håndterer søk, booking og administrasjon av alle kjøretøy
- **`Filehandler`**: Ansvarlig for lesing og skriving av data til/fra filer

### View og Controller
- **`BookingApp`**: JavaFX-applikasjonen som starter brukergrensesnittet
- **`BookingController`**: Controller som kobler brukergrensesnitt med forretningslogikk
- **`Booking.fxml`**: FXML-fil som definerer brukergrensesnittets layout

## 🧪 Testing og Kvalitetssikring
Prosjektet inkluderer brukertester for å sikre korrekt funksjonalitet:

### Implementerte tester
- **`CheckValidInput`**: Validerer brukerinput og håndtering av ugyldig data
- **`GetRelevantVehiclesTest`**: Tester søkefunksjonalitet for å finne relevante reiser
- **`CheckReduceCapacity`**: Verifiserer at kapasitet reduseres korrekt ved booking
- **`checkGetPrice`**: Tester logikken bak priskalkulering

### Testdekning
Testene dekker følgende områder:
- ✅ Input-validering og feilhåndtering
- ✅ Søkelogikk og filtrering
- ✅ Booking og kapasitetshåndtering
- ✅ Prisberegning basert på ulike kriterier
- ✅ Filhåndtering og datapersistens

### Kjøring av tester
Testene ligger i `src/test/java/myProject/` og kan kjøres individuelt eller samlet.

## 💡 Nøkkelfunksjoner

### Intelligent prisberegning
Prosjektet inkluderer også logikk for enkel prisberegning, som kan justeres etter ved behov. Jeg har valgt å implementere en lett kalkuleringsmodell for simplisitet.
- Basispris varierer med transportmiddel (Fly: 3x, Båt: 2x, Bil: 1x)
- Geografisk prising: Dyrere byer (London, New York, Los Angeles) gir 50% påslag
- Billigere byer (Trondheim, Oslo, Bergen) gir 20% rabatt
- Lav kapasitet (under 10 plasser) gir 20% rabatt

### Robust søkefunksjonalitet
- Fleksibel søking: Kun avreise, kun destinasjon, eller begge
- Case-insensitive søk med trimming av mellomrom
- Filtrering på transportmiddel
- Detaljert feilhåndtering med informative meldinger

### Dynamisk booking
- Automatisk kapasitetsreduksjon ved booking
- Kjøretøy fjernes når kapasitet når null
- Persistent lagring av endringer

## 📁 Prosjektstruktur

```
Travel-Planner-TDT4100/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java
│   │   │   ├── exampleproject/          # Eksempelprosjekt (kan fjernes)
│   │   │   └── myProject/               # Hovedprosjekt
│   │   │       ├── BookingApp.java      # JavaFX-applikasjon (Main)
│   │   │       ├── BookingController.java # Controller for GUI
│   │   │       ├── Hub.java             # Hovedforretningslogikk
│   │   │       ├── Vehicle.java         # Modellklasse for kjøretøy
│   │   │       └── Filehandler.java     # Filhåndtering
│   │   └── resources/
│   │       └── myProject/
│   │           ├── Booking.fxml         # GUI-layout
│   │           ├── images/
│   │           └── saves/
│   │               └── trips.txt        # Datalagrings
│   │
│   └── test/
│       └── java/
│           └── myProject/               # Brukertester
│               ├── CheckValidInput.java
│               ├── GetRelevantVehiclesTest.java
│               ├── CheckReduceCapacity.java
│               └── checkGetPrice.java
│
├── target/                             # Kompilerte filer (Maven)
├── pom.xml                            # Maven-konfigurasjon
└── README.md                          # Dette dokumentet
```

## 🔧 Tekniske detaljer

### Objektorienterte prinsipper
- **Innkapsling**: Private felter med public getter-metoder
- **Arv og interface**: `Vehicle` implementerer `Comparable<Vehicle>`
- **Polymorfisme**: Behandling av ulike kjøretøytyper via samme interface
- **Abstraksjon**: Klar separasjon mellom model, view og controller

### Designmønstre
- **MVC (Model-View-Controller)**: Klar separasjon av ansvar
- **Singleton-lignende**: Hub-klassen fungerer som sentral koordinator
- **Strategy pattern**: Ulik prisberegning basert på kjøretøytype

### Feilhåndtering
- Validering av brukerinput med informative feilmeldinger
- `IllegalArgumentException` for ugyldige parametere
- `IllegalStateException` for forretningslogikk-feil

### Mulige forbedringer
- Grafisk tidslinje for reiser
- Brukerkonti og booking-historikk  
- Integrasjon med eksterne API-er for sanntidsdata
- Database-integrasjon (PostgreSQL/MySQL)
- Avansert søkefunksjonalitet med filtre og sortering

### Kjente begrensninger
- Begrenset til forhåndsdefinerte ruter
- Ingen sanntids oppdatering av tilgjengelighet
- Enkel filbasert lagring

## 📚 Læringsutbytte

Dette prosjektet demonstrerer følgende TDT4100-konsepter:
- Objektorientert programmering i Java
- JavaFX for grafiske brukergrensesnitt
- Filhåndtering og datapersistens
- Unittesting og kvalitetssikring
- MVC-arkitekturmønster
- Håndtering av exceptions og feilsituasjoner

---

**Kursinfo**: TDT4100 - Objektorientert programmering  
**Semester**: Vår 2023  
**Institusjon**: Norges teknisk-naturvitenskapelige universitet (NTNU)
