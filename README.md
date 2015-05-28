# Hydrator
Building:
1) Install gradle and add GRADLE_HOME environmental variable
2) Make file named gradle.properties in %userdir%/.gradle/
3) Add the following lines to the file
    - epicbotJarLoc={Location of epicbot.jar}
    - apiJarLoc={Location of API.jar}
    - scriptDir={Location where you want scripts installed}
    - hydratorDir={Location of folder that holds the build.gradle}
4) Open command prompt and navigate to repository location
5) Type "gradle compileScript"
6) Files are now compiled and located in your script directory