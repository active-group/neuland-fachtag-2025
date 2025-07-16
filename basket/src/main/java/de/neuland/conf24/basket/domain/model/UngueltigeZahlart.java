package de.neuland.conf24.basket.domain.model;


public record UngueltigeZahlart(Zahlart zahlart, AblehnungsGrund ablehnungsGrund) implements GrundFuerUngueltigkeit {
  @Override
  public FehlerBeschreibung beschreibung() {
    return switch (ablehnungsGrund) {
      case AblehnungsGrund.ArtikelZuHochWertig lineItem -> new FehlerBeschreibung("Artikel zu hochwertig: " + lineItem);
    };

  }

  private sealed interface AblehnungsGrund {
    record ArtikelZuHochWertig(LineItem lineItem) implements AblehnungsGrund {}
  }
}
