import java.util.Scanner;

public class Game {
    private static int lives = 3;
    private static String[] words = new String[] { "calculator", "apa", "mere", "pere", "gsv", "vultur" };
    private static String randomWord = words[(int)(Math.random() * words.length)];
    private static String unusedLetters = "abcdefghijklmnopqrstuwxyz";
    private static char[] litere = new char[randomWord.length()];

    public static void SplashScreen(){
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
        System.out.println("|       G.S.V - [HANG_MAN] V1.0        |");
        System.out.println("|______________________________________|");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        System.out.println("[#1] -> #START [GAME] <-");
        System.out.println("[#2] -> #EXIT [GAME] <-");
    }

    public static void Start(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[#?] -> ");
        int option = scanner.nextInt();
        switch(option){
            case 1: {
                StartGame();
                break;
            }
            case 2:{
                try {
                    ExitGame();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            default:{
                //scanner.close();
                Start();
                break;
            }
        }
        scanner.close();
    }

    private static void StartGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n");
        int i=0;
        boolean gameOver = true;
        for(i=0; i<litere.length; i++){
            litere[i] = '.';
        }
        while(lives > 0){
            ShowLives();
            System.out.println("[#UNUSED LETTERS]: " + unusedLetters);
            ShowHiddenWord();
            //CheckAnswer();
            System.out.print("[#ANSWER]: ");
            String linie = scanner.nextLine();
            char litera = linie.charAt(0);
            boolean raspunsCorect = false;
            gameOver = true;

            unusedLetters = unusedLetters.replace(litera, '.');

            for(i=0; i<randomWord.length(); i++){
                char l = randomWord.charAt(i);
                if(litera == l){
                    raspunsCorect = true;
                    litere[i] = l;
                }
            }

            for(i=0; i<litere.length; i++){
                if(litere[i] == '.'){
                    gameOver = false;
                }
            }

            if(gameOver == true){
                System.out.println("[#GSV_HANGMAN]: You won! The word was [" + randomWord + "]");
                break;
            }

            if(raspunsCorect == false){
                lives--;
            }

            System.out.println("*-------------------*");
        }
        if(gameOver == false){
            System.out.println("[#GSV_HANGMAN]: You lost! The word was ["+ randomWord + "]");
        }
        scanner.close();
    }

    private static void ShowLives(){
        System.out.print("[#LIVES]: ");
        if(lives == 1){
            System.out.println("[#1] 2 3");
        }
        else if(lives == 2){
            System.out.println("1 [#2] 3");
        }
        else {
            System.out.println("1 2 [#3]");
        }
    }

    private static void ShowHiddenWord(){
        System.out.print("[#WORD]: ");
        for(int i=0; i<litere.length; i++){
            System.out.print(litere[i]);
        }
        System.out.print("\n");
    }

    private static void ExitGame() throws InterruptedException{
        System.out.println("[#GSV_HANGMAN]: Multumesc pentru ca mi-ai jucat jocul! :)\n[#GSV_HANGMAN]: Jocul se va inchide in:");
        for(int i=3; i>0; i--){
            if(i != 1){
                System.out.println("[#GSV_HANGMAN]: " + i + " secunde");
            }
            else {
                System.out.println("[#GSV_HANGMAN]: " + i + " secunda");
            }
            Thread.sleep(1000);
        }
        System.exit(0);
    }
}