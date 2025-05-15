# Cats OnlineBank

## Description
Cats OnlineBank est une application multiplateforme développée en Kotlin Multiplatform Mobile (KMM). Elle permet de gérer des comptes bancaires avec une interface utilisateur moderne basée sur Jetpack Compose.

## Fonctionnalités
- **Gestion des comptes** : Affichage des soldes et des transactions.
- **Interface utilisateur** : Utilisation de Jetpack Compose pour une expérience fluide et réactive.
- **Multiplateforme** : Support des plateformes Android et iOS grâce à Kotlin Multiplatform.
- **Architecture modulaire** : Organisation en modules pour une meilleure maintenabilité.

## Prérequis
- **Android Studio** : Version 2024.3.1 Patch 1 (Meerkat) ou supérieure.
- **Kotlin** : Version 2.1.20.
- **Gradle** : Version 8.9.2.

## Liste des tâches restantes

- [x] Migration des cibles iOS (`iosX64`, `iosArm64`, `iosSimulatorArm64`)
- [x] Ajout de la version pour Cocoapods dans le fichier `build.gradle.kts`
- [x] Résolution des problèmes liés à la tâche `:core:common:kspKotlinIosArm6`
- [ ] Fix all tests Tests
- [x] Tests finaux sur le plateforme Android
- [x] Tests finaux sur le plateforme iOS

## Installation
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/Djvmil/online-bank.git
   cd online-bank