package co.com.poli.usersservice.service;

import co.com.poli.usersservice.clientFeign.BookingClient;
import co.com.poli.usersservice.commons.Functions;
import co.com.poli.usersservice.persistance.entity.User;
import co.com.poli.usersservice.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BookingClient bookingClient;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String id) {
        boolean wasDeleted = false;
        List<?> bookingList = Functions.convertObjectToList(bookingClient.findByUserID(id).getData());
        if(bookingList == null || bookingList.isEmpty()){
            userRepository.deleteById(Long.valueOf(id));
            wasDeleted = true;
        }
        return wasDeleted;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String id) {
        return userRepository.findById(Long.valueOf(id)).orElse(null);
    }

}
