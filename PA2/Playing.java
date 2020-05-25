import java.util.Scanner;

//This class handles the game
public class Playing {

    //If the user choose debugging then this method will start !!WHEN FILE DOESNT EXIST
    public static void debugging(int bombs) throws GameExeptions.HitException {
        Scanner scanner = new Scanner(System.in);

        char x = 1;
        char y = 1;
        String input;
        int temp;
        int temp2;

        boolean isHit = true; //Shows if the location has been already hit

        Board b = new Board();//Create the board. This will create a random board the ships in random positions
        b.noFile();
        b.printBoard(); //Prints the board
        b.initializeMap(); //Used to show the map

        while (bombs != 0) {
            //1. scan as a string
            input = scanner.nextLine();
            //2. Separate
            x = input.charAt(0);
            y = input.charAt(1);
            //3. send x to custom function
            temp = OtherMethods.convertCharToInt(x);
            temp2 =  Integer.parseInt(String.valueOf(y));


            if(input.length() == 3)
                b.checkCoordentates(9, temp);
            else
                b.checkCoordentates(temp2-1, temp);
            bombs--;
            b.printBoardFinal();
        }

        System.out.println("Score " + b.getScore());
    }

    //Same as debugging but doesnt shows the map - shows it at the end .
    public static void release(int bombs) throws GameExeptions.HitException {
        Scanner scanner = new Scanner(System.in);
        char x = 1;
        char y = 1;
        String input;
        int temp;
        int temp2;
        boolean isHit = true;
        Board b = new Board();
        //b.printBoard();
        b.initializeMap();
        while (bombs != 0) {
            //1. scan as a string
            input = scanner.nextLine();
            //2. Separate
            x = input.charAt(0);
            y = input.charAt(1);
            //3. send x to custom function
            temp = OtherMethods.convertCharToInt(x);
            temp2 = Integer.parseInt(String.valueOf(y));


            if (input.length() == 3)
                b.checkCoordentates(9, temp);
            else
                b.checkCoordentates(temp2 - 1, temp);
            bombs--;
        }


        b.printBoardFinal();
        System.out.println("Score " + b.getScore());
    }


    //!!! WHEN FILE EXIST
    //Same as the one above but instead of creating the board it calls the method to convert the string array into the board

    public static void debuggingFile(int bombs, String[] locations) throws GameExeptions.HitException {
        Scanner scanner = new Scanner(System.in);

        char x = 1;
        char y = 1;
        String input;
        int temp;
        int temp2;

        boolean isHit = true; //Shows if the location has been already hit

        Board b = new Board();
        b.file(locations);
        //Create the board. This will create a random board the ships in random positions
        b.printBoard(); //Prints the board
        b.initializeMap(); //Used to show the map

        while (bombs != 0) {
            //1. scan as a string
            input = scanner.nextLine();
            //2. Separate
            x = input.charAt(0);
            y = input.charAt(1);
            //3. send x to custom function
            temp = OtherMethods.convertCharToInt(x);
            temp2 =  Integer.parseInt(String.valueOf(y));


            if(input.length() == 3)
                b.checkCoordentates(9, temp);
            else
                b.checkCoordentates(temp2-1, temp);
            bombs--;
            b.printBoardFinal();
        }

        System.out.println("Score " + b.getScore());
    }

    public static void releaseFile(int bombs, String[] locations ) throws GameExeptions.HitException {
        Scanner scanner = new Scanner(System.in);

        char x = 1;
        char y = 1;
        String input;
        int temp;
        int temp2;

        boolean isHit = true; //Shows if the location has been already hit

        Board b = new Board();
        b.file(locations);
        //Create the board. This will create a random board the ships in random positions
        b.initializeMap(); //Used to show the map

        while (bombs != 0) {
            //1. scan as a string
            input = scanner.nextLine();
            //2. Separate
            x = input.charAt(0);
            y = input.charAt(1);
            //3. send x to custom function
            temp = OtherMethods.convertCharToInt(x);
            temp2 =  Integer.parseInt(String.valueOf(y));


            if(input.length() == 3)
                b.checkCoordentates(9, temp);
            else
                b.checkCoordentates(temp2-1, temp);
            bombs--;

        }
        b.printBoardFinal();
        System.out.println("Score " + b.getScore());
    }
}
