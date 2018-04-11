# Package Delivery System / System Control Software project

## Why

## About this project

This project is part of `CONNEX-AB-Delivery-System`. This repository stores a project `System Control Software` -
software to handle deliveries and manage communication with all trucks.


## Getting Started


Command interface
This section describes commands that trucks will receive and commands that trucks can send to SCS.

[General rule: all trucks must be autonomous as possible. That means, it must execute received task without additional instructions from SCS, except commands provided below.]


## System Control Software (SCS) commands:

"ORDER: PACKAGE; PACKAGE: RED; NUMBER: 1” - Order FT to pick up 1 package in RED color and deliver package to container. 

"ORDER: CONTAINER" - Order CSLT to pick up container and deliver to DT truck. 

“ORDER: DELIVERY” - Order DT to start driving to destination and deliver container. 

“STOP" - halt all movement of truck. 

## Forklift Truck [Package Handling Truck] (FT) pre-condition state:

[Precondition state - FT placed on line, facing in direction of packages]

[Precondition state - Waits for SCS command "ORDER: PACKAGE; PACKAGE: RED”. ]

[Postcondition state - FT stops on line, facing in direction of packages]

## Forklift Truck [Package Handling Truck] (FT)  return commands:

Command that must be send to  SCS: 
"PACKAGE-LOADED: RED" - indicates to SCS that  SCS command "ORDER: PACKAGE; PACKAGE: RED” has been executed and 1 RED colored package has been loaded in container. 

"ERROR" - in any case if task hasn’t been done. 

## Container Sorting and Loading Truck (CSLT) pre-condition state:

[Precondition state - CSLT placed on line, facing in direction of container]

[Precondition state - Waits for SCS command "ORDER: CONTAINER"] 

[Postcondition state - FT stops on line, facing in direction of containers] 

## Container Sorting and Loading Truck (CSLT) return commands:

"CONTAINER-LOADED: 1” - indicates to SCS that SCS command "ORDER: CONTAINER" has been executed  and  1 container is loaded on DT truck . 

"ERROR" - error

## Delivery Truck (DT) pre-condition state:

[Precondition state - DT placed on line, facing in direction of containers]

[Precondition state - Waits for SCS command “ORDER: DELIVERY”] 

[Postcondition state - DT stops on line, facing in direction of containers] 

## Delivery Truck (DT) return  commands:

"LOADING-PLACE" - indicates that DT has arrived in designated container loading place. 

"CONTAINER-DELIVERED" -  indicates to SCS that  SCS command “ORDER: DELIVERY” has been executed and  container has been delivered to location

"ERROR" - error
