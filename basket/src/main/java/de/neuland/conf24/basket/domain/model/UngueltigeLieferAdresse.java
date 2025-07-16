package de.neuland.conf24.basket.domain.model;


public record UngueltigeLieferAdresse(Adresse ungueltigeAdresse) implements GrundFuerUngueltigkeit {
  @Override
  public FehlerBeschreibung beschreibung() {
    return new FehlerBeschreibung("Ungültige Lieferadresse: " + ungueltigeAdresse);
  }
}
