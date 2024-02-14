package view;

import mapping.dtos.ToyDTO;
import model.ToyType;
import services.ToyService;
import services.ToyServicelmpl;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ToyService toyService = new ToyServicelmpl();


        boolean exit = false;
        while (!exit) {
            System.out.println("\nToy Store Management System");
            System.out.println("1. Add a new toy");
            System.out.println("2. Show toys by type");
            System.out.println("3. Show total count of toys");
            System.out.println("4. Show total value of toys");
            System.out.println("5. Decrease quantity of a toy");
            System.out.println("6. Increase quantity of a toy");
            System.out.println("7. Show most popular toy type");
            System.out.println("8. Show least popular toy type");
            System.out.println("9. Filter toys by price");
            System.out.println("10. Sort toys by quantity");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\nEnter toy details:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Type (Female/Male/Unisex): ");
                        ToyType type = ToyType.fromName(scanner.nextLine());
                        System.out.print("Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Quantity: ");
                        int quantity = scanner.nextInt();
                        ToyDTO newToy = new ToyDTO(name, type, price, quantity);
                        toyService.addToy(newToy);
                        break;
                    case 2:
                        System.out.println("\nToys by type:");
                        System.out.println(toyService.showByType());
                        break;
                    case 3:
                        System.out.println("\nTotal count of toys: " + toyService.showTotalCountToy());
                        break;
                    case 4:
                        System.out.println("\nTotal value of toys: $" + toyService.showTotalValueToys());
                        break;
                    case 5:
                        System.out.print("\nEnter toy name to decrease quantity: ");
                        String decreaseName = scanner.nextLine();
                        System.out.print("Enter quantity to decrease: ");
                        int decreaseQuantity = scanner.nextInt();
                        toyService.decreaseQuantityToy(decreaseName, decreaseQuantity);
                        break;
                    case 6:
                        System.out.print("\nEnter toy name to increase quantity: ");
                        String increaseName = scanner.nextLine();
                        System.out.print("Enter quantity to increase: ");
                        int increaseQuantity = scanner.nextInt();
                        toyService.increaseQuantityToy(increaseName, increaseQuantity);
                        break;
                    case 7:
                        System.out.println("\nMost popular toy type: " + toyService.maxToy());
                        break;
                    case 8:
                        System.out.println("\nLeast popular toy type: " + toyService.minToy());
                        break;
                    case 9:
                        System.out.print("\nEnter minimum price: ");
                        double minPrice = scanner.nextDouble();
                        toyService.filterToysByPrice(minPrice).forEach(System.out::println);
                        break;
                    case 10:
                        System.out.println("\nToys sorted by quantity:");
                        toyService.sortToysByQuantity().forEach(System.out::println);
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please enter a valid option.");
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Exiting Toy Store Management System. Goodbye!");
    }
    }




