package Services.Implementations;

import Persistence.Entities.Sub;
import Persistence.Entities.User;
import Persistence.Repositories.UserRepository;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private Validator validator;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
        encoder = new BCryptPasswordEncoder(16);
    }

    /**
     * Returns all users in database
     *
     * @return List<User>
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns Default User "Anon" that enables
     * creating of posts and replies without being
     * logged in
     *
     * @return User
     */
    public User getAnon() {
        Optional<User> anon = userRepository.findById(1L);
        return anon.get();
    }

    /**
     * Returns User object containing email if it exists
     *
     * @param email String email of particular User
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Returns User containing username if it exists
     *
     * @param userName String username of particular User
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    @Override
    public User getUserObjectByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }
    /**
     * Adds new User to the database if userName or email is not
     * already present in another User object in database
     * If either username or email is taken the BindingResult object
     * will be updated with rejectValue
     *
     * @param user   User object to be added to database
     * @param result BindingResult object
     */
    public void addNewUser(User user, BindingResult result) {
        Optional<User> userName = userRepository.findByUserName(user.getUserName());
        Optional<User> email = userRepository.findByEmail(user.getEmail());

        user.setPassword(encoder.encode(user.getPassword()));

        if (userName.isPresent()) result.rejectValue("userName", "error.duplicate", "Username taken");
        if (email.isPresent()) result.rejectValue("email", "error.duplicate", "Email in use");

        if (!result.hasErrors()) userRepository.save(user);
    }

    public void addDefaultUser(User user) {
        Optional<User> userName = userRepository.findByUserName(user.getUserName());
        Optional<User> email = userRepository.findByEmail(user.getEmail());
        System.out.println("username: " + user.getUserName());
        System.out.println("user password: " + user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    /**
     * Deletes User related to provided ID from database if it exists
     *
     * @param userId Long ID User identifier
     */
    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void editUserName(User user) {
        Optional<User> usernameExists = userRepository.findByUserName(user.getUserName());
        if (usernameExists.isPresent()) throw new DuplicateKeyException("Username taken");
        userRepository.save(user);
    }

    @Override
    public User editRealName(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editPassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void editEmail(User user) {
        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
        if (userEmail.isPresent()) throw new DuplicateKeyException("Email taken");
        userRepository.save(user);
    }

    @Override
    public User editAvatar(User user) {
        return userRepository.save(user);
    }

    /**
     * Checks if username provided in UI matches a User in database
     * If User exists, checks if password provided in UI matches password
     * in database
     * Returns User if password matches
     *
     * @param user User provided from UI
     * @return User
     */
    @Override
    public User loginUser(User user) {
        Optional<User> exists = getUserByUserName(user.getUserName());

        if (exists.isPresent()) {
            if (encoder.matches(user.getPassword(), exists.get().getPassword()))
                return exists.get();
        }
        return null;
    }

    public User addSub(User user, Sub sub){
        List<Sub> subs = user.getSubs();
        subs.add(sub);
        user.setSubs(subs);
        return userRepository.save(user);
    }

    public User removeSub(User user, Sub sub){
        List<Sub> subs = user.getSubs();
        subs.remove(sub);
        user.setSubs(subs);
        return userRepository.save(user);
    }
}
