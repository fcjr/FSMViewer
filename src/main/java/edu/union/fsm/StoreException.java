package edu.union.fsm;

public class StoreException extends Exception{
      //Parameterless Constructor
      public StoreException() {}

      //Constructor that accepts a message
      public StoreException(String message)
      {
         super(message);
      }
 }
