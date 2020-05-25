public class GameExeptions extends Exception {

    GameExeptions(String message){
        super(message);
    }

    public static class HitException extends GameExeptions {
        public HitException(){
            super("HitException");
        }
        public HitException(String message){
            super(message);
        }
    }

    public static class ModeInputException  extends GameExeptions {
        public ModeInputException(){
            super("ModeInputException");
        }

        public ModeInputException(String message){
            super(message);
        }
    }
    public static class BombInputException extends GameExeptions {
        public BombInputException(){
            super("BombInputException");
        }

        public BombInputException(String message){
            super(message);
        }
    }

}
