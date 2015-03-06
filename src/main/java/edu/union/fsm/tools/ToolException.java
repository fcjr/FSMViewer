package edu.union.fsm.tool;

public class ToolException extends Exception {

      /**
       * Constructor that accepts a message.
       * @param  message the message
       */
      public ToolException(String message)
      {
         super(message);
      }

      /**
       * Constuctor takes standard exception constructs a new ToolException
       * with the other exceptions message
       * @param  ex the original exception
       */
      public ToolException(Exception ex){
          String message = ex.getMessage();
          super(message);
      }
 }
