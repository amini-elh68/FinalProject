package services;

import data.dao.HibernateUtils;
import data.enums.Role;
import data.dao.ServiceDao;
import data.dao.UserDao;
import data.entities.Service;
import data.entities.SubService;
import data.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.Validation;

import java.util.List;
import java.util.Scanner;

public class HomeService {

    private Scanner scanner = new Scanner(System.in);//ورودی serviceDto service
    private ServiceDao serviceDao = new ServiceDao();
    private Validation validation = new Validation();
    private UserDao userDao = new UserDao();

    public void addService() {
        System.out.println("please enter service name:");
        String serviceName = scanner.nextLine().trim();
        Session session = HibernateUtils.getSession();
        Criteria criteria = session.createCriteria(Service.class);
        criteria.add(Restrictions.eq("serviceName", serviceName));
        List<Service> list = criteria.list();
        session.close();
        if (list.size() > 0) {
            System.out.println("service name is duplicate");
            return;
        }
        Service service = new Service();
        service.setServiceName(serviceName);
        serviceDao.save(service);
    }

    public void addSubService() {
        List<Service> services = serviceDao.getServiceList();
        services.forEach(System.out::println);
        System.out.println("please enter service id for add subService:");
        String serviceId;
        while (true) {
            serviceId = scanner.nextLine().trim();
            if (validation.isValidNumber(serviceId)) {
                int id = Integer.parseInt(serviceId);
                if (services.stream().anyMatch(service -> service.getId() == id)) {
                    break;
                }
            }
            System.out.println("please enter valid service id!");
        }
        Service service = serviceDao.load(Integer.parseInt(serviceId));
        System.out.println("please enter subService name:");
        String subServiceName = scanner.nextLine().trim();
        boolean isExists = service.getSubServices().stream().anyMatch(subService -> subService.getSubServiceName().equals(subServiceName));
        if (isExists) {
            System.out.println("subService name is duplicate!");
            return;
        }
        SubService subService = new SubService();
        subService.setSubServiceName(subServiceName);
        subService.setService(service);
        service.getSubServices().add(subService);
        serviceDao.update(service);
    }

    public void addExpertToService() {
        List<Service> services = serviceDao.getServiceList();
        services.forEach(System.out::println);
        System.out.println("please enter service id:");
        String serviceId;
        while (true) {
            serviceId = scanner.nextLine().trim();
            if (validation.isValidNumber(serviceId)) {
                int id = Integer.parseInt(serviceId);
                if (services.stream().anyMatch(service -> service.getId() == id)) {
                    break;
                }
            }
            System.out.println("please enter valid service id!");
        }
        Service service = serviceDao.load(Integer.parseInt(serviceId));
        System.out.println("please enter user email:");
        String email = scanner.nextLine().trim();
        UsersService usersService = new UsersService();
        if (!usersService.isExistsUser(email)) {
            System.out.println("user does not exists!");
            return;
        }
        User user = userDao.load(email);
        if (user.getRole().equals(Role.CUSTOMER)) {
            System.out.println("user must be expert!");
            return;
        }
        service.getExperts().add(user);
        user.getServices().add(service);
        userDao.update(user);
    }

    public void deleteExpertFromService() {
        System.out.println("please enter user email:");
        String email = scanner.nextLine().trim();
        UsersService usersService = new UsersService();
        if (!usersService.isExistsUser(email)) {
            System.out.println("user does not exists!");
            return;
        }
        User user = userDao.load(email);
        if (user.getRole().equals(Role.CUSTOMER)) {
            System.out.println("user must be expert!");
            return;
        }
        user.getServices().forEach(System.out::println);
        System.out.println("please enter service id to delete:");
        String serviceId;
        while (true) {
            serviceId = scanner.nextLine().trim();
            if (validation.isValidNumber(serviceId)) {
                int id = Integer.parseInt(serviceId);
                if (user.getServices().stream().anyMatch(service -> service.getId() == id)) {
                    break;
                }
            }
            System.out.println("please enter valid service id!");
        }
        Service service = serviceDao.load(Integer.parseInt(serviceId));
        service.getExperts().remove(user);
        user.getServices().remove(service);
        userDao.update(user);
    }

}
