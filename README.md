Welcome to Splash2Kpx

This utility is licensed under the Apache 2.0 license. It has been used to convert the author's SplashID database (exported to .csv format to KeePassX format). It worked well for this, but use at your own risk. The author makes no claims of any other kind.

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


