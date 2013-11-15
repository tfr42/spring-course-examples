Einleitung
===========

Konventionen
------------
Bitte notieren Sie sich zu Beginn fuer die Uebungen die Pfade zu folgenden Verzeichnissen:
 *	Netzlaufwerk auf dem die Software abgelegt ist: __________________________________ [DOWNLOAD_PATH]Wenn auf dieses Verzeichnis im folgenden verwiesen wird, dann ist dies durch [DOWNLOAD_PATH] gekennzeichnet. Fragen Sie bitte den Dozenten nach dem Ordner auf dem Netzlaufwerk.
 *	Lokales Verzeichnis auf dem Schulungsrechner, wo die Software installiert ist:__________________________________ [INSTALL_PATH](Default: C:/schulung/software).
 *	Und das lokale Verzeichnis in dem sich der Eclipse Workspace befindet:__________________________________ [WORKSPACE_PATH] (Default: C:/schulung/workspace).

Vorbereitung
------------Ueberpruefen Sie, ob alle benoetigten Softwarepakete auf dem Schulungsrechner korrekt und vollstaendig im Verzeichnis [INSTALL_PATH] installiert sind.Sollte eine Softwarekomponente fehlen, müssen Sie diese nachinstallieren. Bevor Sie mit der Installation der Softwarepakte beginnen, prüfen Sie, ob Sie auf dem Schulungsrechner lokale Administrationsrechte haben.Die Installationsdateien für die einzelnen Softwarepakete finden Sie im Verzeichnis [DOWNLOAD_PATH]/Software.Für die Installation von Eclipse Plugins über den Eclipse Update Manager ist eine Internet-Verbindung notwendig. Prüfen Sie die Einstellungen unter den Eclipse Preferences > General > Network Connections.

Entwicklungsumgebung einrichten
-------------------------------Benötigte Software- Java SE 6.0 (JDK 6 Update 45) oder Java SE 7u45- Maven 3.1.1- Installation von Eclipse 3.8.2 (Eclipse IDE for Java EE Developers - Juno ) incl. der Plug-ins:  -	Spring IDE 2.3.0   -	m2e Plugin 1.0.1004  (optional)
- Spring Framework 3.2.5.RELEASE - Apache Tomcat 6.x oder 7.0.x - GlassFish v3.0.1  (optional)- SpringSource Tool Suite (Developer Edition) 3.4.0  (optional)

Übungen
=======
Im Unterverzeichnis 
 /demo 			- sind alle Beispiel
 /lab/student	- Aufgaben für den Teilnehmer
 /lab/trainer	- Lösungen vom Trainer
 
 Prüfen des Workspace:
 mvn validate
 
 Bauen der Übungsumgebung
 mvn install
  
 Erstellen der Dokumentation:
 mvn site 
 mvn site:attach-descriptor site-deploy
 
 Öffnen der Dokumentation im Web-Browser:
 file:///Users/tf/helloWorldSpring/index.html
 