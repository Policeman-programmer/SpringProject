package ua.epam.springProject.service;

import ua.epam.springProject.StaticDB;
import ua.epam.springProject.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class UsersAdministrator implements UserService {

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        for (User user : StaticDB.usersTable) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(@Nonnull User user) {
        StaticDB.usersTable.add(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        StaticDB.usersTable.remove(user);
    }

    @Nullable
    @Override
    public User getById(@Nonnull Long id) {
        for (User user : StaticDB.usersTable) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return StaticDB.usersTable;
    }
}
