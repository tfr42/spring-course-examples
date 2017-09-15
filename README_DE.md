Einleitung
===========

Konventionen
------------

Bitte notieren Sie sich zu Beginn fuer die Übungen die Pfade zu folgenden Verzeichnissen:
 *	Netzlaufwerk auf dem die Software abgelegt ist:
 
__________________________________ *DOWNLOAD_PATH*
Wenn auf dieses Verzeichnis im folgenden verwiesen wird, dann ist dies durch {DOWNLOAD_PATH} gekennzeichnet. Fragen Sie bitte den Dozenten nach dem Ordner auf dem Netzlaufwerk.
 *	Lokales Verzeichnis auf dem Schulungsrechner, wo die Software installiert ist:

__________________________________ *INSTALL_PATH*
(Default: C:/schulung/software).
 *	Und das lokale Verzeichnis in dem sich der Eclipse Workspace befindet:

__________________________________ *WORKSPACE_PATH* 
(Default: C:/schulung/workspace).

Vorbereitung
------------
Überpruefen Sie, ob alle benoetigten Softwarepakete auf dem Schulungsrechner korrekt und vollstaendig im Verzeichnis {INSTALL_PATH}` installiert sind.
Sollte eine Softwarekomponente fehlen, müssen Sie diese nachinstallieren. Bevor Sie mit der Installation der Softwarepakte beginnen, prüfen Sie, ob Sie auf dem Schulungsrechner lokale Administrationsrechte haben.
Die Installationsdateien für die einzelnen Softwarepakete finden Sie im Verzeichnis `{DOWNLOAD_PATH}/Software`.
Für die Installation von Eclipse Plugins über den Eclipse Update Manager ist eine Internet-Verbindung notwendig. Prüfen Sie die Einstellungen unter den Eclipse Preferences > General > Network Connections.

Entwicklungsumgebung einrichten
-------------------------------
Benötigte Software
- Java SE 8 (Oracle oder OpenJDK 8)
- Maven 3.0.5+
- Installation von Eclipse 4.6+ (Eclipse IDE for Java EE Developers - Neon ) incl. der Plug-ins:
  -	Spring IDE 3.8+
  -	m2e Plugin 1.7+
  oder 
  - SpringSource Tool Suite (Developer Edition) 3.8+
  oder 
  - JetBrains IntelliJIDEA 14.1
- Spring Framework 4.2+
- Apache Tomcat 7.0+ oder 8.0+ 
- GlassFish 4.0+ (optional)
- Apache ActiveMQ 5.9.0+ (optional)

Übungen
=======
Im Unterverzeichnis 
 `/demo` 		- sind kleine Beispiel-Anwendungen auf Basis von Spring-basierten Projekten wie SpringBatch
 `/lab/student`	- der Workspace mit den Beispielanwendung für die Bearbeitung durch den Teilnehmer
 `/lab/trainer`	- der Workspace mit den Lösungen vom Trainer
 
 Prüfen des Workspace:
 
    mvn validate
 
 Bauen der Übungsumgebung:
 
    mvn install
 
 für das Demo-Projekt helloWorldSpringBoot muß ein Profil aktiviert werden:
    
    mvn -PSpringBoot install
  
 Erstellen der Dokumentation:
 
    mvn site 
    mvn site:attach-descriptor site-deploy
 
 Öffnen der Dokumentation im Web-Browser:
 `file:///$USER_HOME/Sites/helloWorldSpring/index.html`
 
 Maven mehr Speicher zuweisen mit:
 Windows:
 
    set MAVEN_OPTS="-Xmx1g -XX:MaxPermSize=512m"
 
 UNIX/Linux/macOS:
 
    export MAVEN_OPTS="-Xmx1g -XX:MaxPermSize=512m"
  
 
Starten der HSQLDB:
 Im Unterverzeichnis lab/src/main/scripts befinden sich Skripte für MS Windows und UNIX/Linux um die HSQLDB im Server-Modus zu starten:
 Windows:
  
    lab/src/main/script/startDB.cmd

 UNIX/Linux/macOS:
 
    lab/src/main/script/startDB.sh
 
 Starten der HSQLDB Database Manager Anwendung:
 Windows:
  
    lab/src/main/script/startDBManager.cmd

 UNIX/Linux/macOS:
  
    lab/src/main/script/startDBManager.sh

Projekt-Template erzeugen und nutzen:

    cd lab/student/helloWorldSpring/
    mvn archetype:create-from-project
    cd target/generated-sources/archetype
    mvn install
    cd lab/student/
    mvn archetype:generate -DgroupId=net.gfu.seminar.spring.helloworld.student -DartifactId=helloWorldSpringWeb -Dversion=0.0.1-SNAPSHOT -DpackageName=net.gfu.seminar.spring.helloworld.web -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.0mvn archetype:generate -DgroupId=net.gfu.seminar.spring.helloworld.student -DartifactId=helloWorldSpringDI01 -Dversion=1.0.0-SNAPSHOT -DpackageName=net.gfu.seminar.spring.helloworld -DarchetypeGroupId=net.gfu.seminar.spring.helloworld -DarchetypeArtifactId=helloWorldSpring-archetype -DarchetypeVersion=1.0.0-SNAPSHOT

Zu den Übungen MVC01 und MVC02 erstellen eines neuen Maven-Moduls:
 
    cd lab/student
    mvn archetype:generate -DgroupId=net.gfu.seminar.spring.helloworld.student -DartifactId=helloWorldSpringWeb -Dversion=0.0.1-SNAPSHOT -DpackageName=net.gfu.seminar.spring.helloworld.web -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.0
    cd helloWorldSpringWeb
    mvn install
    mvn jetty:run

oder mit Apache Tomcat statt `jetty:run`:
    
    mvn tomcat:run