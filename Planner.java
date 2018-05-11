/**
Name: Nicholas Boesel
Date: 2/20/2016
Course/Section: IT 206.001
Assignment: Programming Assignment 4

This class starts off by making the host family and then asking for other families to invite, it validates the data entered into them and then exits to the menu after the user decides they don't want
to enter more families into the list. The options in the menu give you the option to rescind invitations, get responses, display reports about the families and about the meals being catered,
and exit the menu when you're done. Rescinding invitations allows the user to delete families from the list that have not responded. Getting the responses allows the user to confirm if a family is
coming and also allows them to set the number of meals of each type per person in the family. The family and caterer reports give you details based around the families in the list and how many
diferent meals are to be offered. The exit menu method congratulates the host family and quits the program. 

The number of families is set to 100 as the host can only handle so many families. the meal limt is set to 250 in order to comply with the venue capacity*/
import javax.swing.JOptionPane;
public class Planner
{
   public static void main(String[]args)
   {
   //sets the array of familites to be filled
      Family[] families=initFamilies();
   //sets the limit to the number of guests and meals
      final int MEAL_LIMIT=initMeals();
   //allows the menu to show itself over and over again
      boolean repeater=initRepeater();
   //enter the host's data and make out the invitations
      Family hostFamily=makeHost(MEAL_LIMIT);
      families=addFamilies(families,MEAL_LIMIT,hostFamily);
   //run the menu
      while(repeater)
      {repeater=menu(families, hostFamily, repeater);}
      exitMenu();
   }
   
/**
initFamilies() creates an array of 100 spaces for new families to be invited, 99 because the host family already counts as 1 family and 1 invitation, amking a total of 100
@return This returns the empty list of spaces for new Family objects*/   
   public static Family[] initFamilies()
   {
      Family[] families=new Family[100];
      return families;
   }

/**
creates a number limit of 250 to go along with the limit meals that can be served
this also functions as the limit for the number of people allowed to attend the wedding
as each guest at the wedding only counts toward one meal
@return This returns the limit of 250*/     
   public static final int initMeals()
   {return 250;}
   
/**
Asks the user which family they would like to rescind their invitation towards. (namely take them off the list)
if the family has already responded, they cannot be removed, only those who haven't responded yet can have their invitations rescinded.
@param This uses the families array as an input as this is what is being changed/deleted from.
@return This returns an updated families array with the rescinded invites removed from the list*/   
   public static Family[] rescindInvitation(Family[] families)
   {
      String family=JOptionPane.showInputDialog("which family would you like to take off the list?");
      boolean itsGood=false;
      for(int i=0;i<families.length;i++)
      {
         //checks if the family name put in is the last on the list
         if(families[i]!=null && family.equals(families[i].getName()) && i==(families.length-1))
         {
            families[i]=null;
            Family.removeInvite();
            itsGood=true;
         }
         //checks if the family name put in is valid in other parts of the list
         else if(families[i]!=null && family.equals(families[i].getName()) && families[i].getRSVP()==false)
         {
            families[i]=families[i+1];
            families[families.length-1]=null;
            Family.removeInvite();
            itsGood=true;
            JOptionPane.showMessageDialog(null,"family erased");
         }
         //displays an error if the name entered matches no names on the list 
         else if(!itsGood && i==(families.length-1))
         {JOptionPane.showMessageDialog(null,"Error, returning to menu");}
      }
      return families;
   }
   
/**
Asks the invited families what kind of food they would like and then gives them an option for chicken or vegetable dinners for every individual member
This also verifies that the family named is coming and sets their RSVP to true. returns an error if an incorrect family name is entered. 
@param This uses the families array as an input as this is what is being changed.
@return This returns an updated families array with the recorded responses and number of meals*/   
   public static Family[] recordResponse(Family[] families)
   {
      String famName=JOptionPane.showInputDialog("What is your family's name?");
      for(int i=0;i<families.length;i++)
      {
      //checks if the name that you've entered is valid and if the family hasn't responded yet
            if(families[i]!=null && famName.equals(families[i].getName()) && families[i].getRSVP()==false)
            {
               for(int j=0;j<families[i].getMems();j++)
               {
               //lets the user pick his choice of meal
                  String[] options = {"Chicken Meal","Veggie Meal"};
                  int pick=JOptionPane.showOptionDialog(null, "What food do you want?","",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,"");
                  if(pick==0)
                  {
                  //pick chicken
                     families[i].addChicken();
                     JOptionPane.showMessageDialog(null,"Chicken meal added");
                  }
                  else
                  {
                  //pick veggies
                     families[i].addVeg();
                     JOptionPane.showMessageDialog(null,"Veggie meal added");
                  }
               }
               //shows that the family being edited has responded
               families[i].setRSVP(true);
            }
            else if(families[i]==null && i==(families.length-1))
            {JOptionPane.showMessageDialog(null,"Error, returning to menu");}
      }
      return families;
   }
/**
Asks the user to input a family name after displaying a list of the families currently on the invitation list.
If the name input is that of a family who has responded, it displays all of their details, with families who haven't responded you are just given their name
and an error if the name entered is invalid.
@param This uses the families array as an input as this is what is being viewed.
@return a report on a specific family through a window*/     
   public static void famReport(Family[] families,Family hostFamily)
   {
      String famList=hostFamily.getName()+"\n";
      //puts all of the entered families into a list
      for(int i=0;i<families.length;i++)
      {
         if(families[i]!=null)
         {famList+=families[i].getName()+"\n";}
      }
      JOptionPane.showMessageDialog(null,famList);
      String famName=JOptionPane.showInputDialog("What family's details do you want to see?");
      //checks to see if the family name entered is the host family
      if(famName.equals(hostFamily.getName()))
      {JOptionPane.showMessageDialog(null,hostFamily.toString());}
      else
      {
      //checks to see if the name entered matches any other families
         for(int j=0;j<families.length;j++)
         {
            int match=0;
            if(families[j]!=null && famName.equals(families[j].getName()))
            {match=1;}
            
            if(families[j]!=null && famName.equals(families[j].getName()) && families[j].getRSVP()==true)
            {
            //for families that have responded, display data
               JOptionPane.showMessageDialog(null,families[j].toString());
            }
            else if(families[j]!=null && famName.equals(families[j].getName()) && families[j].getRSVP()==false)
            {
            //for families that haven't responded, display only the name
               JOptionPane.showMessageDialog(null,families[j].getName()+" family has not responded");
            }
            else if(families[j]==null && j==families.length-1)
            {
            //if the name isn't valid, display an error
               JOptionPane.showMessageDialog(null,"This family is not on the list");
            }  
         }
      }
   }
   
/**
Displays the total amount of meals of each kind after going through the array of invited families
@param This uses the families array as an input as this is what is being viewed.
@return a report on a how much of each type of food is needed in a window*/ 
   public static void catererReport(Family[] families, Family hostFamily)
   {
      int totalChicken=hostFamily.getChicken();
      int totalVeg=hostFamily.getVeg();
      for(int i=0;i<families.length;i++)
      {
      //gathers all of the meals together from each family and adds them up
         if(families[i]!=null)
         {totalChicken+=families[i].getChicken();
         totalVeg+=families[i].getVeg();}
      }
      //display the totals in a report
      JOptionPane.showMessageDialog(null,"Caterer Report\nTotal Chicken Meals: "+totalChicken+"\nTotal Veggie Meals: "+totalVeg);
   }
/**
This method gives the user the ability to send the information out for families that they want to invite, specifically the names and sizes of the families.
It checks the users based on how they did and as such sets the name and size if they are good an warns and re-prompts the user if they are bad.
It also contains a counter to make sure that the wedding is not overbooked, warning the user against it and re-prompting them.
@param This uses the families array as an input as this is what is being edited, the host family to count them in the total attendees too, and the meal limit to keep their numbers in check.
@return The finished Family objects compiled into the array's spaces, with the names and sizes built in*/    
   public static Family[] addFamilies(Family[] families,int MEAL_LIMIT,Family hostFamily)
   {
      boolean keepGoing=true;
      String famName;
      int famMems;
      boolean sure;
      int totalAttendees;
      for(int i=0;i<families.length && keepGoing==true;i++)
      {
      //entering the name of the family you're creating
         families[i]=new Family();
         famName=JOptionPane.showInputDialog("Name the family you want to invite");
         families[i].setName(famName);
         boolean itsGood=false; 
         do
         {
            try
            {
               do
                  {
                  //entering the number of family members
                     famMems=Integer.parseInt(JOptionPane.showInputDialog("How many members of the family are there?"));
                     sure=families[i].setMems(famMems);
                     totalAttendees=hostFamily.getMems();
                     for(int j=0;j<families.length;j++)
                        if(families[j]!=null)
                           {totalAttendees+=families[j].getMems();}
                     if(sure)
                        {itsGood=sure;}
                  //checking if family size is valid
                     if(!sure || totalAttendees>MEAL_LIMIT)
                        {JOptionPane.showMessageDialog(null,"Invalid number entry, You either entered a negative number or tried to overbook");}
                  }
               while(!sure || totalAttendees>MEAL_LIMIT);
            }
            catch(NumberFormatException e)
            {JOptionPane.showMessageDialog(null,"Error receiving number");}
         }
         while(!itsGood);
         //asks the user if they want to invite another family    
         int option=JOptionPane.showConfirmDialog(null, "Would you like to send another invite?","",JOptionPane.YES_NO_OPTION);
         if(option==0)
            {keepGoing=true;}
         else
            {keepGoing=false;}
      }
      return families;
   }
/**
This method gives the user the ability to create all the information for the hosting family, even their meals are recorded
and their response status set to true since the hosting family is going to hold the wedding, they'll come anyway.
It also contains a counter to make sure that the wedding is not overbooked, just so the user doesn't do something like have a 251 person host family.
If this is so, then an error rejects their size and tells them to try again.
@param the meal limit is used to help keep the families' numbers in check
@return The finished host Family object as its own seperate enitity to be accessed on its own*/   
   public static Family makeHost(int MEAL_LIMIT)
   {
      int famMems;
      boolean sure=false;
      Family hostFamily=new Family();
      //enter the name of the host family
      String famName=JOptionPane.showInputDialog("Enter the name of the family hosting this event");
      hostFamily.setName(famName);
      Family.setHostName(famName);
         do
         {
            try
            {
               do
               //enter the size of the host family
                     {
                        famMems=Integer.parseInt(JOptionPane.showInputDialog("How many members of the host family are there?"));
                        sure=hostFamily.setMems(famMems);
                        if(!sure || famMems>MEAL_LIMIT)
                           {JOptionPane.showMessageDialog(null,"Invalid number entry");}
                     }
               //checking if family size is valid
               while(!sure || famMems>MEAL_LIMIT);
            }
            catch(NumberFormatException e)
            {
               JOptionPane.showMessageDialog(null,"Error receiving number");
            }
         }
         while(!sure);
      for(int j=0;j<hostFamily.getMems();j++)
            {
            //picks the host family's meals out
               String[] options = {"Chicken Meal","Veggie Meal"};
               int pick=JOptionPane.showOptionDialog(null, "What food do you want?","",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,"");
               if(pick==0)
               {
               //pick a chicken meal
                  hostFamily.addChicken();
                  JOptionPane.showMessageDialog(null,"Chicken meal added");
               }
               else
               {
               //pick a veggie meal
                  hostFamily.addVeg();
                  JOptionPane.showMessageDialog(null,"Veggie meal added");
               }
            }
      hostFamily.setRSVP(true);
      return hostFamily;
   }
/**
This method displays the menu for the user with the functions "Rescind Invitation","Record Response","Print Family Report","Print Caterer Report",and "Exit menu"
all present as buttons that execute their functions by calling the respective method, th only exception being exit menu which quits the menu and then displays
the exit dialog.
@param The variables that go into this are the families array and the host family to be used by other methods inside it and a repeater boolean to keep this repeating in
the main method
@return repeater in order to check whether to continue displaying the menu or move on to the exitMenu method*/     
   public static boolean menu(Family[] families, Family hostFamily,boolean repeater)
   {
      String[] options ={"Rescind Invitation","Record Response","Print Family Report","Print Caterer Report","Exit menu"};
      int pick=JOptionPane.showOptionDialog(null, "Menu Options","",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,"");
      if(pick==0)
      {
      //rescinds invitations
         rescindInvitation(families);
         return repeater;
      }
      else if(pick==1)
      {
      //records responses
         recordResponse(families);
         return repeater;
      }
      else if(pick==2)
      {
      //gives a family report
         famReport(families,hostFamily);
         return repeater;
      }
      else if(pick==3)
      {
      //gives a caterer report
         catererReport(families,hostFamily);
         return repeater;
      }
      else
      {
      //exits the menu
         repeater=false;
         return repeater;
      }
   }
/**
This method displays the host family's name, congratulates them and exits the program
@return nothing, this exits the program*/
   public static void exitMenu()
   {
      JOptionPane.showMessageDialog(null,"Congratulations to the "+Family.getHostName()+" family on the wedding day!");
   }
/**
This method creates the repeater value for use in making the menu screen repeat
@return the repeater boolean for the menu*/   
   public static boolean initRepeater()
   {return true;}
}