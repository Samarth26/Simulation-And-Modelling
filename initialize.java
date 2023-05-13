import java.util.*;
/**
 * Write a description of class initialize here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class initialize
{
    // instance variables - replace the example below with your own
    private int clock;
    private int[] cellChannelsFree;
    //Change to event object later
    private ArrayList<event> FEL;
    //Statistical Counters
    private ArrayList<Integer> locationCallGenerated;
    private double interarrivalCall;
    private double callDuration;
    private double carSpeed;
    private int numBlocked;
    private int numDropped;
    private int numTerminated;
    private int numCallsInitiated;
    private ArrayList<String> values;
    private double interarrivallTime;
    private int baseStation;
    private double pos;
    private double directionNum;
    private double velocity;
    private double firstCallDuration;
    private double handoverTime;
    private String direction;
    /**
     * Constructor for objects of class initialize
     */
    public initialize()
    {
        clock = 0;
        cellChannelsFree = new int[20];
        Arrays.fill(cellChannelsFree, 10);
        FEL = new ArrayList<event>();
        locationCallGenerated = new ArrayList<Integer>();
        interarrivalCall = 0.0;
        callDuration = 0.0;
        carSpeed = 0.0;
        numBlocked = 0;
        numDropped = 0;
        numTerminated = 0;
        numCallsInitiated = 0;
        values = new ArrayList<String>();
    }
        public void createInitiationEvent(){
        /**Use the input modeling to get the different values
        *To construct an event we need the following values
        *int pos, int speed, String direction, int baseStation, double CallDuration, String typeOfEvent
        *We get the following below from input modelling
        *pos is randomly generated
        *Arrival no, Arrival time, Base Station, Call Duration, Velocity
        */
        // First Arrival Time
        Random r0 = new Random();
        Random r_0 = new Random();
        this.interarrivallTime = Math.log(1-r0.nextDouble())/(1/-1.369680); //1.369680
        this.baseStation = r_0.nextInt(20);
        this.pos = Math.random()*(2);
        this.pos = (this.baseStation*2) + (this.pos);
        this.directionNum = Math.random();
        Random r = new Random();
        Random r1 = new Random();
        this.velocity = (r.nextGaussian()*9.02)+120.07;
        this.velocity = velocity/(60*60);
        double exp_inv = Math.log(1-r1.nextDouble());
        double b = -109.84;
        this.firstCallDuration = ((exp_inv*b)/1.1) + 10.003952;
        this.direction = "";
        if (this.directionNum <= 0.5)
            this.direction = "Right";
        else this.direction = "Left";
    }
    public ArrayList<event> getFEL(){
        return FEL;
    }
    public int[] getCellChannelsFree(){
        return cellChannelsFree;
    }
    public double getInterarrivallTime(){
        return interarrivallTime;
    }
    public int getBaseStation(){
        return baseStation;
    }
    public double getPos(){
        return pos;
    }
    public double getDirectionNum(){
        return directionNum;
    }
    public double getVelocity(){
        return velocity;
    }
    public double getFirstCallDuration(){
        return firstCallDuration;
    }
    public String getDirection(){
        return direction;
    }
    public double getTime(){
        return clock;
    }
    public double getHandoverTime(){
        return handoverTime;
    }
    public double getNumDropped(){
        return numDropped;
    }
    public double getNumBlocked(){
        return numBlocked;
    }
    public double getNumCalls(){
        return numCallsInitiated;
    }
    public void setCellChannelsFree(int station){
        //System.out.println("Station: " + station + " num: " + cellChannelsFree[station]);
        cellChannelsFree[station]-=1;
    }
    public void releaseCellChannelsFree(int station){
        cellChannelsFree[station]+=1;
    }
    public void addFEL(event e){
        for(int x = 0; x < FEL.size(); x++){
            if(e.getEventTime() < FEL.get(x).getEventTime()){
                FEL.add(x, e);
                return;
            }
        }
        FEL.add(e);
        return;
    }
    public event removeFEL(int i){
        event e = FEL.remove(i);
        return e;
    }
    public void incrementNumCallsInitiated(){
        numCallsInitiated+=1;
    }
    public void incrementInterArrivalCall(double interarrivalTime){
        interarrivalCall += interarrivalTime;
    }
    public void incrementNumBlocked(){
        numBlocked+=1;
    }
    public void incrementNumDropped(){
        numDropped+=1;
    }
    public void incrementNumTerminated(){
        numTerminated+=1;
    }
    public void storeLocationCallGenerated(int location){
        locationCallGenerated.add(location+1);
    }
    public void setHandoverTime(double time){
        handoverTime = time;
    }
    public void setClock(double time){
        clock+=time;
    }
}
