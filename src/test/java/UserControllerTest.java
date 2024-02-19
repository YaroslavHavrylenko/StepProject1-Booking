import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;
import user.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {
  UserController userController;


  @BeforeEach
  void setUp() {
    userController = new UserController();

    User emin = new User(1, "Emin", "12345" );
    User shafa = new User(2, "Shafa", "12345" );
    userController.getAll().add(emin);
    userController.getAll().add(shafa);

  }


  @Test
  void getAll() {
    int expected = 2;
    int actual = userController.getAll().size();

    assertEquals(expected, actual);

  }

  @Test
  void getByID() {
    User expected = userController.getAll().get(0);
    User actual = userController.getById(1);

    assertEquals(expected, actual);

  }

  @Test
  void delete() {
    boolean expected = true;
    boolean actual = userController.delete(0);

    assertEquals(expected, actual);

  }

  @Test
  void register() {
    int initialSize = userController.getAll().size();
    userController.register("Emin", "555");
    int finalSize = userController.getAll().size();

    boolean expected = true;
    boolean actual = initialSize<finalSize;

    assertEquals(expected, actual);
  }

}