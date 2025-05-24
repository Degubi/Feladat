package hu.api.feladat.controller;

import org.springframework.web.bind.annotation.*;
import hu.api.feladat.*;
import hu.api.feladat.service.*;

@RestController
public final class FeladatController {

    private final FeladatService service;

    public FeladatController(FeladatService service) {
        this.service = service;
    }

    @GetMapping("/melyiknap")
    public Result<String, String> melyiknap(@RequestParam("DATUM") String date) {
        return service.translateWeekOfDayName(date);
    }

    @GetMapping("/primszam")
    public Result<Boolean, String> primszam(@RequestParam("SZAM") String number) {
        return service.isPrime(number);
    }

    @GetMapping("/maganhangzo")
    public Result<Long, String> maganhangzo(@RequestParam("SZOVEG") String text) {
        return service.getNumberOfVowels(text);
    }
}
