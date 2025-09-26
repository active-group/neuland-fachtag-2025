module Warenkorb where

data Warenkorb =
    Warenkorb (Maybe (Produkt, Menge)) (Maybe Lieferadresse)
  | UngueltigerWarenkorb      Produkt Menge Lieferadresse Problem
  | CheckoutFertigerWarenkorb Produkt Menge Lieferadresse
  deriving Show

fuegeProduktHinzu :: Produkt -> Menge -> Warenkorb -> Warenkorb
fuegeProduktHinzu Lifestyle menge (Warenkorb Nothing Nothing) =
    Warenkorb (Just (Lifestyle, menge)) Nothing
fuegeProduktHinzu Sperrgut menge (Warenkorb Nothing (Just lieferadresse@(Packstation {}))) =
    UngueltigerWarenkorb Sperrgut menge lieferadresse SperrgutGehtNichtAnPackstation

-- >>> fuegeProduktHinzu Lifestyle (Menge 5) (Warenkorb Nothing Nothing)
-- Warenkorb (Just (Lifestyle,Menge 5)) Nothing

-- >>> fuegeProduktHinzu Sperrgut (Menge 5) (Warenkorb Nothing (Just mikePackstation))
-- UngueltigerWarenkorb Sperrgut (Menge 5) (Packstation (Name "Mike") (Packstationsnummer 15) (Postnummer 42213) (PLZ "72070") (Kommune "T\252bingen")) SperrgutGehtNichtAnPackstation

data Menge = Menge Integer
  deriving (Show)

data Problem = SperrgutGehtNichtAnPackstation
  deriving Show

data Produkt = 
    Sperrgut
  | Lifestyle
  deriving Show

data Lieferadresse =
    Packstation Name Packstationsnummer Postnummer PLZ Kommune
  | Straßenadresse Name StraßeHausnummer PLZ Kommune
  deriving Show

mike = Name "Mike"
mikeZuHause = 
    Straßenadresse mike 
       (StraßeHausnummer "Pappelweg 2") (PLZ "72076") (Kommune "Tübingen")

mikePackstation =
    Packstation mike (Packstationsnummer 15) (Postnummer 42213) (PLZ "72070") (Kommune "Tübingen")

data Name = Name String
  deriving Show

data StraßeHausnummer = StraßeHausnummer String
  deriving Show

data Packstationsnummer = Packstationsnummer Integer
  deriving Show

data Postnummer = Postnummer Integer
  deriving Show

data PLZ = PLZ String
  deriving Show

data Kommune = Kommune String
  deriving Show
