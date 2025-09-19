\# 🏦 Système de Gestion de Comptes Bancaires

\## 📌 Description du projet

Ce projet est une application \*\*console Java 8\*\* permettant de gérer les comptes bancaires d’une banque de manière simple et efficace.  

Elle offre des fonctionnalités de création de comptes (courants et épargne), gestion des versements, retraits, virements, consultation de solde .



L’application est développée en \*\*architecture en couches\*\* afin de respecter les principes \*\*SOLID\*\* et éviter les anti-patterns (God Class, couplage fort, violation de l’encapsulation.).



---

\## ⚙️ Fonctionnalités principales

\- Créer un compte bancaire (courant ou épargne).

\- Effectuer un \*\*versement\*\* dans un compte.

\- Effectuer un \*\*retrait\*\* depuis un compte.

\- Effectuer un \*\*virement\*\* entre comptes.

\- Consulter le \*\*solde\*\* d’un compte.

\- Consulter la \*\*liste des opérations\*\* effectuées sur un compte.

\- Validation stricte des données (montants positifs, format du code compte `CPT-XXXXX`).

\- Gestion des exceptions et saisies utilisateur.

\- Persistance des données en mémoire jusqu’à la fermeture de l’application.



---

\## 🏗️ Structure du projet

Le projet est organisé selon une \*\*architecture en couches\*\* :



\- \*\*Couche présentation (UI)\*\* : Interface console interactive pour interagir avec l’utilisateur.  

\- \*\*Couche métier \*\* : Contient la logique métier (gestion des comptes, opérations, règles de retrait, calcul des intérêts, etc.)aussi définit les classes principales (`Compte`, `CompteCourant`, `CompteEpargne`, `Operation`, `Versement`, `Retrait`).  

\- \*\*Couche utilitaire\*\* : Contient les outils communs (validation, génération de code de compte, formatage, etc.).  

\- \*\*Couche App\*\* :  il contient le class App qui contient le point de début de programme (public static void main (String \[] args) )

 et voila le structures de projet



```text
GestionComptesBancaires/
│
├── src/
|   ├── app/                 # le point du début de programme 
│   │   ├── App.java                 # Point d’entrée de l’application
│   |
│   ├── ui/                           # Couche Présentation (UI / Console)
│   │   ├── MainMenu.java             # Menu interactif console
│   │
│   ├── metier/                       # Couche Métier (Logique )
│   │   │   ├── Compte.java           # Classe abstraite (code, solde, opérations)
│   │   │   ├── CompteCourant.java    # Hérite de Compte (avec découvert)
│   │   │   └── CompteEpargne.java    # Hérite de Compte (avec taux intérêt)
│   │   │   ├── Operation.java        # Classe abstraite ( date, montant)
│   │   │   ├── Versement.java        # Hérite de Operation (source)
│   │   │   └── Retrait.java          # Hérite de Operation (destination)
│   │   │   └── BanqueService.java    # Gestion des comptes (création, recherche, virement) et Gestion des retraits, versements
│   │
│   ├── utils/                         # Couche Utilitaire
│   │   ├── ValidationUtils.java       # Vérification format code compte, montants
│   │
│   ├── out/                         # dossier contient les class 
│   │   ├── app/                     # contient le class de App.java
│   │   ├── ui/                      # contient les class de couch ui
│   │   ├── metier/                  # contient les class de couch metier
│   │   ├── utils/                   # contient les class de couch utils
│   │   ├── Gestion.jar              # le fichier jar du projet
│   │ 
├── README.md                         # Documentation projet
├── diagramme_classes.pdf             # Diagramme UML



---

\## 🛠️ Technologies utilisées

\- \*\*Java 8 (JDK 1.8)\*\*  

\- \*\*Collections (ArrayList, HashMap)\*\* pour la gestion des données en mémoire  

\- \*\*Java Time API\*\* (`LocalDateTime`, `DateTimeFormatter`) pour les dates des opérations  

\- \*\*Git/GitHub\*\* pour le versionnement du code  

\- \*\*Eclipse\*\* pour le développement  



---

\## 📂 Prérequis

Avant d’exécuter le projet, assurez-vous d’avoir :

\- \*\*Java JDK 8\*\* installé et configuré  

\- \*\*Eclipse IDE\*\* (ou autre éditeur compatible)  

\- \*\*Git\*\* pour cloner le dépôt  



---

\## 📂 Captures d'écran

<img width="1439" height="817" alt="Capture d’écran 2025-09-19 20:13:11" 
src="https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201311.png?raw=true" />

![Capture 2025-09-19 20:13:11](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201311.png?raw=true)

![Capture 2025-09-19 20:13:55](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201355.png?raw=true)

![Capture 2025-09-19 20:14:22](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201422.png?raw=true)

![Capture 2025-09-19 20:15:18](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201518.png?raw=true)

![Capture 2025-09-19 20:16:13](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201613.png?raw=true)

![Capture 2025-09-19 20:17:54](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201754.png?raw=true)

![Capture 2025-09-19 20:18:16](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201816.png?raw=true)

![Capture 2025-09-19 20:18:37](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201837.png?raw=true)

![Capture 2025-09-19 20:18:56](https://github.com/Hichamelhmaydi/Syst-me-de-Gestion-des-Comptes-Bancaires/blob/0a4243f34e557901158d24ef1e2938d14d68ec1e/Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20201856.png?raw=true)


