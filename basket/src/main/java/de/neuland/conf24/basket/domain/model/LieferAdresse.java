package de.neuland.conf24.basket.domain.model;


public sealed interface LieferAdresse {

  static LieferAdresse wieRechnungsAdresse() {
    return new AnRechnungsAdresse();
  }

  record AnRechnungsAdresse() implements LieferAdresse {
  }

  record AbweichendeStraßenanschrift(Adresse adresse) implements LieferAdresse {
  }


}
