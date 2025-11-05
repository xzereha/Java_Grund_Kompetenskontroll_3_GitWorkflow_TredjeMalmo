# Plan

- ✅ BokningsRepository
- ✅ Prislista (Mimmi)
- ✅ BokningsService 
  - Skapa Bokning  - Skapa Bokning, Pontus, Tilda - bygger komponent i Test
    - Skicka mail
  - Redigera Bokning- Pontus, Tilda - bygger komponent i Test
  - Ta bort Bokning- Pontus, Tilda - bygger komponent i Test
  - Visa Bokningar- Pontus, Tilda - bygger komponent i Test
  - Visa bokningsdetaljer- Pontus, Tilda - bygger komponent i Test
- Meny | in progress|  Oliver
- ✅ Sortering | Mario & Vera
- ✅ Init (skapa bokningar vid start) (Mimmi)
- ✅ MailService (Mimmi)
- AvklaradService 
  - Avsluta bokning
    - Sätt pris
  - Skicka Mail


## Dependencies
- BokningsService
  - BokningsRepository
  - Prislista
  - MailService
- Sortering
  - BokningsRepository
- Init
  - BokningsRepository
- AvklaradService
  - BokningsRepostory
  - MailService
  - (BokningsService)
- Meny
  - BokningsService
  - Sortering
  - AvklaradService 
