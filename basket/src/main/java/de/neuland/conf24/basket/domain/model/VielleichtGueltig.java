package de.neuland.conf24.basket.domain.model;


import io.vavr.control.Option;

import java.util.function.Function;

public sealed interface VielleichtGueltig<G, U> {
  static <G, U> VielleichtGueltig<G, U> gueltig(G wert) {
    return new Gueltig<>(wert);
  }

  static <G, U> VielleichtGueltig<G, U> ungueltig(U grund) {
    return new Ungueltig<>(grund);
  }

  default <T> T fold(Function<G, T> gueltigMapper, Function<U, T> ungueltigMapper) {
    return switch (this) {
      case Gueltig(G wert) -> gueltigMapper.apply(wert);
      case Ungueltig(U grund) -> ungueltigMapper.apply(grund);
    };
  }

  <G1> VielleichtGueltig<G1, U> map(Function<G, G1> mapper);

  <U1> VielleichtGueltig<G, U1> mapUngueltig(Function<U, U1> mapper);

  <G2> VielleichtGueltig<G2, U> flatMap(Function<G, VielleichtGueltig<G2, U>> flatMapper);

  Option<U> ungueltigOption();


  record Gueltig<G, U>(G value) implements VielleichtGueltig<G, U> {
    @Override
    public <G1> VielleichtGueltig<G1, U> map(Function<G, G1> mapper) {
      return new Gueltig<>(mapper.apply(value));
    }

    @Override
    public <U1> VielleichtGueltig<G, U1> mapUngueltig(Function<U, U1> mapper) {
      return new Gueltig<>(value);
    }

    @Override
    public <G2> VielleichtGueltig<G2, U> flatMap(Function<G, VielleichtGueltig<G2, U>> flatMapper) {
      return flatMapper.apply(value);
    }

    @Override
    public Option<U> ungueltigOption() {
      return Option.none();
    }
  }

  record Ungueltig<G, U>(U grund) implements VielleichtGueltig<G, U> {
    @Override
    public <G1> VielleichtGueltig<G1, U> map(Function<G, G1> mapper) {
      return new Ungueltig<>(grund);
    }

    @Override
    public <U1> VielleichtGueltig<G, U1> mapUngueltig(Function<U, U1> mapper) {
      return new Ungueltig<>(mapper.apply(grund));
    }

    @Override
    public <G2> VielleichtGueltig<G2, U> flatMap(Function<G, VielleichtGueltig<G2, U>> flatMapper) {
      return new Ungueltig<>(grund);
    }

    @Override
    public Option<U> ungueltigOption() {
      return Option.some(grund);
    }
  }
}
