/**
Name: Nicholas Boesel
Date: 2/20/2016
Course/Section: IT 206.001
Assignment: Programming Assignment 4

This data definition class contains all of the variables needed for a family in the wedding simulation.
Each family has it's own name, number of family members, number of chicken and veggie meals, and unique invitation number, as well as an RSVP boolean.
it also uses a default constructor and several add methods for the meals as they are processed in the implementation.
This class is meant to be used and passed through the implementation where it will be created, entered as input, altered, and displayed by the implementation methods.*/
public class Family
{
   private String name;
   private int members;
   private int chickenMeals;
   private int vegMeals;
   private boolean responded;
   private static int invitation;
   private static String hostName;
   private int inviteNum;
   
   //default constructor
   public Family()
   {
      invitation++;
      this.name=null;
      this.members=0;
      this.chickenMeals=0;
      this.vegMeals=0;
      this.responded=false;
      this.inviteNum=invitation;
      hostName=null;
   }
   //Sets the family's name to that of the input value
   //@param famRSVP is what name in this instance is set to
   public void setName(String famName)
   {
         this.name=famName;
   }
   //returns the family's name
   public String getName(){return this.name;}
   //sets the family's RSVP status to the input
   //@param famRSVP is what responded in this instance is set to
   public void setRSVP(boolean famRSVP)
   {
      this.responded=famRSVP;
   }
   //returns the family's RSVP status in this instance.
   public boolean getRSVP(){return this.responded;}
   
   //gets the input value for family member and sets the number of family members to the input if valid
   //@param famMems is what members in this instance is set to
   public boolean setMems(int famMems)
   {
      if(famMems<=0||famMems>250)
      {
         return false;
      }
      else
      {  
         this.members=famMems;
         return true;
      }
   }
   //returns the number of family members in this instance.
   public int getMems(){return this.members;}
   
   //gets the input value for chicken and sets the number of chicken meals to the input if valid
   //@param famChicken is what the chicken meals in this instance gets set to.
   public boolean setChicken(int famChicken)
   {
      if(famChicken+this.vegMeals>this.members)
      {
         return false;
      }
      else
      {  
         this.chickenMeals=famChicken;
         return true;
      }
   }
   //returns the number of chicken meals in this instance.
   public int getChicken(){return this.chickenMeals;}
   
   //adds a chicken meal to the family in this instance.
   public void addChicken(){this.chickenMeals++;}
   //gets the input value for vegetable meals and sets the number of veggie meals to the input if valid 
   //@param famVeg is what the vegetable meals in this instance gets set to. 
   public boolean setVeg(int famVeg)
   {
      if(famVeg+this.chickenMeals>this.members)
      {
         return false;
      }
      else
      {  
         this.vegMeals=famVeg;
         return true;
      }
   }
   //returns the number of veggie meals in this instance.
   public int getVeg(){return this.vegMeals;}
   
   //adds a vegetarian meal to the family in this instance.
   public void addVeg(){this.vegMeals++;}
   
   //puts all of the data of an instance into a string to be read 
   public String toString()
   {
    return"Family name: "+ this.name+"\nNumber of attendees: "+ this.members+"\nNumber of chicken meals: "+this.chickenMeals+"\nNumber of vegetarian meals: "+this.vegMeals+"\nHave they responded? "+this.responded+"\ninvitation number: "+this.inviteNum;
   }
   
   //gets the invitation's number
   public int getInviteNum()
   {return inviteNum;}
   
   //gets the name of the host family
   public static String getHostName(){return hostName;}
   
   //sets the host family's name to the input parameter
   //@param famName is the name that you're changing the host family to
   public static void setHostName(String famName){hostName=famName;}
   
   //decrements the number of invites
   public static void removeInvite(){invitation--;}
}