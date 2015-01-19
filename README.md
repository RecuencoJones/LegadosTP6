LegadosTP6
==========

This repository contains two Midi app projects that given a **.mid** file play it and shows on stdout the notes being played on which channel and various observers depending on the branch project.

Branches:

* Develop --> Game Observer, Colour Observer, Synth Observer
* Tryhard --> Multicolour Observer
* Master --> General purpose

Requirements
---------

* Java JDK `1.7.0_45` or higher with its `PATH` and `JAVA_HOME` variables 
* [Gradle](https://www.gradle.org/) `2.2.1` or higher with its `PATH` and `GRADLE_HOME` variables

Procedure
---------

Execute `gradle run` from each branch root directory to start the app.

There are up to 15 midi samples provided in the [resources](src/main/resources) folder of each branch.
Samples were taken with the export Midi function of guitar pro5.2

We recommend to try files **12** and **14** with **develop** and **13**, **14** and **15** with **tryhard**.

Sample list
---------
- Digimon - Butterfly
- Pokemon - Wild Battle
- Children of Bodom - Bed of razors
- Dope - Die Motherfucker Die
- Nightwish - Wishmaster
- Pokemon - Route 1
- Darude - Sandstorm
- Beethoven - Moonlight Sonata
- Amon Amarth - Pursuit of Vikings
- K-On - Cagayake girls
- Dragonforce - Through the fire and flames
- GirlDeMo - Little Braver
- Galneryus - Destiny
- Battlefield - Main theme (metal version, one channel only)

*No mapping to filename is given for you to discover ;)

Youtube samples
---------

- [Pursuit of Vikings](https://www.youtube.com/watch?v=dhHmgAK-Vfc)
- [Moonlight Sonata](https://www.youtube.com/watch?v=gBWnEYgscio)
- [Through the fire and flames - Develop](https://www.youtube.com/watch?v=iI-vYcQkYPA)
- [Through the fire and flames - Tryhard](https://www.youtube.com/watch?v=4VeLLtsRPYo)
