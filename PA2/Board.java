import javax.swing.undo.AbstractUndoableEdit;
import java.awt.*;

public class Board {

    private final int columns = 10;
    private final int rows = 10;

    String[] userInput;

    //This is used to later initialize the boats
    private final int AIRCRAFTCARRIER = 0;
    private final int BATTLESHIP = 1;
    private final int SUBMARINE = 2;
    private final int DESTROYER = 3;
    private final int PATROLBOAT = 4;

    //This are the states possible on the map, used while printing and checking if it is hit
    private final int NOT_EXPLORED = 0;
    private final int HIT_SHIP = 1;
    private final int WATER = 2;
    private final int SHIP = 3;


    private int[][] showBoard;//This arrays is used while printing
    private Ship[][] board; //This array contains the actual objects Ship
    private int[] numberShips; //This array is used to initialize the ships

    public Board() {
        //1. Create the board where the ship objects will be
        this.showBoard = new int[rows][columns];
        //2. Create the board used to print
        board = new Ship[rows][columns];
    }

    public void noFile(){
        calculateShipNumber();
        createShips();
    }

    public void file(String[] locations){
        this.userInput = locations;
        formatString(userInput);
        createFromFile(userInput);
    }

    /*Because of the way the file is given if there is and enter there are no spaces after the enter in that line.
    * For example a line that has the ship P and only P is given as "P" but it should be given as "P         " so this
    * function adds those leading spaces  */
    private void formatString(String[] userInput){
        int counter = 0;
        int idx = 0;
        String[] temp = new String[10];
        //Iterate through each line, if the length is not 10 it means that the line has a \n and therefore it needs more spaces
        for (int i = 0; i < 10; i++) {
            int len = userInput[i].length();
            if(userInput[i].length() != 10){
                counter = userInput[i].length();
                temp[i] = userInput[i];
                while(counter!=10){
                    temp[i] += " ";
                    counter++;
                }
            }
            else
                temp[i] = userInput[i];
        }
        this.userInput = temp.clone();
    }

    /*This function just iterates through the string and according to the letter in the position it creates the coorresponding
    * Ship on the same location */
    private void createFromFile(String[] locations){
       int counter = 0;
        int idxString = 0 ;
        char cTemp;
        Ship s = null; //Create each ship object

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cTemp = locations[i].charAt(j);


                //1. Create the object boat
                switch (cTemp) {
                    case 'A':
                        s = new AircraftCarrier();
                        this.board[i][j] = s;
                        break;
                    case 'B':
                        s = new Battleship();
                        this.board[i][j] = s;
                        break;
                    case 'S':
                        s = new Submarine();
                        this.board[i][j] = s;
                        break;
                    case 'D':
                        s = new Destroyer();
                        this.board[i][j] = s;
                        break;
                    case 'P':
                        s = new PatrolBoat();
                        this.board[i][j] = s;
                        break;
                    case ' ':
                        this.board[i][j] = null;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }

            }
        }
    }

    private void calculateShipNumber() {
        this.numberShips = new int[5];
        //As initialized before each of the boats are represented by an index in the array, this sets the number of
        //ships to be created for each type (also established by the pdf)
        numberShips[AIRCRAFTCARRIER] = 1; //Aircraft
        numberShips[BATTLESHIP] = 2; //Battleship
        numberShips[SUBMARINE] = 2; //Submarine
        numberShips[DESTROYER] = 1; //Destroyer
        numberShips[PATROLBOAT] = 4; //Patrol
    }

    private void createShips() {
        //System.out.println("ENTERED CREATE SHIP");
        Ship s; //Create each ship object

        int length, coordinateX, coordinateY, orientation, finalX, finalY;
        boolean vertical, canFit;
        //Makes the loop that will create all of the ships
        for (int i = 0; i < numberShips.length; i++) {
            for (int n = 0; n < numberShips[i]; n++) {
                //Creates the ship objects according to the index
                switch (i) {
                    case AIRCRAFTCARRIER:
                        s = new AircraftCarrier();
                        break;
                    case BATTLESHIP:
                        s = new Battleship();
                        break;
                    case SUBMARINE:
                        s = new Submarine();
                        break;
                    case DESTROYER:
                        s = new Destroyer();
                        break;
                    case PATROLBOAT:
                        s = new PatrolBoat();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }

                int counter = 0;
                length = s.getLength();

                /*The do while loop first tries to create a random position, then that position gets checked in the
                * "CheckIfFits" function, and if it fits it exits, if it doesnt fit then the loop starts again */
                do {
                    counter++;
                    canFit = true;
                    coordinateX = OtherMethods.generateAleatoryNum(0, this.board.length - 1); //This generates the X coordinate randomly
                    coordinateY = OtherMethods.generateAleatoryNum(0, this.board.length - 1); //This generates the Y coordinate randomly
                    orientation = OtherMethods.generateAleatoryNum(0, 1); //This gives 0 or 1 . 0 is vertical 1 is horizontal

                    //randomly get the orientation
                    switch (orientation) {
                        case 0: //DOWN
                            vertical = true;
                            break;
                        case 1: //RIGHT
                            vertical = false;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + orientation);
                    }

                    //Get the starting location aka coordinateX / coordinateY and add to the length depending on the orientation
                    if (vertical) {
                        finalX = coordinateX;
                        finalY = coordinateY + ((length));
                    } else {
                        finalX = coordinateX + length;
                        finalY = coordinateY;
                    }

                    //Call function to check if it fits
                    canFit = checkIfFits(coordinateX, coordinateY, finalX, finalY, vertical);

                } while (!canFit);

                /*If it fits then the ship will be 'put' on the board array, starting from {coordinateX, coordinateY}
                * and going all the way to finalX/finalY-1 */

                if (vertical) {
                    for (int j = coordinateY, im = 0; j != finalY; j++, im++) {
                        this.board[coordinateX][j] = s;
                    }
                } else {
                    for (int j = coordinateX, im = 0; j != finalX; j++, im++) {
                        this.board[j][coordinateY] = s;
                    }
                }
            }
        }

    }


    public boolean checkIfFits(int initialX, int initialY, int finalX, int finalY, boolean vertical) {

        //4. Check if the location is okay

        //check if the final position is within the bounds of the array (< 10 or >= 0)
        if (finalX > 10 || finalX < 0)
            return false;
        if (finalY > 10 || finalY < 0)
            return false;


        int orientationX, orientationY;
        if(vertical){
            orientationX = 0;
            orientationY = 1;
        }
        else{
            orientationX = 1;
            orientationY = 0;
        }

        //check if there is already a boat at the positions that we want to place the boat
        for (int i = initialX, j = initialY;(i!= finalX || j != finalY); i+= orientationX, j+= orientationY) {
            if (this.board[i][j] != null)
                return false;
        }

        //Checks the sides of the boat (the boat can not be besides any other boat)
        for (int i = initialX, j = initialY;(i!= finalX || j != finalY); i+= orientationX, j+= orientationY) {
            if (vertical) {
                //Check the right side
                if (!(initialX== 9) && this.board[(i + 1)][j] != null )
                    return false;

                //Check the left side
                if (!(initialX==0) && this.board[(i - 1)][j] != null )
                    return false;
            } else { //if horizontal
                //Check down side
                if ( !(initialY== 9)&& this.board[i][(j + 1)] != null)
                    return false;
                    //Check up side
                if (!(initialY== 0) && this.board[i][(j - 1)] != null  )
                        return false;
            }
        }

        //check the position before the start and end of the boat (so that two ships are not in line)
        if (vertical) {
            //checks up
            if (initialY != 0)
                if(this.board[(initialX)][(initialY-1)] != null )
                    return false;
            //checks down
            if (finalY <= 9)
                if(this.board[finalX][finalY] != null )
                    return false;
        }
        else {
            //checks left
            if (initialX != 0)
                if( this.board[(initialX - 1)][initialY] != null )
                    return false;

            //checks right
            if (finalX != 10)
                if( this.board[finalX][finalY] != null )
                return false;


        }
        return true;
    }


    //Honestly I dont know why I need two prints but they work and I tried to change it and something happened so im not touching it

    public void printBoard() {
        //prints a - j
        System.out.print("\n     ");
        for (int i = 'A'; i < ('A' + this.board.length); i++) {
            System.out.print((char) i + "  "); // (char)
        }
        //prints the line after the letters
        System.out.print("\n     ");
        for (int i = 'A'; i <= 'J'; i++) {
            System.out.print("-" + "  ");
        }
        //prints the numbers and the boats
        System.out.println("");
        int column = 1;
        for (int i = 0; i < this.board.length; i++) {
            //If it is a single digit then print 2 spaces after
            if (column < 10)
                System.out.print(column++ + "  ");
            else //if it is a double digit print only one
                System.out.print(column++ + " ");
            System.out.print("| "); //print one space after the line
            for (int j = 0; j < this.board[0].length; j++) {
                if(this.board[i][j] == null){
                    System.out.print("   ");//print 3 spaces if blank
                }
                else
                    System.out.print( this.board[i][j].getInitial() + "  "); //print 2 spaces after the boat initial

            }
            System.out.println("");
        }
    }

    public void printBoardFinal(){
        //prints a - j
        System.out.print("     ");
        for (int i = 'A'; i < ('A' + this.board.length); i++) {
            System.out.print((char) i + "  "); // (char)
        }
        //prints the line after the letters
        System.out.print("\n     ");
        for (int i = 'A'; i <= 'J'; i++) {
            System.out.print("-" + "  ");
        }
        //prints the numbers and the boats
        System.out.println("");
        int column = 1;
        for (int i = 0; i < this.board.length; i++) {
            //If it is a single digit then print 2 spaces after
            if (column < 10)
                System.out.print(column++ + "  ");
            else //if it is a double digit print only one
                System.out.print(column++ + " ");
            System.out.print("| "); //print one space after the line
            for (int j = 0; j < this.board[0].length; j++) {
                switch (this.showBoard[i][j]) {
                    case NOT_EXPLORED:
                        System.out.print("   ");//print 3 spaces if blank
                        break;
                    case SHIP:
                        System.out.print( this.board[i][j].getInitial() + "  "); //print 2 spaces after the boat initial
                        break;
                    case WATER:
                        System.out.print('X' + "  " ); // 2 spaces afterwards
                        break;
                    case HIT_SHIP:
                        System.out.print( this.board[i][j].getInitialX() + " "); //print 1 spaces after the boat initial
                        break;
                }
            }
            System.out.println("");
        }
    }


    //When  the user inputs coordinates this checks the state of the coordinates (aka. if it has been explored, hit, if it is water or if it is a ship)
    public void checkCoordentates(int x, int y) throws GameExeptions.HitException {
        try{
            if (x >= 0 && x < this.board.length && y >= 0 && y <= this.board.length) {
                //IF it is a ship it means it hasnt been hit therefore the state changes to hit
                if (this.showBoard[x][y] == SHIP) {
                    this.showBoard[x][y] = HIT_SHIP;
                    System.out.println("HIT " + this.board[x][y].getInitial());
                }
                //If it is not explored it means it is "untouched" water, therefore the state changes to water (aka. touched water)
                else if (this.showBoard[x][y] == NOT_EXPLORED) {
                    this.showBoard[x][y] = WATER;
                    System.out.println("MISS");
                }
                //If it is water or hit_ship it means the user had previously typed the same coordinates therefore it throws an error
                else if(this.showBoard[x][y] == WATER || this.showBoard[x][y] == HIT_SHIP)
                    throw new GameExeptions.HitException("Try again");
            }
        }catch (GameExeptions.HitException e){
            System.out.println(e.getMessage());
        }

    }

    //This just copies the locations of the boats in the board array to the showBoard array
    public void initializeMap(){
        //update and place the other boats
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(this.board[i][j] != null)
                    this.showBoard[i][j] = SHIP;
                else
                    this.showBoard[i][j] = NOT_EXPLORED;
            }
        }
    }

    //The score is supposed to equal to the size of the ship (which makes no sense because the smallers are harder to hit but give you less points but meh)
    public int getScore() {
        int score = 0;

        //So I iterate through the show board and if I find a location that is hit I look into the board array and get the length to add it to the score.
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                switch (this.showBoard[i][j]) {
                    case HIT_SHIP:
                        score += this.board[i][j].getLength();
                        break;
                }
            }
        }
        return  score;
    }
}

