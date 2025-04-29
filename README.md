<br>
<h3 align="center">
  <a href="https://github.com/FH-Joanneum-Iron-Road-for-Children/.github/blob/develop/profile/images/logo.png">
  <img src="https://github.com/FH-Joanneum-Iron-Road-for-Children/.github/blob/develop/profile/images/logo.png" alt="IRFC Logo" width="250" style="border-radius: 50px;">
  </a>
</h3>
<br>

# Iron Road For Children Backend (IRFC)

## TL;DR

Zum Starten des Projekt werden Java 17, Docker und [Quarkus](https://quarkus.io/get-started/) benötigt.

Wenn alle Abhängigkeiten installiert wurden, kann das Projekt local gestartet werden:

```shell script
.\mvnw compile quarkus:dev
```

## Quarkus Weboberfläche

Über die Weboberfläche können interne Backend-Einstellungen überprüft werden und die API mit Hilfe von [Swagger UI](https://swagger.io/tools/swagger-ui/) überprüft und getestet werden.

### localhost

* Swagger UI: <http://localhost:8080/q/swagger-ui/>
* Dev UI: <http://localhost:8080/q/dev/>

### Testsystem

* Swagger UI: <https://backend.irfc-test.fh-joanneum.at/q/swagger-ui>

### Prodsystem

* Swagger UI: <https://backend.irfc.fh-joanneum.at/q/swagger-ui>

## [Running the application in dev mode](./doc/Quarkus.md)

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/> .

## [Development](./doc/Development.md)

## Endpoints

Diese Endpunkte werden in der WebApp, der Android App und IOs App verwendet.

### Countdown Api

In der Datenbank wird ein einzelner Datumswert gespeichert und verwendet. Dieser kann verwendet werden, um den Countdown bis zum Start von Iron Road For Children zu berechnen.

## Event Location Api

Endpunkte zur Abfrage oder Speichern eines Orts, an welchem ein jeweiliges Event stattfindet.

## Event Category Api

Endpunkte zur Abfrage oder Speichern der zugewiesenen Kategorie eines Events. Beispielsweise "Musik", "Tattoo" oder "Essen".

## Event Info Api

Endpunkte zur Abfrage oder Speichern von Informationen zu einzelnen Events.

## Event Api

Endpunkte für Events, welche aus einem picture, einer Kategorie, Ort, Info und einem Datum bestehen.

## Gallery Api

Endpunkte für die Gallery, welche in der App dargestellt wird.

## Highlight Api

Highlights werden in der App, im Main Screen angezeigt und können aus Bildern oder Videos bestehen.

## Intro Video Api

Endpunkte für das Teaser Video im Main Screen. Es kann derzeit immer nur ein einzelnes Video verwendet werden. **Wird dieses bearbeitet wird das vorhandene Intro Video entfernt!**

## Picture Api

Endpunkte für die Bilder, welche zu einem jeweiligen Event hinzugefügt werden.

## Playlist Api

Endpunkte zur Verwendung der Spotify playlist. Es kann derzeit nur eine einzelne playlist gespeichert werden. Dabei handelt es sich jedoch nur, um den **Link** zur verwendeten Spotify playlist. **Wird dieser bearbeitet wird die vorhandene Playlist entfernt!**
