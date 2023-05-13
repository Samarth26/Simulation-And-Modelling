
/**
 * Write a description of class termination here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class termination
{
    // instance variables - replace the example below with your own
    private double time;
    private int station;
    /**
     * Constructor for objects of class termination
     */
    public termination(double time, int station)
    {
        // initialise instance variables
        this.time = time;
        this.station = station;
    }
    public void terminationEvent(initialize obj, event e1){
        if(obj.getFEL().size() == 0){
            obj.releaseCellChannelsFree(station);
        }
        else{
            String typeOfEvent = e1.getTypeOfEvent();
            if(typeOfEvent.equals("Terminate")){
                if(station < 20 && station >= 0)
                obj.releaseCellChannelsFree(station);
                obj.incrementNumTerminated();
            }
            else{
                if(typeOfEvent.equals("Blocked")) obj.incrementNumBlocked();
                else obj.incrementNumDropped();
            }
        }
        obj.setClock(time);
        return;
    }
}
