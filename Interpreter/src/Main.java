////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
import view.UserInterface;


////////////////////////
// PROGRAM CONTROLLER //
////////////////////////
public class Main {
    public static void main(String[] args) {

        // Application startup
        UserInterface app = new UserInterface();

        // Run application until user triggers exit status
        int status = 0;
        do {

            // Get application status
            status = app.menuScreen();

        }while (status != 0);
    }
}