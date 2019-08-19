package ua.epam.springProject.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.springProject.domain.User;

public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable User getUserByEmail(@Nonnull String email);

}
