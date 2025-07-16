package de.neuland.conf24.basket.domain.model;


import java.time.LocalDateTime;

public sealed interface Zahlart {

  record PayPal() implements Zahlart {}
  record Rechnung(LocalDateTime scoringAkzeptiert) implements Zahlart {}
  record Lastschrift(IBAN iban) implements Zahlart {}

}
