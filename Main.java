
import java.util.ArrayList;

public class Main {

    // Helper method to reset the CPU each time
    public static CPU loadCPU(ArrayList<Process> processes){
        CPU cpu = new CPU();
        for (Process p : processes) {
            cpu.scheduleWaiting(new Process(p));
        }
        return cpu;
    }

    // Main method
    public static void main(String[] args) {
        ArrayList<Process> processes = CSVFile.fileToArray();
        System.out.println("Loaded processes: " + processes.size());


        // Run Round Robin algorithm with time quantum = 2
        CPU cpu2 = loadCPU(processes);
        cpu2.runRoundRobin(2);

        // Run Round Robin algorithm with time quantum = 4
        CPU cpu4 = loadCPU(processes);
        cpu4.runRoundRobin(4);

        // Run Round Robin algorithm with time quantum = 6
        CPU cpu6 = loadCPU(processes);
        cpu6.runRoundRobin(6);

        // Run Round Robin algorithm with time quantum = 8
        CPU cpu8 = loadCPU(processes);
        cpu8.runRoundRobin(8);

        // Run Round Robin algorithm with time quantum = 10
        CPU cpu10 = loadCPU(processes);
        cpu10.runRoundRobin(10);

    }
}