# Development Informationen

## Neue Schnittstellen hinzufügen

Für eine neue Schnittstelle bzw. Endpunkt müssen folgende Dateien angelegt werden:

- `<Name>Entity.java`
  - Stellt persistierbares Datenbankmodell dar
- `<Name>DTO.java`
  - Das *Data Transfer Object* zur Datenübertragung zwischen API-Schichten
- `<Name>Mapper.java`
  - Mapper zwischen DTO und Entity
- `<Name>Repository.java`
  - Schnittstelle zur Datenbank und Darstellung von Operationen wie `findAll()` oder `findById()`
- `<Name>Api.java`
  - Stellt die REST-API dar (@GET, @POST, ...)

## Problembehandlung

1. Auf Groß- und Kleinschreibung achten! In der PostGres Datenbank dürfen keine Großbuchstaben verwendet werden!
2. Die `JAVA_HOME` muss gesetzt sein
3. Datenbank Änderungen müssen am besten mit einer neuen `XML` adaptiert werden. Bestehende `XML`-Dateien sollten nicht verändert werden!

## Datenbank Erreichbarkeit

Die Postgres Datenbank läuft in einem Docker Container, auf welchen auch z.B. über die Commandline zugegriffen werden kann.

1. Docker Container auflisten: `docker ps`
2. Verbindung zum Postgres Container aufbauen: `docker exec -it <postgres_container_id_or_name> bash`
3. Verbindung zur Datenbank aufbauen: `psql -U test -d quarkus`
4. Nun können Informationen ausgelesen werden. z.B. `\d+ event_info`
