package programs.java.FSDProject;

import java.util.*;
import java.io.*;

public class FileHandling {
	 public static void main(String[] args) {
		 
		 /* Create a new object fh of the FileHandler Class.
		  * All Operation will happen on the object         */
		 FileHandler fh = new FileHandler();
		 
		 
		 System.out.println("**********************************************************");
		 System.out.println("*                Welcome to LockedMe.com                 *");
		 System.out.println("*                                                        *");
		 System.out.println("*                Developer: Parijat Singh                *");
		 System.out.println("**********************************************************");
		 System.out.println();
		 
		 
		 boolean[] quit = new boolean[2]; // need 2 variables to keep track of main menu and ASR menu quit
		 Scanner scan = new Scanner(System.in); //this scan variable hold user response to 'Do u want to continue'
		 do {
			 quit = fh.mainmenu();  //call the mainmenu method for the file handler class that returns which menu items selected quit and which did not
			 if (!quit[0] && !quit[1]) {
				 quit[1] = false; // reset ASR menu quit variable in case user decides to go back to ASR
				 /* the logic below is to ensure correct response to Do u want to continue*/
				 System.out.println("\nDo you want to continue (Type y to continue, any other key to exit application) \n");
		         fh.ch = scan.next().charAt(0);
		         if (fh.ch == 'y' || fh.ch=='Y') {
		        	 continue;//if user enters y then continue to show main menu
		        	 
		         } else { //Exit gracefully when user does not want to cotinue
		        	 fh.systemquit();
		         } 
	         } else if (quit[0]) {// to make sure the exit is from main menu only
	        	 fh.ch = 'n';
	           }
	 
	     } while (fh.ch == 'Y'|| fh.ch == 'y'); //stay within the application until the user keeps responding y
	 }


}
