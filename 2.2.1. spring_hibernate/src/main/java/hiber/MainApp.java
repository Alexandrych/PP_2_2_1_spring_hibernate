package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      User user5 = new User("User5", "Lastname5", "user5@mail.ru");

      user1.setCar(new Car("Lada", 5));
      user2.setCar(new Car("BMW", 6));
      user3.setCar(new Car("BMW", 7));
      user4.setCar(new Car("BMW", 7));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("*****" + "\n");

      Car testCar1 = new Car("Lada", 5);
      Car testCar2 = new Car("Lada", 5000);

      System.out.println(userService.getUserByCar(testCar1) + "\n");
      System.out.println(userService.getUserByCar(testCar2) + "\n");
      System.out.println(userService.getUserByCar("BMW", 6) + "\n");
      System.out.println(userService.getUserByCar("BMW", 7));

      context.close();
   }
}
