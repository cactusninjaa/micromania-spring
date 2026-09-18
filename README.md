# Micromania API

API REST Spring Boot qui gere le stock et les ventes d'une chaine de magasins de jeux video : produits (consoles, jeux video, accessoires), clients, employes et magasins.
Chaque produit expose, en plus du CRUD, le calcul de son prix de revente ainsi que les operations de vente et de reapprovisionnement.

## Stack

Java 21 · Spring Boot 4.1.1 (Web, Data JPA) · PostgreSQL · springdoc-openapi + Scalar · Docker / Docker Compose

## Prerequis

- Docker et Docker Compose (mode recommande)
- ou, pour un lancement local : JDK 21 et un PostgreSQL accessible (Maven est fourni via le wrapper `./mvnw`)

## Variables d'environnement

Copier le modele puis renseigner les valeurs :

```bash
cp .env.example .env
```

Le fichier `.env` est ignore par git : il ne doit jamais etre commite.

### Utilisees par Docker Compose (`.env`)

| Variable | Description | Exemple |
|---|---|---|
| `YOUR_USERNAME` | utilisateur PostgreSQL | `postgres` |
| `YOUR_PASSWORD` | mot de passe PostgreSQL | `change-me` |
| `YOUR_DB_NAME` | nom de la base | `micromania` |
| `LOCAL_PORT` | port de l'API expose sur la machine hote | `8080` |
| `DOCKER_PORT` | port ecoute par l'API dans le conteneur, a laisser a `8080` | `8080` |
| `DB_LOCAL_PORT` | port PostgreSQL expose sur la machine hote | `5432` |

En conteneur, Compose injecte directement `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` et `SPRING_DATASOURCE_PASSWORD`, qui surchargent la configuration de `application.properties`.

### Utilisees en lancement local (hors Docker)

`src/main/resources/application.properties` lit ces variables, avec une valeur par defaut entre `:` et `}` :

| Variable | Defaut | Description |
|---|---|---|
| `DB_HOST` | `localhost` | hote PostgreSQL |
| `DB_PORT` | `5432` | port PostgreSQL |
| `DB_NAME` | `micromania` | nom de la base |
| `DB_USER` | `postgres` | utilisateur PostgreSQL |
| `DB_PASSWORD` | `micromania` | mot de passe PostgreSQL |

## Lancer le projet

### Avec Docker Compose (API + base)

```bash
docker compose up --build
```

L'API est alors disponible sur `http://localhost:${LOCAL_PORT}` (8080 par defaut). Pour arreter :

```bash
docker compose down
```

Le service `db` n'a pas de volume : les donnees sont perdues a chaque `docker compose down`.

### En local avec Maven

Necessite un PostgreSQL deja demarre (ou uniquement la base via `docker compose up db`).

```bash
./mvnw spring-boot:run
```

Autres commandes utiles :

```bash
./mvnw clean package      # construire le jar dans target/
./mvnw test               # lancer les tests
java -jar target/*.jar    # executer le jar construit
```

Le schema de la base est cree et mis a jour automatiquement au demarrage (`spring.jpa.hibernate.ddl-auto=update`).

## Documentation de l'API

Application demarree :

- **Documentation interactive (Scalar)** : http://localhost:8080/doc.html
- **Specification OpenAPI brute (JSON)** : http://localhost:8080/v3/api-docs

La page Scalar est servie depuis `src/main/resources/static/doc.html` et lit la spec generee par springdoc ; les metadonnees (titre, version, description) sont definies dans `src/main/java/com/cour/micromania/config/OpenApiConfig.java`.

## Collection Postman

La collection se trouve dans le depot : [`postman/micromania.postman_collection.json`](postman/micromania.postman_collection.json)

Import : Postman → *Import* → selectionner le fichier. Elle couvre tous les endpoints et definit les variables `baseUrl` (`http://localhost:8080`), `id`, `customerId` et `quantity`, a ajuster si l'API tourne sur un autre port.

## Endpoints

| Ressource | Base | Operations |
|---|---|---|
| Consoles | `/consoles` | CRUD + `GET /{id}/resel-price`, `POST /{id}/sell`, `POST /{id}/buy` |
| Jeux video | `/video-games` | CRUD + `GET /{id}/resel-price`, `POST /{id}/sell`, `POST /{id}/buy` |
| Accessoires | `/accessories` | CRUD + `GET /{id}/resel-price`, `POST /{id}/sell`, `POST /{id}/buy` |
| Clients | `/customers` | CRUD |
| Employes | `/employees` | CRUD |
| Magasins | `/shops` | CRUD |

CRUD = `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}`.

## Structure du projet

```
src/main/java/com/cour/micromania/
├── config/       configuration OpenAPI
├── controller/   endpoints REST
├── dto/          objets de requete et de reponse d'erreur
├── exception/    exceptions metier et handler global
├── model/        entites JPA et enums
├── repository/   acces aux donnees (Spring Data JPA)
└── service/      logique metier
```
