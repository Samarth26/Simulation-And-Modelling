import java.util.*;
/**
 * Write a description of class initiation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class initiation
{
    // instance variables - replace the example below with your own
    private double time;
    private double speed;
    private int station;
    private double pos;
    private double duration;
    private String direction;
    /**
     * Constructor for objects of class initiation
     */
    public initiation()
    {
        // initialise instance variables
        time = 0;
    }
    public initiation(double time, double speed, int station, double pos, double duration, String direction){
        this.time = time; // This is the next call initiation time and the next arrival time
        this.speed = speed;
        this.station = station;
        this.pos = pos;
        this.duration = duration;
        this.direction = direction;
        //need a get FEL Function
    }
    public void initiationEvent(initialize obj, int FCA){
        //if(station == 0)
        //System.out.println(obj.getCellChannelsFree()[station]);
        if((FCA == 10 && obj.getCellChannelsFree()[station] == 0) || (FCA == 9 && obj.getCellChannelsFree()[station] <= 1)){

            //Create Termination Event
            event e1 = new event(pos, speed, direction, station, duration, "Blocked", time, time);
            //Add 1 to numBlocked and numCallsInitiated
            obj.incrementNumCallsInitiated();
            //Store locationCallGenerated and interarrivalCall
            obj.incrementInterArrivalCall(time);
            obj.storeLocationCallGenerated(station);
            obj.addFEL(e1);
        }
        else{
            //Calculate Handover Time
            double remainingdist, position, handover_time = 0;
            int nextStation = 0;
            if(direction.equals("Right")){
                    nextStation = station+1;
                    remainingdist = (nextStation*2) - pos;
                    position = pos + remainingdist;
                    handover_time = time + (remainingdist/speed);
                }
                else{
                    nextStation = station-1;
                    remainingdist = pos - (nextStation*2);
                    position = pos - remainingdist;
                    handover_time = time + (remainingdist/speed);
                    }
            obj.setHandoverTime(handover_time);
            event e1 = new event(pos, speed, direction, station, duration,"Handover", handover_time, time); //Create the handover event
            //Add 1 to numCallsInitated 
            obj.incrementNumCallsInitiated(); 
            //Subtract one from cellChannelsFree
            obj.setCellChannelsFree(station);
            //if(station == 0)
            //System.out.println(obj.getCellChannelsFree()[station]);
            //Store interarrivalCall, location call
            obj.incrementInterArrivalCall(time);
            obj.storeLocationCallGenerated(station);
            obj.addFEL(e1);
        }
        obj.setClock(time);
        return;
    }
}
