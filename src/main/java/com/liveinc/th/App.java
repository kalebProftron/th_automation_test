package com.liveinc.th;

import java.util.Timer;

public class App {
    public static void main( String[] args )
    {
        Login loginTest = new Login();
        // RegisterApplicant appTest = new RegisterApplicant();
        // loginTest.runTest();

      Timer t=new Timer();
      t.scheduleAtFixedRate(loginTest, 0, 3600000);
    }
}
