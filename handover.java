/**
 * Write a description of class handover here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class handover
{
    // instance variables - replace the example below with your own
    private double time;
    private double speed;
    private int station;
    private double duration;
    private String direction;
    private initialize obj;
    /**
     * Constructor for objects of class handover
     */
    public handover(double time, double speed, int station, double duration, String direction)
    {
        // initialise instance variables
        this.time = time;
        this.speed = speed;
        this.station = station;
        this.duration = duration;
        this.direction = direction;
    }

    public void handoverEvent(event e, initialize obj){
        double prev_handover_time = e.getEventTime();
        double pos = e.getPos();
        double timeSpent = prev_handover_time - e.getStartTime();
        //System.out.println(obj.getCellChannelsFree()[station] + " " + station);
        //System.out.println(prev_handover_time + " " + e.getStartTime() + " " + duration + " " + timeSpent);
        if(prev_handover_time - e.getStartTime() > duration || station <= 0 || station >= 19){
            event e1 = new event(pos, speed, direction, station, duration, "Terminate", time, e.getStartTime());
            obj.addFEL(e1);
        }
        else{
            int directionBinary = 0;
            if(direction.equals("Right"))
                directionBinary = 1;
            else directionBinary = -1;
            int nextStation = station+directionBinary;
            if(nextStation >= 0 && nextStation < 20 && obj.getCellChannelsFree()[nextStation] == 0){
                event e1 = new event(pos, speed, direction, station, duration, "Dropped", time,e.getStartTime());
                obj.addFEL(e1);
                obj.releaseCellChannelsFree(station);
            }
            else{
                double position = pos;
                double handover_time;
                double remainingdist;
                if(direction.equals("Right")){
                    remainingdist = (nextStation*2) - pos;
                    position = pos + remainingdist;
                    handover_time = time + (remainingdist/speed);
                }
                else{
                    remainingdist = pos - (nextStation*2);
                    position = pos - remainingdist;
                    handover_time = time + (remainingdist/speed);
                    }
                event e1 = new event(position, speed, direction, nextStation, duration, "Handover", handover_time, e.getStartTime());
                obj.addFEL(e1);
                if(station >= 20 || station < 0)
                    System.out.println(e.toString() + " " + station);
                obj.releaseCellChannelsFree(station);
                if(nextStation >= 0 && nextStation < 20)
                obj.setCellChannelsFree(nextStation);
            }
            obj.setClock(time);
            return;
        }
    }
}
