package de.neuland.conf24.basket.domain.model;


import io.vavr.collection.List;

import java.util.function.Function;

import static de.neuland.conf24.basket.domain.model.LieferAdresse.wieRechnungsAdresse;
import static de.neuland.conf24.basket.domain.model.VielleichtGueltig.gueltig;

@SuppressWarnings("unchecked")
public final class WarenkorbInBearbeitung implements Warenkorb<WarenkorbInBearbeitung>{
  LineItems lineItems = LineItems.empty();
  VielleichtGueltig<RechnungsAdresse, UngueltigeRechnungsAdresse> rechnungsAdresse;
  VielleichtGueltig<LieferAdresse, UngueltigeLieferAdresse> lieferAdresse = gueltig(wieRechnungsAdresse());
  VielleichtGueltig<Zahlart, UngueltigeZahlart> zahlart;

  public WarenkorbInBearbeitung add(LineItem lineItem) {
    lineItems = lineItems.add(lineItem);
    zahlart = zahlart.flatMap(lineItems::validiereZahlart);
    lieferAdresse = lieferAdresse.flatMap(lineItems::validiereLieferAdresse);
    return this;
  }

  public WarenkorbInBearbeitung zahlart(Zahlart zahlart) {
    this.zahlart = lineItems.validiereZahlart(zahlart);
    this.lieferAdresse = lieferAdresse.flatMap(lineItems::validiereLieferAdresse);
    return this;
  }


  public VielleichtGueltig<BestellbarerWarenkorb, UngueltigerWarenkorb> validiere() {
    return pruefeAdressenUndZahlArt()
        .mapUngueltig(fehler -> new UngueltigerWarenkorb(this, fehler));
  }


  private UngueltigerWarenkorb ungueltigerWarenkorb(GrundFuerUngueltigkeit grund) {
    return new UngueltigerWarenkorb(this, List.of(grund.beschreibung()));
  }

  private VielleichtGueltig<BestellbarerWarenkorb, List<FehlerBeschreibung>> pruefeAdressenUndZahlArt() {
    return rechnungsAdresse.fold(this::pruefeLieferAdresseUndZahlart,
                                 u -> ungueltig(u, lieferAdresse, zahlart)
    );
  }

  private VielleichtGueltig<BestellbarerWarenkorb, List<FehlerBeschreibung>> pruefeLieferAdresseUndZahlart(RechnungsAdresse ra) {

    return lieferAdresse.fold(g -> pruefeZahlArt(ra, g),
                              u -> ungueltig(u, zahlart)
    );

  }

  private VielleichtGueltig<BestellbarerWarenkorb, List<FehlerBeschreibung>> pruefeZahlArt(RechnungsAdresse ra, LieferAdresse la) {
    return zahlart.fold(g -> gueltig(new BestellbarerWarenkorb(lineItems, ra, la, g)),
                        this::ungueltig
    );
  }


  private VielleichtGueltig<BestellbarerWarenkorb, List<FehlerBeschreibung>> ungueltig(GrundFuerUngueltigkeit grund, VielleichtGueltig<?, ? extends GrundFuerUngueltigkeit>... weitere) {
    return VielleichtGueltig.ungueltig(List.of(weitere)
                                           .map(u -> u.mapUngueltig(GrundFuerUngueltigkeit::beschreibung))
                                           .map(VielleichtGueltig::ungueltigOption)
                                           .flatMap(Function.identity())
                                           .prepend(grund.beschreibung()));
  }

}
