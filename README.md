# Cats OnlineBank

## Description
Cats OnlineBank est une application Android développée en Kotlin, utilisant Jetpack Compose pour l'interface utilisateur et Koin pour l'injection de dépendances. Ce projet est structuré en plusieurs modules pour une meilleure maintenabilité et suit les principes de la Clean Architecture.

## Fonctionnalités
- **Architecture modulaire** : séparation des responsabilités entre les modules.
- **Jetpack Compose** : pour une interface utilisateur moderne et réactive.
- **Koin** : pour la gestion des dépendances.
- **Kotlin Flow** : pour la gestion des flux de données asynchrones.
- **Build-logic et conventions Gradle** : pour une gestion centralisée et standardisée de la configuration du projet.
- **Tests** : support des tests unitaires et d'instrumentation.

## Migration vers Kotlin Multiplatform (KMP)
Le projet a été migré vers Kotlin Multiplatform en utilisant Compose Multiplatform. Pour voir cette version, basculez sur la branche [`migration/kmp`](https://github.com/Djvmil/online-bank/tree/migration/kmp).

## Prérequis
- **Android Studio** : version `2024.3.1 Patch 1` ou supérieure.
- **JDK** : version 17 ou supérieure.
- **Gradle** : version 8.9.2.

## Installation
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/Djvmil/online-bank.git
   cd online-bank