package ua.epam.springProject.service;

import ua.epam.springProject.StaticDB;
import ua.epam.springProject.domain.Event;
import ua.epam.springProject.domain.Ticket;
import ua.epam.springProject.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public class BookingServiseImpl implements BookingService {

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        StaticDB.purchasedTicketsTable.addAll(tickets);

    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return null;
    }
}
