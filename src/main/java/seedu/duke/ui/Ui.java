package seedu.duke.ui;

import seedu.duke.model.Stock;
import seedu.duke.model.Transaction;
import seedu.duke.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String dividerLine = "____________________________________________________________";
    private final String logo = "__________                              ___________                  .___\n"
            + "\\______   \\_____  ______   ___________  \\__    ___/___________     __| _/____\n"
            + " |     ___/\\__  \\ \\____ \\_/ __ \\_  __ \\   |    |  \\_  __ \\__  \\   / __ |/ __ \\\n"
            + " |    |     / __ \\|  |_> >  ___/|  | \\/   |    |   |  | \\// __ \\_/ /_/ \\  ___/\n"
            + " |____|    (____  /   __/ \\___  >__|      |____|   |__|  (____  /\\____ |\\___  >\n"
            + "                \\/|__|        \\/                              \\/      \\/    \\/";

    public void greetUser() {
        print(logo);
        printWithDivider("Welcome to the Command Line Paper Trading App!");
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printWithDivider(String... messages) {
        print(dividerLine);
        for (String message: messages) {
            print(message);
        }
        print(dividerLine);
    }

    public String getUserInput() {
        print("What would you like to do today?");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput;
    }

    public void view(ArrayList<Stock> stocks) {
        print(dividerLine);
        if (stocks.size() == 0) {
            print("You currently have an empty portfolio. Try buying a stock!");
        }
        for (int i = 0; i < stocks.size(); i++) {
            print((i + 1) + ". " + stocks.get(i).toString());
            double totalCost = 0;
            double totalAsset = stocks.get(i).getLatestPrice() * stocks.get(i).getTotalQuantity();
            for (Transaction t: stocks.get(i).getTransactions()) {
                print("\t" + t.toString());
                totalCost += t.getUnitPrice() * t.getQuantity();
            }
            print("Total revenue = $" + Parser.parsePrice(totalAsset));
            print("Total cost = $" + Parser.parsePrice(totalCost));

            if (totalAsset != 0) {
                double earnings = Parser.parsePrice(totalAsset) - Parser.parsePrice(totalCost);
                print("Total Profit/Loss = $" + earnings);
            } else {
                print("Total Profit/Loss = error!");
            }
        }
        print(dividerLine);
    }

    public void printStocks(ArrayList<Stock> stocks) {
        String[] stockNames = new String[stocks.size()];
        for (int i = 0; i < stocks.size(); i++) {
            stockNames[i] = (i + 1) + ". " + stocks.get(i).toString();
        }
        printWithDivider(stockNames);
    }

}
