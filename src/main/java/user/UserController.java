package user;

import java.util.List;

public class UserController {
    UserService userService= new UserService();


    public List<User> getAll() {
        return userService.getAll();
    }

    public User getById(int id) {
        return userService.getById(id).get();
    }

    public boolean delete(int id) {
        return userService.delete(id);
    }

    public boolean save() {
        return userService.save();
    }

    public void register(String username, String pass) {
        userService.register(username, pass);
    }

    public void fillList() {
        userService.fillList();
    }
}
