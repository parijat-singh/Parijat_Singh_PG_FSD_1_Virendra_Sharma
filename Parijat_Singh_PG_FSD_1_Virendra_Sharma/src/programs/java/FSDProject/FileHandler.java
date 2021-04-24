package programs.java.FSDProject;

import java.util.*;
import java.io.*;


public class FileHandler {
	
	public char ch; //this variable keeps track of user response to 'do u want to continue'
	boolean[] quitvar = new boolean[2]; // this variable helps resturn which menu user came out of (main menu & ASR menu)
	
	/* Constructor for FileHandler*/
	public FileHandler() {
		ch = 'y';
		quitvar[0] = quitvar[1] = false;
	}
	
	/* This method is called whenever user wants to quit out of main menu or does not want to continue*/
	public void systemquit() {
		System.out.println("Thank you for using LockedMe.com File Handling system.");
		System.out.println("Good Bye!");
		ch = 'n';
	}
	
	/* This method is the main control method that only returns when the user is done*/
	
	public boolean[] mainmenu() { //need 2 values in boolean return, first one track exit form main menu, second from ASR Submenu
		int choice;
		//first display the main menu
		System.out.println("Pick from the following Menu items:");
		System.out.println("1. List files");
		System.out.println("2. Add/Remove/Search");
		System.out.println("3. Exit Application");	 
		// then ask for user to pick a menu item and capture in variable choice
		Scanner scan = new Scanner(System.in); 
		String charChoice = scan.nextLine();
		if (charChoice.equals("1") || charChoice.equals("2") || charChoice.equals("3")) {
			 choice = Integer.parseInt(charChoice);
		} else {
			System.out.println("Wrong input");
			return quitvar;
		}
		//based of choice 1) List file 2) go to ASRMenu 3) Close Application
		switch (choice)
        {
        case 1 : 
        	//ask for the file path of directory from where files should be listed
        	Scanner scan1 = new Scanner(System.in); 
        	System.out.println("Enter File Path:");
        	String filePath = scan1.nextLine();
        	String[] pathnames;
        	try {  
	            //create an object with the given file path so we can apply the File methods
        		File file = new File(filePath);
	            
	            if (file.exists()) { //exists() method confirms if the file path exists or not
	            	System.out.println("List of files in Alphabetical order:");
	            	pathnames = file.list(); //list() method lists all files in the directory in alphabetical order. No use of TreeSet is required
	            	for (String pathname : pathnames) { // loop to print each file name
	            		System.out.println(pathname);
	            	}
	            	System.out.println(); //print an empty line to separate from next menu
	            	                       
	            } else System.out.println("File path does not exist!") ; //if exists() returns false
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	        }
            break;                         
        case 2 : 
            try
            {
            	quitvar[1] = ASRMenu(); //call ASRMenu() method when user picks option 2
                
            }
            catch(Exception e)
            {
                System.out.println("Error : " +e.getMessage());
            }
            break;                         
        case 3 : 
            try
            {
                systemquit(); // call systemquit() method when user picks 3
                quitvar[0] = true; // set return value of mainmenu to indicate user picked to close application
            }
            catch(Exception e)
            {
                System.out.println("Error : "+e.getMessage());
            }
            break;
        	default : System.out.println("Wrong Entry \n ");
        	break;
        }
		return quitvar;
	}
	
	// This method takes the user to the sub menu where user can pick other file operations - Add, Search, Remove and return to main menu
	public boolean ASRMenu() { //this boolean return is captured in the second element of boolean return for mainmenu()
		int choiceASR;
		boolean quitvarASR = false; // keeps track of whether the user decided to go to Main menu. 
		// first display the menu items available for user to pick from
		System.out.println("Which File operation would u like to perform:");
		System.out.println("1. Add File");
		System.out.println("2. Remove File");
		System.out.println("3. Search for a File");	 
		System.out.println("4. Return to Main Menu");
		// then ask user to make a selection, capture the selection in variable choiceASR
		Scanner scanASR = new Scanner(System.in); 
		String stchoiceASR = scanASR.nextLine();
		if (stchoiceASR.equals("1") || stchoiceASR.equals("2") || stchoiceASR.equals("3") || stchoiceASR.equals("4")) {
			 choiceASR = Integer.parseInt(stchoiceASR);
		} else {
			System.out.println("Wrong input");
			return quitvarASR;
		}
		
		//based on choiceASR go to the respective block 1) Add file 2) Remove File 3) Search file 4) Return to Main menu
		switch (choiceASR)
        {
        case 1 : 
        	//First ask for user to enter path to the directory where the file should be added
        	Scanner scanPath = new Scanner(System.in); 
        	System.out.println("Enter File Path where you want to add File:");
        	String filePath = scanPath.nextLine();
        	// next ask user to enter the name of the file that should be added to that path
        	Scanner scanFileAdd = new Scanner(System.in); 
        	System.out.println("Enter File Name:");
        	String addFileName = scanFileAdd.nextLine();
        	try {  
	            File addFile = new File(filePath + "\\" + addFileName); // capture full file path and file name in the object of class File
	            
	            if (addFile.createNewFile()) {  //try to create the file. if createNewFile() is true, then file was successfully created, if false, file already exists 
	                System.out.println("New File is created!");  
	            } else {                   
	                if(addFile.exists()) {
	                    System.out.println("File already exists.");
	                }
	            }
	        } catch (IOException e) {  //if filepath is invalid or any other exception, catch the exception
	        	System.out.println("Error : " +e.getMessage());
	        	//e.printStackTrace(); 
	        }
            break;                         
        case 2 : 
            Scanner scanPathR = new Scanner(System.in); 
            System.out.println("Enter File Path from where you want to remove File:");
        	String filePathR = scanPathR.nextLine();
        	Scanner scanFileRem = new Scanner(System.in); 
        	System.out.println("Enter File Name:");
        	String remFileName = scanFileRem.nextLine();
        	try {  
	            File remFile = new File(filePathR + "\\" + remFileName);
	            
	            if (remFile.delete()) {  // this will be false if file or directory does not exist
	                System.out.println("File Deleted!");  
	            } else {                   
	                if(!remFile.exists()) {
	                    System.out.println("File not found");
	                }
	            }
            }
            catch(Exception e)
            {
                System.out.println("Error : " +e.getMessage());
            }
            break;                         
        case 3 : 
        	Scanner scanPathS = new Scanner(System.in); 
            System.out.println("Enter File Path where you want Search File:");
        	String filePathS = scanPathS.nextLine();
        	Scanner scanFileSer = new Scanner(System.in); 
        	System.out.println("Enter File Name:");
        	String SerFileName = scanFileSer.nextLine();
        	try {  
	            File SerFile = new File(filePathS + "\\" + SerFileName);
	            
	            if (SerFile.exists()) {  
	                System.out.println("File Exists!"); 
	                System.out.println("Full Path of the file is " + filePathS + "\\" + SerFileName);
	            } else {                   
	                System.out.println("File not found");
	              }
            }
            catch(Exception e)
            {
                System.out.println("Error : " +e.getMessage());
            }
            break;
        case 4 :
        	quitvarASR = true;// sets return indicating quit from ASR Sub menu
        	break;
        	default : System.out.println("Wrong Entry \n ");
        	break;
        }
		return quitvarASR;
	}


}
