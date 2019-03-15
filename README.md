#Parking Lot Apps
created by: Nurmalita Sari

## Setup
No need to setup dependency if have installed maven and jdk 1.8

### Running from Maven
1. Download maven
2. Unzip project
3. In folder of project: `mvn clean install`
4. Then goto target project and run the jar file:  `java -jar parkinglot-v1.jar`


### Running from jar file
1. Unzip project
2. Go to target folder on the project with cmd
3. run jar file: `java -jar parkinglot-v1.jar`

## Usage
1. If running program success system will show:

```
------ PARKING LOT -------
1. Input Manual
2. Read From TXT
3. Exit
Please choose: 
```

 choose use manual input or from file_input.txt
2. If manual, the command same as command example in pdf file
3. If input from txt, then system will read from file_input.txt (src/main/resource)