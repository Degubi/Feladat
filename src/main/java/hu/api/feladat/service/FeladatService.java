package hu.api.feladat.service;

import java.time.*;
import java.time.format.*;
import java.util.stream.*;
import org.springframework.stereotype.*;
import hu.api.feladat.*;
import hu.api.feladat.Result.Ok;
import hu.api.feladat.Result.Error;

@Service
public final class FeladatService {

    public Result<Long, String> getNumberOfVowels(String text) {
        return new Ok<>(
            text.chars()
                .map(Character::toLowerCase)
                .filter(k -> k == 'a' || k == 'e' || k == 'i' || k == 'o' || k == 'u')
                .count()
            );
    }

    public Result<Boolean, String> isPrime(String number) {
        try {
            return new Ok<>(isPrime(Long.parseLong(number)));
        }catch(NumberFormatException _) {
            return new Error<>("Hibás/érvénytelen érték!");
        }
    }

    public Result<String, String> translateWeekOfDayName(String date) {
        try {
            return new Ok<>(switch(LocalDate.parse(date).getDayOfWeek()) {
                case MONDAY -> "hétfő";
                case TUESDAY -> "kedd";
                case WEDNESDAY -> "szerda";
                case THURSDAY -> "csütörtök";
                case FRIDAY -> "péntek";
                case SATURDAY -> "szombat";
                case SUNDAY -> "vasárnap";
            });
        }catch(DateTimeParseException _) {
            return new Error<>("Hibás/érvénytelen dátum!");
        }
    }

    private static boolean isPrime(long value) {
        return value > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(value)).noneMatch(i -> value % i == 0);
    }
}
