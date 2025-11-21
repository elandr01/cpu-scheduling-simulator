public class Process {
    private int PID;
    private int arrival_time;
    private int burst_time;
    private int remaining_time;
    private int completion_time;

    public Process(int PID, int arrival_time, int burst_time) {
        this.PID = PID;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.remaining_time = burst_time;
    }

    public Process(Process process){ // Copy Constructor
        this.PID = process.getPID();
        this.arrival_time = process.getArrivalTime();
        this.burst_time = process.getBurstTime();
        this.remaining_time = burst_time;
    }

    // context switch method

    // Getters and setters
    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getArrivalTime() {
        return this.arrival_time;
    }

    public void setArrivalTime(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getBurstTime() {
        return burst_time;
    }

    public void setBurstTime(int burst_time) {
        this.burst_time = burst_time;
    }

    public int getRemainingTime() {
        return remaining_time;
    }

    public void setRemainingTime(int remaining_time) {
        this.remaining_time = remaining_time;
    }

    public int getCompletionTime() {
        return completion_time;
    }

    public void setCompletionTime(int completion_time) {
        this.completion_time = completion_time;
    }

    public int getTurnaroundTime() {
        return this.getCompletionTime() - this.getArrivalTime();
    }

    public int getWaitingTime() {
        return this.getTurnaroundTime() - this.getBurstTime();
    }
}
