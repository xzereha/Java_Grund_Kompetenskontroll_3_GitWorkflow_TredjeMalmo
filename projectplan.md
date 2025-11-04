# Plan

- BokningsRepository
- Prislista
- BokningsService - Pontus, Tilda
  - Skapa Bokning
    - Skicka mail
  - Redigera Bokning
  - Ta bort Bokning
  - Visa Bokningar
  - Visa bokningsdetaljer
- Meny
- Sortering
- Init (skapa bokningar vid start)
- MailService
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
