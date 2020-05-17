import java.util.Scanner;

public class BattleShipGame {
/*STEP1 - Create a new object Scanner which will be used during the game 
for player to communicate with the computer*/
	Scanner input=new Scanner(System.in);
//STEP2 - create game environment - 2D Array with 10 lines and 10 columns in each line	
	private String[][]ocean=new String [10][10];
//STEP5 - define instant variable with starting value "0"
	private int playersShips=0;
    private int computersShips=0;
    
    
   
   
    
/*STEP3 - Create method "BattleShipGame" which has the same name as a class 
    and will start straight away when class is running*/    
    public BattleShipGame(){
//STEP4 - Create the ocean map and allocate it to BattleShipGame method
    	this.oceanMap();
//STEP6 - Let player to create his/her ships
    	this.playersShips();
//STEP7 - computer's ships
    	this.computersShips();
//STEP8 - Create the updated map with all ships in(only player's are visible)
    	System.out.println("\n Updated map. Your ships are marked up as \"@\".");
    	System.out.println(" ");
    	this.oceanMapUpdate();
//STEP12 - Game running + Game over:
    	this.game();
    	System.out.println("GAME OVER!");
    }

    
//STEP4 - Create the ocean map and allocate it to BattleShipGame method
    public void oceanMap(){
        System.out.println("  0123456789  ");
        for (int row = 0; row < ocean.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++) {
                if (ocean[row][col] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789  ");
    }

//STEP8 - Create the updated map with all ships in(only player's are visible)
    public void oceanMapUpdate(){
        System.out.println("  0123456789  ");
        for (int row = 0; row < ocean.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++) {
            	if (ocean[row][col]=="1") {
                    System.out.print("@");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789  ");
    }

//STEP6 - Let player to create his/her ships
    public void playersShips() {	
    	while (playersShips!=5) {
        	System.out.println("Enter X coordinate for your ship: ");
        	int x = input.nextInt();
        	System.out.println("Enter Y coordinate for your ship: ");
        	int y = input.nextInt();
        	if (((x >= 0) && (x <= 9)) && ((y >= 0) && (y <= 9))) {
        		if (ocean[x][y]==null) {
        			ocean[x][y] = "1";
        			playersShips += 1;
        			
        		}else {
        			System.out.println("You cannot place two ships to the same location. Try again, please!");
        		}
        	} else {
        		System.out.println("Your X and Y parameter must be 0<=X(Y)<=9. Try again, please!");
        	}
        }
    System.out.println("----------------------------------");
    }


//STEP7 - computer's ships
    public void computersShips() {    
        while (computersShips!=5) {
        int x=(int)(Math.random()*9);
        int y=(int)(Math.random()*9);
        if ((ocean[x][y]==null)&&(ocean[x][y]!="1")) {
            ocean[x][y] = "2";
            computersShips += 1;
            System.out.println(computersShips + ". computer's ship deployed!");
        	}
        }
    System.out.println("----------------------------------");
    }
 
   
    
//STEP9 - Player's turn
    public void yourTurn() {
    	System.out.println("Please X coordinate where you want to shot: ");
    	int x=input.nextInt();
    	System.out.println("Please Y coordinate where you want to shot: ");
    	int y=input.nextInt();
    	if (((x >= 0) && (x <= 9)) && ((y >= 0) && (y <= 9))) {
	    	if (ocean[x][y]=="2") {
	    		System.out.println("You shut the computer's ship. Well done!");
	    		computersShips-=1;
	    		ocean[x][y]="!";
	    	} 
	    	if (ocean[x][y]=="1") {
	    		System.out.println("You shut your own ship.");
	    		playersShips-=1;
	    		ocean[x][y]="x";
	    	}else {
	    		System.out.println("You missed.");
	    		ocean[x][y]="-";
	    	}
    	}else {
    		System.out.println("Your X and Y parameter must be 0<=X(Y)<=9. You lost one shot!");
    	}
    }    	
//STEP10 - computer's turn
    public void computersTurn() {
       	int x=(int)(Math.random()*9);
       	int y=(int)(Math.random()*9);
       	if (ocean[x][y]=="1") {
       		System.out.println("Computer shut your ship!");
       		playersShips-=1;
       		ocean[x][y]="x";
       	} 
       	if (ocean[x][y]=="2") {
    		System.out.println("Computer shut its own ship");
    		computersShips-=1;
    		ocean[x][y]="!";
    	} else {
       		System.out.println("Computer missed.");
       		ocean[x][y]="y";
       	}
    }   
//STEP11 - Game
    public void game() {
    	while (true) {
    		this.yourTurn();
    		this.computersTurn();
    		this.oceanMapUpdate();
    		System.out.println("Your ships: " + playersShips + ". Computer's ships: " + computersShips + "." );
    		System.out.println("----------------------------------");
    		System.out.println(" ");
    		if (playersShips==0){
    			System.out.println("You lost!");
    			break;
		   	} 
    		if (computersShips==0){
		   		System.out.println("Congratulation. You won!");
		   		break;
		   	}
    	}
    }  
}
