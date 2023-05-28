package application;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private TodoDao database;

    public UserInterface(Scanner scanner, TodoDao database) {
        this.scanner = scanner;
        this.database = database;
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println("");
            System.out.println("Enter command:");
            System.out.println("1) list");
            System.out.println("2) add");
            System.out.println("3) mark as done");
            System.out.println("4) remove");
            System.out.println("x) quit");

            System.out.print("> ");
            String command = this.scanner.nextLine();
            if (command.equals("x")) {
                break;
            }
            if (command.equals("1")) {
                System.out.println("Listing the database contents");
                for (Todo task:this.database.list()) {
                    System.out.println(task);
                }
            }
            if (command.equals("2")) {
                System.out.println("Enter the name");
                String name = scanner.nextLine();
                System.out.println("Enter the description");
                String description = scanner.nextLine();
                if (this.database.list().isEmpty()) {
                    this.database.add(new Todo(1,name,description,false));
                    continue;
                }
                int id = this.database.list().size();
                this.database.add(new Todo(id,name,description,false));
            }
            if (command.equals("3")) {
                System.out.println("Which todo should be marked as done (give the id)?");
                int id = Integer.valueOf(scanner.nextLine());
                this.database.markAsDone(id);
            }
            if (command.equals("4")) {
                System.out.println("Which todo should be removed (give the id)?");
                int id = Integer.valueOf(scanner.nextLine());
                this.database.remove(id);
            }
            if (command.equals("x")) {
                break;
            }
        }
        System.out.println("Thank you!");
    }

}
