# README
Readme for Plan-it.
Eksamensprojekt, 2. semester, Dat21v2. KEA, december, 2021.

## Forudsætninger

Du har brug for følgende for at køre dette program:

-   Du har et udviklingsmiljø der understøtter Java-udvikling, såsom IntelliJ
-   Du har en SQL-server på din computer og eventuelt et program, såsom MySQL Workbench
-   En GitHub-konto

## Kør programmet fra udviklingsmiljø

Brug følgende metode for at køre programmet lokalt via din egen maskine.
```
1. Klon projektet
2. Åbn projektet i et udviklingsmiljø, såsom IntelliJ
3. Brug de vedlagte SQL-scripts til at oprette en database med testdata. Brug eventuelt MySQL Workbench
4. Indtast environment variables
5. Forbind projektet til databasen
6. Hvis der er problemer med at forbinde, så tjek eventuelt om 8080 er optaget, da Spring Boot benytter en Tomcat server, som kræver denne port for at kunne køre
7. Kør programmet
8. Skriv "localhost:8080" i en browser for at starte applikationen
```


## Upload til Heroku

For at uploade dit program til Heroku, skal du gøre følgende:

```
1. Upload dit projekt til GitHub.
2. Log ind på Heroku eller opret en ny bruger
3. Indtast environment variables under settings>Config Vars>Reveal Config Vars
4. Vælg GitHub som deployment method og vælg dit repository
5. Under "Manual deploy" vælg master branch og tryk på deploy

```

## Gruppe

Dette projekt er udviklet af gruppe 1, som består af følgende personer:

- Tobias Vinther   [@tobiasvinther](https://github.com/tobiasvinther) 
- Jonatan Segal	  [@JonatanSegal](https://github.com/JonatanSegal) 
- Thony Dyreborg-Kragh	  [@ThonyDK](https://github.com/ThonyDK) 
