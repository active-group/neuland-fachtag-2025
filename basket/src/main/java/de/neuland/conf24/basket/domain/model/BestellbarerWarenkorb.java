package de.neuland.conf24.basket.domain.model;


import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class BestellbarerWarenkorb implements Warenkorb<BestellbarerWarenkorb>{
  LineItems lineItems;
  RechnungsAdresse rechnungsAdresse;
  LieferAdresse lieferAdresse = LieferAdresse.wieRechnungsAdresse();
  Zahlart zahlart;
}
