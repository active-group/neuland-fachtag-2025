package de.neuland.conf24.basket.domain.model;


import io.vavr.collection.List;

public record UngueltigerWarenkorb(WarenkorbInBearbeitung warenkorb, List<FehlerBeschreibung> beschreibung) {
}
