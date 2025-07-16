package de.neuland.conf24.basket.domain.model;


import io.vavr.control.Option;

import static de.neuland.conf24.basket.domain.model.VielleichtGueltig.gueltig;

public class LineItems {
  public static LineItems empty() {
    throw new UnsupportedOperationException("FIXME: Implement");

  }

  public LineItems add(LineItem lineItem) {
    return this;

  }

  public VielleichtGueltig<Zahlart, UngueltigeZahlart> validiereZahlart(Zahlart zahlart) {
    return gueltig(zahlart);
  }

  public VielleichtGueltig<LieferAdresse, UngueltigeLieferAdresse> validiereLieferAdresse(LieferAdresse adresse) {
    return gueltig(adresse);
  }
}
