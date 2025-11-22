# Round Robin CPU Scheduling Simulator
## About the Project
This program is designed to simulate how a CPU runs processes using Round Robin, a preemptive scheduling algorithm that runs each process for a fixed amount of time (time quantum) before switching to the next process.

## How To Run the Simulation
* Clone the repository and open it with a Java IDE (Intellij is recommended).

* Check the root directory of the project for a file called processes.csv. 
  * If processes.csv exists, ignore this step.
* If processes.csv does not exist:
  * Navigate to the CSVFile class (CSVFile.java).
  * Remove the comment symbol (//) from the start of line 62 of the CSVFile class.
  * Run the main method (CSVFile.main()) to create the CSV file. Do not run the main method more than once.

* Navigate to the Main class and run the main method to start the simulation.


## Interpreting the Output
After all processes have finished executing, the program will print out four metrics describing the system’s performance and averages:
* CPU Utilization: the percentage of a CPU's time being used to perform tasks, represented as a decimal value between 0 and 1 (0%-100%)
* Throughput: the number of processes that complete execution per time unit.
* Average wait time: The average amount of time processes wait in the ready queue
* Average turnaround time: the average amount of time it takes to execute a process
