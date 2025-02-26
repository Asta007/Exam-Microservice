# MicroService

## Description
Ce projet Spring Boot, "Exam2025", est une application de gestion d'étudiants utilisant Spring Data JPA et H2 pour les tests. Il permet de gérer les étudiants (création, mise à jour, suppression, récupération) via des DTO mappés avec MapStruct. Des exceptions personnalisées gèrent les erreurs, et la documentation de l'API est générée avec Swagger. Des tests unitaires sont réalisés avec Mockito pour assurer la fiabilité du code, démontrant une approche moderne et robuste pour une application d'entreprise.

---

## Technologies utilisées

- **Base H2**
- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **Spring Web**
- **Lombok**
- **Mapstruct**
- **Mockito**
- **OpenApi**

---

## Concepts Implémentés

- **CRUD Student**
- **Mapper, DTO**
- **Gestion des Exception [ NotFound, AlreadyExist ]**
- **Test Unitaire Junit**
- **Swagger**

###  Couverture des Teste
![test_list](data/test_list.png)

![test_coverage](data/test_coverage.png)

---

##  Installation et exécution

###  Configuration de la base de données
Nous avons utilisé une base de donnée embarquée H2 de type fichier
> data/exam2025.mv.db

## 🛠️ Open Api

L'application expose une API REST permettant de gérer une entité (ex. : `Contact`).
> **[ Acceder a swagger](http://localhost:8080/swagger-ui/index.html)**

### StudentEntity
```json
{
  "firstName": "Alph",
  "lastName": "Sy",
  "emailPro": "sy@malick",
  "emailPerso": "alpha@sy",
  "phoneNumber": "98659876",
  "address": "Hamo",
  "archive": true,
  "registrationNu": "LKJHJG7668"
}
```
---

# Elastic Stack Docker Compose Setup

Ce projet configure un environnement Elastic Stack avec tous les outils nécessaires pour collecter, analyser et visualiser des données de logs et des métriques système. Utilisez Kibana pour gérer et visualiser vos données Elasticsearch.


## Description des Services

Le fichier `docker-compose.yml` contient plusieurs services pour mettre en place l'Elastic Stack.

### 1. **Elasticsearch** (`es01`)

Ce service est le cœur du stack Elastic. Il gère les données et les index de recherche.

- **Ports exposés** : 9200
- **Configuration** : Elasticsearch est configuré en mode "single-node".
- **Volume** : Les données sont stockées dans un volume persistant `esdata01`.

### 2. **Kibana**

Kibana est l'interface graphique pour interagir avec Elasticsearch. Elle permet de visualiser les données et logs.

- **Ports exposés** : 5601
- **Configuration** : Connecté à Elasticsearch via `es01`.
- **Volume** : Les données de Kibana sont stockées dans un volume persistant `kibanadata`.

### 3. **Logstash**

Logstash est utilisé pour ingérer, transformer et envoyer des logs vers Elasticsearch.

- **Configuration** : Utilise un fichier de configuration `logstash.conf` pour définir les pipelines de traitement.
- **Volume** : Les données de Logstash sont stockées dans un volume persistant `logstashdata01`.

### 4. **Filebeat**

Filebeat est utilisé pour envoyer les logs des fichiers vers Logstash.

- **Volume** : Monté avec le répertoire local contenant la configuration de Filebeat et les données de logs.

### 5. **Metricbeat**

Metricbeat collecte des métriques de performance du système et des containers Docker et les envoie vers Elasticsearch.

## Démarrage du Projet

Pour démarrer l'environnement Elastic Stack, suivez ces étapes :

1. Clonez ce dépôt (ou téléchargez les fichiers nécessaires).
2. Créez un fichier `.env` et définissez les variables d'environnement requises (voir ci-dessus).
3. Lancer les services avec Docker Compose :

```bash
docker-compose up -d
```

Cela va télécharger les images Docker nécessaires et démarrer les services en arrière-plan.

### Vérification de l'état des services

Vous pouvez vérifier l'état des services via Docker Compose :

```bash
docker-compose ps
```

### Accès à Kibana

Une fois les services démarrés, vous pouvez accéder à Kibana à l'adresse suivante :

[http://localhost:5601](http://localhost:5601)

Utilisez les identifiants suivants pour vous connecter :

- **Nom d'utilisateur** : `kibana_system`
- **Mot de passe** : Définie dans `.env` sous `KIBANA_PASSWORD`

### Accès à Elasticsearch

Elasticsearch sera accessible via HTTP à l'adresse suivante :

[http://localhost:9200](http://localhost:9200)

Les logs seront envoyés à Elasticsearch, et vous pourrez les visualiser via Kibana.