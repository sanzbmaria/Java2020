import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class OtherMethods {
    public static int generateAleatoryNum(int min, int max) {
        int num = (int) Math.floor(Math.random() * (max - min + 1) + (min));
        return num;
    }

    public static int convertCharToInt(char x) {
        int temp;
        switch (x) {
            case 'A':
                temp = 0;
                break;
            case 'B':
                temp = 1;
                break;
            case 'C':
                temp = 2;
                break;
            case 'D':
                temp = 3;
                break;
            case 'E':
                temp = 4;
                break;
            case 'F':
                temp = 5;
                break;
            case 'G':
                temp = 6;
                break;
            case 'H':
                temp = 7;
                break;
            case 'I':
                temp = 8;
                break;
            case 'J':
                temp = 9;
                break;
            default:
                temp = 10;
                break;
        }
        return temp;
    }



}
