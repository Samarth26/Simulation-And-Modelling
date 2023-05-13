import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;

/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) throws java.io.IOException {
        double warmup_time = 0;
        int FCA = 10;
        for(int a = 0; a<2; a++){
            if(a == 1) FCA = 9;
            double totalNumCallsDropped = 0;
            double totalNumCallsBlocked = 0;
            double totalNumCalls = 0;
            /*FileWriter writer = new FileWriter("percentCallsDropped.csv");
            FileWriter writer2 = new FileWriter("percentCallsBlocked.csv");
            FileWriter writer3 = new FileWriter("clock.csv");
            FileWriter writerFCA = new FileWriter("percentCallsDroppedFCA.csv");
            FileWriter writerFCA2 = new FileWriter("percentCallsBlockedFCA.csv");
            FileWriter writerFCA3 = new FileWriter("clockFCA.csv");*/
            for(int x = 0; x < 40; x++){
                double clock = 0;
                initialize obj = new initialize();
                obj.createInitiationEvent();
                event e1 = new event(obj.getPos(), obj.getVelocity(), obj.getDirection(), obj.getBaseStation(), obj.getFirstCallDuration(),"Initiate", obj.getTime() + obj.getInterarrivallTime(), obj.getInterarrivallTime());
                obj.addFEL(e1);
                int i = 0;
                double warmupDropped = 0;
                double warmupBlocked = 0;
                double warmupCalls = 0;
                double pos = 0;
                double callDuration = 0;
                int baseStation = 0;
                double speed = 0;
                double arrivalTime = 0;
                while(clock<=50000){
                    if(obj.getFEL().size() == 0){
                        obj.createInitiationEvent();
                        event e3 = new event(obj.getPos(), obj.getVelocity(), obj.getDirection(), obj.getBaseStation(), obj.getFirstCallDuration(),"Initiate", clock + obj.getInterarrivallTime(), clock + obj.getInterarrivallTime());
                        arrivalTime+= clock;                
                        obj.addFEL(e3);
                    }
                    event removedEvent = obj.removeFEL(0);
                    clock = removedEvent.getEventTime();
                    //System.out.println(removedEvent.toString());

                    if(removedEvent.getTypeOfEvent().equals("Initiate")){
                        i++;
                        //Call Initation event
                        initiation i1 = new initiation(removedEvent.getEventTime(), removedEvent.getSpeed(), removedEvent.getBaseStation(), removedEvent.getPos(), removedEvent.getCallDuration(), removedEvent.getDirection());
                        i1.initiationEvent(obj, FCA);
                        //Create Initiation Event
                        pos+= removedEvent.getPos();
                        callDuration+= removedEvent.getCallDuration();
                        baseStation+= removedEvent.getBaseStation();
                        speed += removedEvent.getSpeed();
                        obj.createInitiationEvent();
                        //System.out.println(clock);
                        arrivalTime+= clock;                
                        event e2 = new event(obj.getPos(), obj.getVelocity(), obj.getDirection(), obj.getBaseStation(), obj.getFirstCallDuration(),"Initiate", clock + obj.getInterarrivallTime(), clock + obj.getInterarrivallTime());
                        obj.addFEL(e2);
                    }
                    else if(removedEvent.getTypeOfEvent().equals("Terminate")|| removedEvent.getTypeOfEvent().equals("Blocked")|| removedEvent.getTypeOfEvent().equals("Dropped")){
                        //Call Termination Event
                        termination t1 = new termination(removedEvent.getEventTime(), removedEvent.getBaseStation());
                        t1.terminationEvent(obj, removedEvent);
                    }
                    else{
                        //Call Handover Event
                        handover h1 = new handover(removedEvent.getEventTime(), removedEvent.getSpeed(), removedEvent.getBaseStation(), removedEvent.getCallDuration(), removedEvent.getDirection());
                        h1.handoverEvent(removedEvent, obj);
                    }
                    if(clock <= 35000){
                    warmupDropped = obj.getNumDropped();
                    warmupBlocked = obj.getNumBlocked();
                    warmupCalls = obj.getNumCalls();
                    }
                    /*writer.append(String.valueOf((obj.getNumDropped() - warmupCalls)/(obj.getNumCalls() - warmupCalls)));
                    writer.append("\n");
                    writer2.append(String.valueOf((obj.getNumBlocked() - warmupBlocked)/(obj.getNumCalls() - warmupCalls)));
                    writer2.append("\n");
                    writer3.append(String.valueOf(clock));
                    writer3.append("\n");
                    if(a==1){
                    writerFCA.append(String.valueOf((obj.getNumDropped() - warmupCalls)/(obj.getNumCalls() - warmupCalls)));
                    writerFCA.append("\n");
                    writerFCA2.append(String.valueOf((obj.getNumBlocked() - warmupBlocked)/(obj.getNumCalls() - warmupCalls)));
                    writerFCA2.append("\n");
                    writerFCA3.append(String.valueOf(clock));
                    writerFCA3.append("\n");*/
                }

                double percentCallsDropped = (obj.getNumDropped() - warmupDropped)/(obj.getNumCalls() - warmupCalls);
                double percentCallsBlocked = (obj.getNumBlocked() - warmupBlocked)/(obj.getNumCalls() - warmupCalls);
                System.out.println("% Dropped: " + percentCallsDropped*100 + ", % Blocked: " + percentCallsBlocked*100);
                //System.out.println("Pos: " + pos/obj.getNumCalls() + " Speed: " + speed/obj.getNumCalls() + " Arrival Time " + arrivalTime/obj.getNumCalls() + " Base Station: " +  baseStation/obj.getNumCalls() + " Call Duration : "+ callDuration/obj.getNumCalls());
                totalNumCallsDropped += obj.getNumDropped() - warmupDropped;
                totalNumCallsBlocked += obj.getNumBlocked() -  warmupBlocked;
                totalNumCalls += obj.getNumCalls() - warmupCalls;
            }
            /*writer.close();
            writer2.close();
            writer3.close();
            writerFCA.close();
            writerFCA2.close();
            writerFCA3.close();*/
            double totalPercentCallsDropped = totalNumCallsDropped/totalNumCalls;
            double totalPercentCallsBlocked = totalNumCallsBlocked/totalNumCalls;
            System.out.println("Average % Dropped: " + totalPercentCallsDropped*100 + ", Average % Blocked: " + totalPercentCallsBlocked*100 + ", FCA: " + FCA);
        }
    }
}

