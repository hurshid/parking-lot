# parking-lot command line app
I own a parking lot that can hold up to n cars at any given point in time. Each slot is given a number starting at 1
increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system
that allows my customers to use my parking lot without human intervention.
[more](./PROBLEM_2_PARKING_LOT.md)


## Running the App From the Command Line

To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi)
and put the `mvn` command on your path.
Then, you will need to install a Java 1.8 (or higher) JDK (not JRE!), and make sure you can run `java` from the command line.
Go to project's root folder and run `mvn clean install` and Maven will compile your project,
and put the results it in a jar file in the `target` directory.


Run without arguments - you will see all the commands project supports:
```  
> java -jar target/parking-lot-1.0-SNAPSHOT.jar 
```

Put available commands in the file and set file name as a parameter
```  
> java -jar target/parking-lot-1.0-SNAPSHOT.jar "/temp/command.txt" 
```

