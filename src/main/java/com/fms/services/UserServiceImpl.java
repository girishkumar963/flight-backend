package com.fms.services;

import com.fms.dtos.Booking;
import com.fms.dtos.User;
import com.fms.exceptions.AdminUserCannotBeDeletedException;
import com.fms.exceptions.BookingAlreadyPresentException;
import com.fms.exceptions.UserIdAlreadyExistException;
import com.fms.exceptions.UserIdNotFoundException;
import com.fms.daos.BookingDao;
import com.fms.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final String STR="User with id ";

    @Autowired
    UserDao userDao;

    @Autowired
    BookingDao bookingDao;
    
    @Override
    public User addUser(User newUser)
    {
        Optional<User> findUserByEmail=userDao.findByUserEmail(newUser.getUserEmail());
        if(findUserByEmail.isPresent())
        {
            throw new UserIdAlreadyExistException("User with email :" + newUser.getUserEmail() + " already exist.");
        }
//        List<Booking> bookings= newUser.getBookings();
//        bookings.forEach(booking->{
//        	bookingDao.save(booking);
//        });
        userDao.save(newUser);
        return newUser;
    }

    @Override
    public User updateUser(User updateUser)
    {
        Optional<User> findUserById = userDao.findById(updateUser.getUserId());
        if(findUserById.isPresent())
        {
            userDao.save(updateUser);
            return updateUser;
        }
        else
        {
            throw new UserIdNotFoundException(STR+updateUser.getUserId()+" doesnot exist so cannot update the user.");
        }
    }

    @Override
    public void deleteUser(BigInteger userId)
    {
        Optional<User> findUserById = userDao.findById(userId);
        if(this.viewUser(userId).getUserType().equalsIgnoreCase("admin"))
        {
            throw new AdminUserCannotBeDeletedException(STR+userId+" is an admin so cannot delete.");
        }
        else
        {
            if(findUserById.isPresent())
            {
                userDao.deleteById(userId);
            }
            else
            {
                throw new UserIdNotFoundException(STR+userId+" doesnot exist so cannot delete the user.");
            }
        }
    }

    @Override
    public User viewUser(BigInteger userId)
    {
        Optional<User> findUserById = userDao.findById(userId);
        if(findUserById.isPresent())
        {
            return findUserById.get();
        }
        else
        {
            throw new UserIdNotFoundException(STR+userId+" doesnot exist so cannot show the user details.");
        }
    }

    @Override
    public List<User> viewUser()
    {
        return userDao.findAll();
    }

    @Override
    public void validateUser(User users)
    {
        Optional<User> findUserByEmail=userDao.findByUserEmail(users.getUserEmail());
        if(findUserByEmail.isPresent())
        {
            throw new UserIdAlreadyExistException(STR + users.getUserId() + " already exist.");
        }
    }
}
