Einleitung
===========

Konventionen
------------

Bitte notieren Sie sich zu Beginn fuer die Übungen die Pfade zu folgenden Verzeichnissen:
 *	Netzlaufwerk auf dem die Software abgelegt ist:
 
 [DOWNLOAD_PATH]: __________________________________ 
 Wenn auf dieses Verzeichnis im folgenden verwiesen wird, dann ist dies durch [DOWNLOAD_PATH] gekennzeichnet. Fragen Sie bitte den Dozenten nach dem Ordner auf dem Netzlaufwerk.
 
 *	Lokales Verzeichnis auf dem Schulungsrechner, in dem die Software-Pakete installiert sind:

 [INSTALL_PATH]: __________________________________  (Default: C:/schulung/software)
 
 *	Und das lokale Verzeichnis in dem sich der Eclipse Workspace befindet:

 [WORKSPACE_PATH]: __________________________________ (Default: C:/schulung/workspace)  


Vorbereitung
------------
Überpruefen Sie, ob alle benoetigten Softwarepakete auf dem Schulungsrechner korrekt und vollstaendig im Verzeichnis [INSTALL_PATH] installiert sind.
Sollte eine Softwarekomponente fehlen, müssen Sie diese nachinstallieren. Bevor Sie mit der Installation der Softwarepakte beginnen, prüfen Sie, ob Sie auf dem Schulungsrechner lokale Administrationsrechte haben.
Die Installationsdateien für die einzelnen Softwarepakete finden Sie im Verzeichnis [DOWNLOAD_PATH]/Software.
Für die Installation von Eclipse Plugins über den Eclipse Update Manager ist eine Internet-Verbindung notwendig. Prüfen Sie die Einstellungen unter den Eclipse Preferences > General > Network Connections.

Entwicklungsumgebung einrichten
-------------------------------
Benötigte Software
- Java SE 6.0 (JDK 6 Update 45) oder Java SE 7u45
- Maven 3.2.1
- Installation von Eclipse 3.8.2 (Eclipse IDE for Java EE Developers - Juno ) oder höher incl. der Plug-ins:
  -	Spring IDE 3.5.0 
  -	m2e Plugin 1.4.0
  oder 
  - SpringSource Tool Suite (Developer Edition) 3.5.0  
  oder 
  JetBrains IntelliJIDEA 13.x
- Apache Tomcat 7.x oder 8.x 
- GlassFish v4.0  (optional)
- Apache ActiveMQ 5.9.0 (optional)

Übungen
=======
Im Unterverzeichnis 
 /demo 			- sind kleine Beispiel-Anwendungen auf Basis von Spring-basierten Projekten wie z.B. SpringBatch
 /lab/student	- der Workspace mit den Aufgaben für den Teilnehmer
 /lab/trainer	- der Workspace mit den Lösungen vom Trainer
 
Wichtige Maven Befehle:
----------------------- 

 Prüfen des Workspace:
 mvn validate
 
 Bauen der Übungsumgebung:
 mvn install
 mit den Demo-Projekten helloWorldRoo und helloWorldSpringBoot müssen die Profile aktiviert werden:
 mvn -PSpringRoo,SpringBoot install
  
 Erstellen der Dokumentation:
 mvn site 
 mvn site:attach-descriptor site-deploy
 
 Öffnen der Dokumentation im Web-Browser:
 file:///$USER_HOME/Sites/helloWorldSpring/index.html
 
 Maven mehr Speicher zuweisen mit:
 Windows:
 set MAVEN_OPTS="-Xmx1g -XX:MaxPermSize=512m"
 
 UNIX:
 export MAVEN_OPTS="-Xmx1g -XX:MaxPermSize=512m"
 
 Projekt-Template erzeugen und nutzen:
 cd lab/student/helloWorldSpring/
 mvn archetype:create-from-project
 cd target/generated-sources/archetype
 mvn install
 cd lab/student/
 mvn archetype:generate -DgroupId=net.gfu.seminar.spring.helloworld.student -DartifactId=helloWorldSpringDI01 -Dversion=1.0.0-SNAPSHOT -DpackageName=net.gfu.seminar.spring.helloworld -DarchetypeGroupId=net.gfu.seminar.spring.helloworld -DarchetypeArtifactId=helloWorldSpring-archetype -DarchetypeVersion=1.0.0-SNAPSHOT
 
 Projekt aktualisieren:
 Abhängigkeiten aktualisieren:
 mvn versions:display-dependency-updates
 
 Plugins aktualisieren:
 mvn versions:display-plugin-updates
 mvn versions:use-latest-versions
 
Starten der HSQLDB:
-------------------
 Im Unterverzeichnis lab/src/main/scripts befinden sich  Skripte für MS Windows und UNIX/Linux um die HSQLDB im Server-Modus zu starten:
 Windows:
  lab/src/main/script/startDB.cmd

 UNIX:
  lab/src/main/script/startDB.sh
 
 Starten der HSQLDB Database Manager Anwendung:
 Windows:
  lab/src/main/script/startDBManager.cmd

 UNIX:
  lab/src/main/script/startDBManager.sh