module Cart where

-- Warenkorb hat:
-- - Produkt
-- - Lieferadresse
data Warenkorb =
    Warenkorb (Maybe Produkt) (Maybe Lieferadresse)
  | FehlerhafterWarenkorb Produkt Lieferadresse Fehler
  | BestellbarerWarenkorb Produkt Lieferadresse
  deriving Show

leererWarenkorb = Warenkorb Nothing Nothing

packstation = Packstation (PackstationsNummer 69) (Postnummer "42") (PLZ "13409") (Kommune "Berlin")

produktHinzufuegen :: Produkt -> Warenkorb -> Warenkorb
produktHinzufuegen Moebel (Warenkorb _ (Just adresse@(Packstation {}))) =
  FehlerhafterWarenkorb Moebel adresse KeineMoebelAnPackstation
produktHinzufuegen Moebel (Warenkorb _ maybeAdresse) =
  Warenkorb (Just Moebel) maybeAdresse
produktHinzufuegen Lifestyle (Warenkorb _ _) = undefined
produktHinzufuegen Moebel (FehlerhafterWarenkorb _ _ _) = undefined
produktHinzufuegen Lifestyle (FehlerhafterWarenkorb _ _ _) = undefined
produktHinzufuegen Moebel (BestellbarerWarenkorb _ _) = undefined
produktHinzufuegen Lifestyle (BestellbarerWarenkorb _ _) = undefined

data Fehler = KeineMoebelAnPackstation
  deriving Show

data Produkt = Moebel | Lifestyle
  deriving Show

data Lieferadresse =
    Packstation PackstationsNummer Postnummer PLZ Kommune
  | Straßenadresse Straße Hausnummer PLZ Kommune
  deriving Show

data PackstationsNummer = PackstationsNummer Int
  deriving Show

data Postnummer = Postnummer String
  deriving Show

data Straße = Straße String
  deriving Show

data Hausnummer = Hausnummer String
  deriving Show

data PLZ = PLZ String
  deriving Show

data Kommune = Kommune String
  deriving Show


