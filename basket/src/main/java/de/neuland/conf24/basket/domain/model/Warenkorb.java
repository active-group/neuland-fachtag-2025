package de.neuland.conf24.basket.domain.model;


public sealed interface Warenkorb<T extends Warenkorb<T>> permits BestellbarerWarenkorb, WarenkorbInBearbeitung {
}
