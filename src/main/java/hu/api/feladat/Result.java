package hu.api.feladat;

public sealed interface Result<T, E> {
    public record Ok<T>(T value) implements Result<T, String> {}
    public record Error<T>(String message) implements Result<T, String> {}
}
