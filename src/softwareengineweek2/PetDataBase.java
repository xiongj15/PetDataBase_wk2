//Jeffrey Xiong
//11/3/2019
//Create a petDatabase that adds,store,removes,pets.
package softwareengineweek2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

public class PetDataBase {

    //Create Pet Class to store new pet objects
    public static class Pet {
        //Properties

        String name;
        int age;

        public Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            this.name = name;
            return name;
        }

        public int getAge() {
            this.age = age;
            return age;
        }

        public void setName(String newName) {
            this.name = newName;

        }

        public void setAge(int newAge) {
            this.age = newAge;

        }
    }

    // Public Variables
    public static Scanner s = new Scanner(System.in);
    public static Pet[] Petarray = new Pet[25]; //Create petArray to store new created pet class ojects
    public static int petcount = 0; //Keep track of the amount of objects inside the list

    // Methods
    public static int getUserChoice() {
        System.out.println("");
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name ");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
        System.out.print("Your choice: ");
        int userChoice = s.nextInt();
        return userChoice;
    }

    public static void addPets() {
        s.nextLine();
        int count = 0;
        System.out.println("Enter 'done' to exit");
        for (;;) {
            String[] temp;
            System.out.print("add pet (name, age): ");
            String input = s.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            temp = input.split(" ");
            int len = temp.length;
            if (len != 2) {
                System.out.println("Error in information, try again");
                continue;
            }
            String name = temp[0];
            int age = parseInt(temp[1]);
            if (name.equalsIgnoreCase("done")) {
                s.nextLine();
                break;
            }
            if (petcount == 5) {
                System.out.println("DataBase Full, exiting Add function");
                break;
            }
            //   int age = s.nextInt();
            if (age <= 0 || age > 20) {
                System.out.println("Invalid age, try again");
                continue;
            }
            Pet newPet = new Pet(name, age); //Create new pet object
            Petarray[petcount] = newPet; //Save new created object into list
            petcount++; //increse the list
            count++;
        }
        System.out.println(count + " pets added.");
    }

    public static void showAllPets() {
        System.out.printf("%-4s %-10s %s ", "ID", "NAME", "AGE");
        System.out.println("");
        for (int count = 0; count < petcount; count++) {
            System.out.printf("%-4d %-10s %d ", count, Petarray[count].getName(), Petarray[count].getAge());
            System.out.println("");
        }
        System.out.println(petcount + " rows in set");
    }

    public static void updatePets() { //Search for pet to update, searches using index number
        for (int x = 0; x == 0;) {
            System.out.print("Enter Pet ID : ");
            int id = s.nextInt();
            if (id < 0 || id >= petcount) {
                System.out.println("ID does not exist, try again");
                continue;
            }
            for (int count = 0; count < petcount; count++) {
                if (id == count) {
                    System.out.print("Enter Pet new Name and new Age : ");
                    String newName = s.next();
                    int newAge = s.nextInt();
                    Petarray[count].setName(newName);
                    Petarray[count].setAge(newAge);
                    System.out.println("Pet " + count + " was updated.");
                    x = 1;
                }
            }
        }
    }

    public static void removePets() { //Search for pet to delete, searches using index number
        s.nextLine();
        for (int x = 0; x == 0;) {
            System.out.print("Enter Pet ID : ");
            int id = s.nextInt();
            if (id < 0 || id >= petcount) {
                System.out.println("ID does not exist, try again");
                continue;
            }
            for (int count = 0; count < petcount; count++) {
                if (id == count) {
                    Petarray[count] = Petarray[count + 1]; //take pet infront of it and replace it
                    petcount--; //Decrease the pet counter
                    System.out.println("Pet " + count + " has been removed");
                    break;
                }
            }
            x = 1;
        }
    }

    public static void nameSearch() { //Search for name using, name and index number
        System.out.print("Enter Pet name : ");
        String name = s.next();
        for (int count = 0; count < petcount; count++) {
            if (name.equals(Petarray[count].getName())) {
                System.out.printf("%-4d %-10s %d ", count, Petarray[count].getName(), Petarray[count].getAge());
                System.out.println("");
            }
        }
    }

    public static void ageSearch() { //Search for age using, age and index number
        System.out.print("Enter Pet Age : ");
        int age = s.nextInt();
        for (int count = 0; count < petcount; count++) {
            if (age == Petarray[count].getAge()) {
                System.out.printf("%-4d %-10s %d ", count, Petarray[count].getName(), Petarray[count].getAge());
                System.out.println("");
            }
        }
    }

    public static void loadFile() throws FileNotFoundException { //load File for previous data
        File Datafile = new File("PetData.txt");
        Scanner f = new Scanner(Datafile);
        while (f.hasNextLine()) {
            String[] temp;
            String input = f.nextLine();
            temp = input.split(" ");
            String name = temp[0];
            int age = parseInt(temp[1]);
            Pet newPet = new Pet(name, age);
            Petarray[petcount] = newPet;
            petcount++;
        }
    }

    public static void saveFile() throws IOException { //Save data on exit
        FileWriter DataWriter = new FileWriter("PetData.txt");
        for (int count = 0; count < petcount; count++) {
            String name = Petarray[count].getName();
            int age = Petarray[count].getAge();
            DataWriter.write(name + " " + age + "\n");
        }
        DataWriter.close();
    }

    public static boolean checkFileExists() { //Check to see if there is a file to load
        File dataFile = new File("PetData.txt");
        boolean fileExists = dataFile.exists();
        if (fileExists == true) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        if (checkFileExists() == true) {
            System.out.println("There exists previous data, loading data");
            loadFile();
        }
        Boolean mainon = true;
        System.out.println("Begin of Program");
        for (; mainon == true;) {
            switch (getUserChoice()) {
                case 1:
                    showAllPets();
                    break;
                case 2:
                    addPets();
                    break;
                case 3:
                    showAllPets();
                    updatePets();
                    break;
                case 4:
                    showAllPets();
                    removePets();
                    break;
                case 5:
                    nameSearch();
                    break;
                case 6:
                    ageSearch();
                    break;
                case 7:
                    mainon = false;
                    System.out.println("Saving Database, Ending program, goodbye");
                    saveFile();
                    break;
                default:
                    break;
            }
        }
    }
}
