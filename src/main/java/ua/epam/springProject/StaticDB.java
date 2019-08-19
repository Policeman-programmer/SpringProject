package ua.epam.springProject;

import ua.epam.springProject.domain.Auditorium;
import ua.epam.springProject.domain.Event;
import ua.epam.springProject.domain.Ticket;
import ua.epam.springProject.domain.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StaticDB {
    public static HashSet<User> usersTable = new HashSet<>();
    public static List<Event> eventsTable = new ArrayList<>();
    public static ArrayList<Auditorium> auditoriumTable = new ArrayList<>();
    public static HashSet<Ticket> purchasedTicketsTable = new HashSet<>();

}
