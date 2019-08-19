package ua.epam.springProject;

import org.springframework.context.ApplicationContext;
import ua.epam.springProject.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class App {

    private static ApplicationContext ctx = AppContext.getInstance();

    public static void main(String[] args) {
        App app = (App) ctx.getBean("app");
        app.fillUsersTable();
        app.fillEventsTables();
        app.definePerson();
    }

    private void definePerson() {
        final int ADMIN = 1;
        final int USER = 2;
        System.out.println("Who you are (input number)?");
        System.out.println(ADMIN + " administrator");
        System.out.println(USER + " user");

        try {
            int userInput = new Scanner(System.in).nextInt();
            if (userInput == ADMIN) {
                adminOperation();
            } else if (userInput == USER) {
//                userOperation();
            } else {
                System.out.println("Incorrect input, try again");
                definePerson();
            }
        } catch (Exception e) {
            definePerson();
        }
    }

    private void adminOperation() {
        System.out.println("Choose operation");
        System.out.println("1 Enter event");
        System.out.println("2 View purchased tickets");
        System.out.println("3 View registered users");
        System.out.println("4 View events");
        System.out.println("5 Back to choose mode (user/admin");
        int userInput = new Scanner(System.in).nextInt();
        if (userInput == 1) {
            enterEvent();
            adminOperation();
        } else if (userInput == 2) {
            viewPurchasedTickets();
        } else if (userInput == 3) {
            viewRegisteredUsers();
        }else if (userInput == 4) {
            viewEvents();
        } else if (userInput == 5) {
            definePerson();
        } else {
            System.out.println("Incorrect input, try again");
            adminOperation();
        }
    }

    private void viewEvents() {
        for (Event event : StaticDB.eventsTable) {
            System.out.println(event.toString());
        }
        adminOperation();
    }

    private void viewRegisteredUsers() {
        if (StaticDB.usersTable.isEmpty()) {
            System.out.println("There is no registered user!");
        } else {
            for (User user : StaticDB.usersTable) {
                System.out.println(user.toString());
            }
        }
        adminOperation();
    }

    private void viewPurchasedTickets() {
        if (StaticDB.purchasedTicketsTable.isEmpty()) {
            System.out.println("There is no purchased ticket!");
        } else {
            for (Ticket ticket : StaticDB.purchasedTicketsTable) {
                System.out.println(ticket.toString());
            }
        }
        adminOperation();
    }

    private void enterEvent() {
        System.out.println("Enter name of event which you going to create");
        System.out.println("or press '0' to return to previous menu");
        String userInput = new Scanner(System.in).nextLine();
        if (userInput.equals("0")) {
            adminOperation();
        } else {
            Event event = (Event) ctx.getBean("event");
            event.setName(userInput);
            setEventPrise(event);
            setEventRating(event);
            assignAirDateAndAuditorium(event);
        }
    }

    private void assignAirDateAndAuditorium(Event event) {
        LocalDateTime eventDate = null;
        do {
            LocalDateTime today = LocalDateTime.now();
            System.out.println("Assign the day and auditorium");
            System.out.println("1 - Today");
            System.out.println("2 - Tomorrow");
            System.out.println("3 - After tomorrow");
            System.out.println("4 - At " + today.plusDays(3).getDayOfWeek());
            System.out.println("5 - At " + today.plusDays(4).getDayOfWeek());
            System.out.println("6 - At " + today.plusDays(5).getDayOfWeek());
            System.out.println("7 - At " + today.plusDays(6).getDayOfWeek());

            int userInput = new Scanner(System.in).nextInt();

            if (userInput == 1) {
                eventDate = today;
            } else if (userInput == 2) {
                eventDate = today.plusDays(1);
            } else if (userInput == 3) {
                eventDate = today.plusDays(2);
            } else if (userInput == 4) {
                eventDate = today.plusDays(3);
            } else if (userInput == 5) {
                eventDate = today.plusDays(4);
            } else if (userInput == 6) {
                eventDate = today.plusDays(5);
            } else if (userInput == 7) {
                eventDate = today.plusDays(6);
            } else {
                System.out.println("incorrect input. Try again");
            }
        } while (eventDate == null);
        Auditorium auditorium = chooseAuditorium();
        if (event.addAirDateTime(eventDate, auditorium)) {
            System.out.println("Not allowed to assign to this day");
            assignAirDateAndAuditorium(event);
        }

        System.out.println("Will be this event in another day?");
        System.out.println("1 - Yes, I'm going to choose one another day");
        System.out.println("any key - No, finish");

        String userInput = new Scanner(System.in).nextLine();
        if (userInput.equals("1")){
            assignAirDateAndAuditorium(event);
        }
    }

    private Auditorium chooseAuditorium() {
        Auditorium auditorium = null;
        do {
            System.out.println("Choose auditorium");
            System.out.println("1 " + Auditorium.GREEN_HALL);
            System.out.println("2 " + Auditorium.RED_HALL);
            System.out.println("3 " + Auditorium.VIP_HALL);
            int userInput = new Scanner(System.in).nextInt();
            switch (userInput) {
                case 1:
                    auditorium = StaticDB.auditoriumTable.get(1);
                    break;
                case 2:
                    auditorium = StaticDB.auditoriumTable.get(2);
                    break;
                case 3:
                    auditorium = StaticDB.auditoriumTable.get(3);
                    break;
                default:
                    System.out.println("Incorrect input, try again");
                    break;
            }
        } while (auditorium == null);
        return auditorium;
    }

    private void setEventRating(Event event) {
        System.out.println("Enter the rating of event");
        System.out.println("1 Low");
        System.out.println("2 Mid");
        System.out.println("3 High");
        int rating = new Scanner(System.in).nextInt();
        if (rating == 1) {
            event.setRating(EventRating.LOW);
        } else if (rating == 2) {
            event.setRating(EventRating.MID);
        } else if (rating == 3) {
            event.setRating(EventRating.HIGH);
        } else {
            System.out.println("incorrect input, try again!");
            setEventRating(event);
        }
    }

    private void setEventPrise(Event event) {
        System.out.println("Enter the price of event");
        double eventPrise;
        try {
            eventPrise = new Scanner(System.in).nextDouble();
            event.setBasePrice(eventPrise);
        } catch (Exception e) {
            System.out.println("Incorrect prise. try again!");
            setEventPrise(event);
        }
    }

    private void fillUsersTable() {
        StaticDB.usersTable.add(new User("Yevhenii", "Trokhniuk", "MyEmail.com"));
        StaticDB.usersTable.add(new User("Betmen", "Superheroevich", "betmen@gmail.com"));
        StaticDB.usersTable.add(new User("Vasilii", "Pupkin", "vasilii_pupkin@gmail.com"));
    }

    private void fillEventsTables() {
        buildAuditoriums();
        Event romeoAndJuliet = new Event("Rome & Juliet", 30.5, EventRating.MID);
        romeoAndJuliet.addAirDateTime(LocalDateTime.now(), StaticDB.auditoriumTable.get(1));
        romeoAndJuliet.addAirDateTime(LocalDateTime.now().plusDays(1), StaticDB.auditoriumTable.get(1));
        romeoAndJuliet.addAirDateTime(LocalDateTime.now().plusDays(3), StaticDB.auditoriumTable.get(1));

        Event faust = new Event("Faust", 20.5, EventRating.MID);
        faust.addAirDateTime(LocalDateTime.now(), StaticDB.auditoriumTable.get(2));
        faust.addAirDateTime(LocalDateTime.now().plusDays(1), StaticDB.auditoriumTable.get(2));
        faust.addAirDateTime(LocalDateTime.now().plusDays(3), StaticDB.auditoriumTable.get(2));

        StaticDB.eventsTable.add(romeoAndJuliet);
        StaticDB.eventsTable.add(faust);
    }

    private void buildAuditoriums() {
        Long[] vipSits = new Long[]{1L, 2L, 3L, 4L, 5L};
        StaticDB.auditoriumTable.add(new Auditorium(Auditorium.GREEN_HALL, 20, new HashSet<>(Arrays.asList(vipSits))));

        vipSits = new Long[]{6L, 7L, 8L, 9L, 10L};
        StaticDB.auditoriumTable.add(new Auditorium(Auditorium.RED_HALL, 30, new HashSet<>(Arrays.asList(vipSits))));

        vipSits = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
        StaticDB.auditoriumTable.add(new Auditorium(Auditorium.VIP_HALL, 40, new HashSet<>(Arrays.asList(vipSits))));
    }
}
