import java.awt.*;

//The class ship describes the types of ships available
public class Ship {
    String type; //Type of ship (ej. a Destroyer or Submarine)
    private int length; //How long the ship is
    char initial; //The initial that is going to be shown on the map
    String initialX; //When the ship is hit then X + the initial will be shown on the map

    //Constructor for ship
    public Ship(int length, String type, char initial) {
        this.length = length;
        this.type = type;
        this.initial = initial;
        initialX = "X" + Character.toLowerCase(getInitial());
    }

    //Get methods
    public String getType(){
        return type;
    }
    public int getLength(){
        return  length;
    }
    public char getInitial(){
        return initial;
    }
    public String getInitialX(){
        return  initialX;
    }
}


//Ships that must be created (according to the PDF)
class AircraftCarrier extends Ship{

    public AircraftCarrier() {
        super(6, "Aircraft Carrier", 'A');
    }
}
class Battleship extends Ship{

    public Battleship() {
        super(4, "Battleship", 'B');
    }
}
class Submarine extends Ship{

    public Submarine() {
        super(3, "Submarine", 'S');
    }
}
class Destroyer extends Ship{

    public Destroyer() {
        super(3, "Destroyer", 'D');
    }
}
class PatrolBoat extends Ship{

    public PatrolBoat() {
        super(2, "Patrol Boat", 'P');
    }
}
