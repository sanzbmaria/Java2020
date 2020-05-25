import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PA2v2 {
    public static void main(String[] args) throws GameExeptions.HitException, IOException {

        //1. Get the inputs  (FIX add input file)
        Scanner scanner = new Scanner(System.in);

        //The user will input the number of bombs they want to use (aka the number of tries they will have)
        //The user will also input the mode they want to use R for game mode and D for debugging
        int bombs = 0;
        char mode = 0;

        //If the user inputs a negative number or 0 the game will finish
        try {
            bombs = scanner.nextInt();

            if (bombs <= 0) {
                throw new GameExeptions.BombInputException();
            }
        } catch (GameExeptions.BombInputException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        //IF the mode is not R r or D d then the program will finish
        try {
            mode = scanner.next().charAt(0);
            if (!(mode == 'R' || mode == 'r' || mode == 'd' || mode == 'D')) {
                throw new GameExeptions.ModeInputException();
            }

        } catch (GameExeptions.ModeInputException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }


        ///GETING INPUT FROM  THE FILE
        //Scan the file
        String  inputFileName;
        inputFileName = scanner.nextLine().trim();
        //Just in case delete spaces from the string
        inputFileName = inputFileName.replaceAll("\\s","");
        File input = new File( inputFileName );

        //Check if the file exist
        boolean exists = input.exists();

        String[] locations = new String[10];
        //If it exist save it into a string
        if(exists){
            Scanner scan = new Scanner(input);

            //Save it into a string
            for (int i = 0; i < 10; i++) {
                locations[i] = scan.nextLine();
            }
        }

        //If it doesnt then just go into create a board mode
        //Play according to the mode selected
        if (!exists) {
            switch (mode) {
                case 'D':
                    //Intentional
                case 'd':
                    Playing.debugging(bombs);
                    break;
                case 'R':
                    //Intentional
                case 'r':
                    Playing.release(bombs);
                    break;

            }
        }
        if (exists) {
            switch (mode) {
                case 'D':
                    //Intentional
                case 'd':
                    Playing.debuggingFile(bombs, locations);
                    break;
                case 'R':
                    //Intentional
                case 'r':
                    Playing.releaseFile(bombs, locations);
                    break;

            }
        }

    }

}
