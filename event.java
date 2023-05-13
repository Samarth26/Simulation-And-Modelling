
/**
 * Write a description of class event here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class event
{
    // instance variables - replace the example below with your own
    private double pos;
    private double speed;
    private String direction;
    private int baseStation;
    private String typeOfEvent;
    private double callDuration;
    private double eventTime;
    private double callStartTime;
    /**
     * Constructor for objects of class event
     */
    public event(){
        pos = 0;
        speed = 0;
        direction = null;
        baseStation = 0;
    }
    public event(double pos, double speed, String direction, int baseStation, double callDuration, String typeOfEvent, double eventTime, double callStartTime)
    {
        // initialise instance variables
        this.pos = pos;
        this.speed = speed;
        this.direction = direction;
        this.baseStation = baseStation;
        this.callDuration = callDuration;
        this.typeOfEvent = typeOfEvent;
        this.eventTime = eventTime;
        this.callStartTime = callStartTime;
    }
    public double getPos(){
        return this.pos;
    }
    public double getSpeed(){
        return this.speed;
    }
    public String getDirection(){
        return this.direction;
    }
    public int getBaseStation(){
        return this.baseStation;
    }
    public double getCallDuration(){
        return this.callDuration;
    }
    public String getTypeOfEvent(){
        return this.typeOfEvent;
    }
    public double getEventTime(){
        return this.eventTime;
    }
    public double getStartTime(){
        return this.callStartTime;
    }
    public String toString(){
        return "pos: "  + pos + "speed: " + speed + "direction: "+ direction + "base station: " + baseStation + "call duration: " + callDuration + " type of event " + typeOfEvent + " event time " + eventTime;
    }
/**
 * Scanner sc = new Scanner(new File("F:\\CSVDemo.csv"));  
        sc.useDelimiter(",");   //sets the delimiter pattern  
        while (sc.hasNext())  //returns a boolean value  
        {  
            values.add(sc.next());  //find and returns the next complete token from this scanner  
        }
        sc.close();  //closes the scanner  
 */
}
