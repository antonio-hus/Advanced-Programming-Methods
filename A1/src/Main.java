/**

 A1:
 Intr-o parcare exista masini, motociclete
 si biciclete. Sa se afiseze toate vehiculele
 de culoare rosie.

**/

import Repository.*;
import Controller.*;
import View.*;

public class Main {
    public static void main(String[] args) {

        // Application startup
        UserInterface app = new UserInterface();

        // Run application until user triggers exit status
        int status = 0;
        do {

            // Get application status
            status = app.parkingLotOptions();

        }while (status != 0);
    }

}