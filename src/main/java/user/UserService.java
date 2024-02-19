package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
  UserDao userDao = new UserDao();


  public List<User> getAll() {
    return userDao.getAll();
  }

  public Optional<User> getById(int id) {
    if (userDao.getById(id).isPresent()) {
      return userDao.getById(id);
    } else {
      return Optional.empty();
    }
  }

  public boolean delete(int id) {
    return userDao.delete(id);
  }

  public boolean save() {
    return userDao.save();
  }

  public void register(String username, String pass) {
    userDao.users.add(new User(userDao.getAll().size()+1, username, pass));
  }

  public void fillList() {
    File file = new File("src/main/java/database/user.txt");
    List<User> userList = new ArrayList<>();

    try {
      List<String> lines = new BufferedReader(new FileReader(file)).lines().collect(Collectors.toList());

      for (String line : lines) {
        String[] split1 = line.split(" ");
        userList.add(new User(Integer.parseInt(split1[0].trim()),split1[1].trim(),split1[2].trim()));
      }

      userDao.users.addAll(userList);

    } catch (Exception e) {
      System.out.printf("Database files are going to be created %s\n", file);
    }

  }

}