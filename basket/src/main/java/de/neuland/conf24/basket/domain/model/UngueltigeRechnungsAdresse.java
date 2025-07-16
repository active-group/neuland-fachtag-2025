package de.neuland.conf24.basket.domain.model;


public sealed interface UngueltigeRechnungsAdresse extends GrundFuerUngueltigkeit {
  record Fehlt() implements UngueltigeRechnungsAdresse {
    @Override
    public FehlerBeschreibung beschreibung() {
      return new FehlerBeschreibung("Bitte geben Sie eine Rechnungsadresse ein");

    }
  }

  record UnbekannteAnschirft() implements UngueltigeRechnungsAdresse {
    @Override
    public FehlerBeschreibung beschreibung() {
      throw new UnsupportedOperationException("FIXME: Implement");
    }
  }
}
