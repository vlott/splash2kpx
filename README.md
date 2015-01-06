Welcome to Splash2Kpx

This utility is licensed under the Apache 2.0 license and will simply

Prerequisites:

    - Export SplashID database to .csv format
    - Maven 3.x installed
    - Java 7 installed

Build the utility:

    % mvn clean compile assembly:single

Run the utility:

    % java -jar target/splash2kpx-jar-with-dependencies.jar <Full path to your SplashID .csv file>

Assumptions:

    - Icon 0 used for all KeePassX entries
    - Icon 1 used for
    - SplashID Address, Code and Comment fields combined into KeePassX comment field

Links:

-


