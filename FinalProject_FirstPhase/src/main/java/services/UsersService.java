package services;

import data.dao.HibernateUtils;
import data.enums.Role;
import data.enums.UserStatus;
import data.dao.ServiceDao;
import data.dao.UserDao;
import data.entities.Service;
import data.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.Validation;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UsersService {

    private Scanner scanner = new Scanner(System.in);
    private Validation validation = new Validation();
    private UserDao userDao = new UserDao();

    public void addUser() {
        System.out.println("please enter role:");
        String role = scanner.nextLine().trim();
        while (!validation.isValidRole(role) || Role.valueOf(role).equals(Role.MANAGER)) {
            System.out.println("please enter valid role!");
            role = scanner.nextLine().trim();
        }
        System.out.println("please enter name:");
        String name = scanner.nextLine().trim();
        System.out.println("please enter family:");
        String family = scanner.nextLine().trim();
        System.out.println("please enter email:");
        String email = scanner.nextLine().trim();
        while (!validation.isValidEmail(email)) {
            System.out.println("please enter valid email!");
            email = scanner.nextLine().trim();
        }
        if (isExistsUser(email)) {
            System.out.println("there is a user with this email!");
            return;
        }
        System.out.println("please enter password:");
        String password = scanner.nextLine().trim();
        while (!validation.isValidPassword(password)) {
            System.out.println("please enter valid password!");
            password = scanner.nextLine().trim();
        }
        User user = new User();
        user.setRole(Role.valueOf(role));
        user.setName(name);
        user.setFamily(family);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(UserStatus.NEW);
        userDao.save(user);
    }

    public void changePassword() {
        System.out.println("please enter user email:");
        String email = scanner.nextLine().trim();
        if (isExistsUser(email)) {
            User specifiedUser = userDao.load(email);
            System.out.println("please enter new password:");
            String newPassword = scanner.nextLine().trim();
            while (!validation.isValidPassword(newPassword)) {
                System.out.println("please enter valid password!");
                newPassword = scanner.nextLine().trim();
            }
            specifiedUser.setPassword(newPassword);
            userDao.update(specifiedUser);
            System.out.println("password changed successfully");
        } else {
            System.out.println("user not found!");
        }
    }

    public boolean isExistsUser(String email) {
        Session session = HibernateUtils.getSession();
        Query query = session.getNamedQuery("searchUserByEmail");
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        session.close();
        if (users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void searchUser() {
        System.out.println("please enter role:");
        String role = scanner.nextLine().trim();
        while (role.length() > 0 && !validation.isValidRole(role)) {
            System.out.println("please enter valid role!");
            role = scanner.nextLine().trim();
        }
        System.out.println("please enter name:");
        String name = scanner.nextLine().trim();
        System.out.println("please enter family:");
        String family = scanner.nextLine().trim();
        System.out.println("please enter email:");
        String email = scanner.nextLine().trim();
        while (email.length() > 0 && !validation.isValidEmail(email)) {
            System.out.println("please enter valid email!");
            email = scanner.nextLine().trim();
        }
        String serviceId = "";
        if (Role.valueOf(role).equals(Role.EXPERT)) {
            ServiceDao serviceDao = new ServiceDao();
            List<Service> services = serviceDao.getServiceList();
            services.stream().forEach(service -> System.out.println(service));
            System.out.println("please enter service of expert id:");//hoze takhasosi
            serviceId = scanner.nextLine().trim();
            while (serviceId.length() > 0) {
                if (validation.isValidNumber(serviceId)) {
                    int id = Integer.parseInt(serviceId);
                    if (services.stream().anyMatch(service -> service.getId() == id))
                        break;
                }
                System.out.println("please enter valid service id!");
                serviceId = scanner.nextLine().trim();
            }
        }
        Session session = HibernateUtils.getSession();
        Criteria criteria = session.createCriteria(User.class);
        if (!"".equals(role)) {
            criteria.add(Restrictions.eq("role", Role.valueOf(role)));
        }
        if (!"".equals(name)) {
            criteria.add(Restrictions.eq("name", name));
        }
        if (!"".equals(family)) {
            criteria.add(Restrictions.eq("family", family));
        }
        if (!"".equals(email)) {
            criteria.add(Restrictions.eq("email", email));
        }
        List<User> users = criteria.list();
        session.close();
        if (!"".equals(serviceId)) {
            int id = Integer.parseInt(serviceId);
            users = users.stream().filter(user -> user.getServices().stream().filter(service -> service.getId() == id)
                    .collect(Collectors.toList()).size() > 0).collect(Collectors.toList());
        }
        users.stream().forEach(user -> System.out.println(user));
    }

}
