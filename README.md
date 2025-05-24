# Parancsok
- Alkalmazás futtatása: ./mvnw package -DskipTests && java -jar target/Feladat-1.0.0.jar
- Tesztek futtatása: ./mvnw clean && ./mvnw test
- Docker image buildelése: ./mvnw package -DskipTests && sudo docker build -t feladat/feladat-application .
- Docker image futtatás: sudo docker run -p 8080:8080 feladat/feladat-application

# Endpointok
- /melyiknap?DATUM: A paraméterben megadott dátum alapján adja vissza, hogy ez melyik napja a hétnek (például: szerda).
- /primszam?SZAM: A paraméterben megadott szám alapján adja vissza, hogy ez a szám prímszám-e.
- /maganhangzo?SZOVEG: A paraméterben megadott szöveg alapján adja vissza, hogy hány darab magánhangzót tartalmaz (a magyar ABC szerint).
