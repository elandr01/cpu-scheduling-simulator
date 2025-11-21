import java.util.*;

public class CPU {
    // initialize queues
    private final PriorityQueue<Process> waitingQueue;
    private final Queue<Process> readyQueue;

    // constructor
    public CPU(){
        waitingQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getArrivalTime));
        readyQueue = new ArrayDeque<>();
    }

    public void scheduleWaiting(Process process){
        waitingQueue.add(process);
    }

    public void scheduleReady(int current_time){
        while (!waitingQueue.isEmpty() && waitingQueue.peek().getArrivalTime() <= current_time) {
            Process arrived = waitingQueue.poll();
            readyQueue.offer(arrived);
            assert arrived != null;
            System.out.println("Process " + arrived.getPID() + " added to ready queue at time " + current_time);
        }
    }

    public void runRoundRobin(int time_quantum){
        int current_time = 0;
        final int context_switch_time = 1;
        double num_context_switches = 0;
        int num_processes = 0;
        List<Integer> waitTimes = new ArrayList<>();
        List<Integer> turnaroundTimes = new ArrayList<>();

        System.out.println("\n\n-----Running Round Robin Simulator with Time Quantum " + time_quantum + "-----");

        while(!waitingQueue.isEmpty() || !readyQueue.isEmpty()){ // run the simulator while at least one queue is not empty

            scheduleReady(current_time); // add processes to the ready queue

            if (readyQueue.isEmpty()) { // if ready queue is empty, increment time
                current_time++;
                continue;
            }

            Process currentProcess = readyQueue.peek(); // define current process as the process at the front of the ready queue

            // If current process burst time is smaller than or equal to time quantum, run the process without preemption
            if (currentProcess.getRemainingTime() <= time_quantum){
                current_time += currentProcess.getRemainingTime();
                System.out.println("Process " + currentProcess.getPID() + " finished running at time " + current_time);

                currentProcess.setCompletionTime(current_time);
                num_processes += 1;
                waitTimes.add(currentProcess.getWaitingTime());
                turnaroundTimes.add(currentProcess.getTurnaroundTime());

                readyQueue.poll();

                continue;

            // otherwise run the process for the time quantum, then add the process with the remaining time to the end of the ready queue
            } else {
                current_time += time_quantum;
                System.out.println("Time " + (current_time - time_quantum) + " → " + current_time
                        + ": Process " + currentProcess.getPID() + " ran for " + time_quantum + " units.");
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - time_quantum);

                scheduleReady(current_time);

                readyQueue.poll();
                readyQueue.offer(currentProcess);
                num_context_switches += 1;
            }

        }

        System.out.println("Round Robin Simulator finished execution\n");

        // Calculate and print out CPU utilization
        double total_execution_time = current_time;
        double cpu_utilization = 1 - ((context_switch_time * num_context_switches) / total_execution_time);
        System.out.println("CPU Utilization: " + cpu_utilization);

        // Calculate and print out throughput
        double throughput = num_processes / total_execution_time;
        System.out.println("Throughput: " + throughput);

        // Calculate and print out average waiting time
        double avg_wait_time;
        double total_wait_time = 0;
        for (Integer waitTime : waitTimes) {
            total_wait_time += waitTime;
        }
        avg_wait_time = total_wait_time / num_processes;
        System.out.println("Average wait time: " + avg_wait_time + " units");

        // Calculate and print out average turnaround time
        double avg_turnaround_time;
        double total_turnaround_time = 0;
        for (Integer turnaroundTime : turnaroundTimes) {
            total_turnaround_time += turnaroundTime;
        }
        avg_turnaround_time = total_turnaround_time / num_processes;
        System.out.println("Average turnaround time: " + avg_turnaround_time + " units");
    }

}
