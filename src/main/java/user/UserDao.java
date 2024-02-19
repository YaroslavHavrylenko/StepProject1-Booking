package user;

import dao.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDao implements DAO<User> {
  public List<User> users = new ArrayList<>();


  @Override
  public List<User> getAll() {
    return users;
  }

  @Override
  public Optional<User> getById(int id) {
    try{
      return users.stream().filter(user -> id == user.getId()).findFirst();
    }
    catch(Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public boolean delete(int id) {
    if (id > users.size() || id < 0) return false;
    users.removeIf(user -> id == user.getId());
    return true;
  }

  @Override
  public boolean save() {
    File file = new File("src/main/java/database/user.txt");

    try {
      new BufferedReader(new FileReader(file)).lines().collect(Collectors.toList());
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));

      for (User user : users) {
        bw.write(user.toString());
        bw.write("\n");
      }

      bw.close();
      return true;

    } catch (Exception e) {

      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (User user : users) {
          bw.write(user.toString());
          bw.write("\n");
        }

        bw.close();
        return true;

      } catch (Exception e2) {
        System.out.println("Something went wrong!");
        return false;
      }
    }
  }

}

