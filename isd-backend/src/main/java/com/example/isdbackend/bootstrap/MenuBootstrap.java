//package com.example.isdbackend.bootstrap;
//
//import com.example.isdbackend.model.*;
//import com.example.isdbackend.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Random;
//
//@Component
//public class MenuBootstrap implements CommandLineRunner {
//    private final MenuRepository menuRepository;
//    private final ProviderRepository providerRepository;
//    private final MenuTypeRepository menuTypeRepository;
//    private final MenuItemRepository menuItemRepository;
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//    public MenuBootstrap(MenuRepository menuRepository, ProviderRepository providerRepository, MenuTypeRepository menuTypeRepository, MenuItemRepository menuItemRepository, UserRepository userRepository, RoleRepository roleRepository) {
//
//        this.menuRepository = menuRepository;
//        this.providerRepository = providerRepository;
//        this.menuTypeRepository = menuTypeRepository;
//        this.menuItemRepository = menuItemRepository;
//        this.userRepository = userRepository;
//
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//
//        MenuType S1 = new MenuType();
//        S1.setPrice(100f);
//        S1.setType('S');
//        menuTypeRepository.save(S1);
//
//        MenuType M1 = new MenuType();
//        M1.setPrice(50f);
//        M1.setType('M');
//        menuTypeRepository.save(M1);
//
//        MenuType S2 = new MenuType();
//        S2.setPrice(90f);
//        S2.setType('S');
//        menuTypeRepository.save(S2);
//
//        MenuType M2 = new MenuType();
//        M2.setPrice(70f);
//        M2.setType('M');
//        menuTypeRepository.save(M2);
//
//
//
//        MenuType S3 = new MenuType();
//        S3.setPrice(100f);
//        S3.setType('S');
//        menuTypeRepository.save(S3);
//
//        MenuType M3 = new MenuType();
//        M3.setPrice(50f);
//        M3.setType('M');
//        menuTypeRepository.save(M3);
//
//        MenuType S4 = new MenuType();
//        S4.setPrice(120f);
//        S4.setType('S');
//        menuTypeRepository.save(S4);
//
//        MenuType M4 = new MenuType();
//        M4.setPrice(150f);
//        M4.setType('M');
//        menuTypeRepository.save(M4);
//
//        Provider andys = new Provider();
//        andys.setName("Andys pizza");
//        andys.setDeliveryPrice(40);
//
//        providerRepository.save(andys);
//
//        //------
//        Provider dodopitsa = new Provider();
//        dodopitsa.setName("Dodo pizza");
//        dodopitsa.setDeliveryPrice(10);
//
//        providerRepository.save(dodopitsa);
//
//        Provider burger = new Provider();
//        burger.setName("Burger king");
//        burger.setDeliveryPrice(10);
//        burger.setActive(true);
//
//        providerRepository.save(burger);
//
//        Provider olivia = new Provider();
//        olivia.setName("Olivia");
//        olivia.setDeliveryPrice(10);
//
//        providerRepository.save(olivia);
//
//
//        Provider salat5 = new Provider();
//        salat5.setName("Salate");
//        salat5.setDeliveryPrice(10);
//        salat5.setActive(true);
//
//        providerRepository.save(salat5);
//
//
//        //-----
//
//        Provider mac = new Provider();
//        mac.setName("Mac Donalds");
//        mac.setDeliveryPrice(20);
//
//        providerRepository.save(mac);
//
//        Menu salat = new Menu();
//        salat.setName("Bistro");
//        salat.setProvider(andys);
//        salat.setActive(true);
//        salat.setDayOfWeek(DayOfWeek.FRIDAY);
//        S1.setMenu(salat);
//        M1.setMenu(salat);
//        menuRepository.save(salat);
//        menuTypeRepository.save(S1);
//        menuTypeRepository.save(M1);
//
//
//        Menu salat2 = new Menu();
//        salat2.setName("Indean");
//        salat2.setProvider(andys);
//        salat2.setActive(true);
//        salat2.setDayOfWeek(DayOfWeek.WEDNESDAY);
//        S2.setMenu(salat2);
//        M2.setMenu(salat2);
//        menuRepository.save(salat2);
//        menuTypeRepository.save(S2);
//        menuTypeRepository.save(M2);
//
//        Menu salat3 = new Menu();
//        salat3.setName("Japan");
//        salat3.setProvider(andys);
//        salat3.setActive(false);
//        salat3.setDayOfWeek(DayOfWeek.THURSDAY);
//        S3.setMenu(salat3);
//        M3.setMenu(salat3);
//        menuRepository.save(salat3);
//        menuTypeRepository.save(S3);
//        menuTypeRepository.save(M3);
//
//        Menu salat4 = new Menu();
//        salat4.setName("Moldavian");
//        salat4.setProvider(andys);
//        salat4.setActive(true);
//        salat4.setDayOfWeek(DayOfWeek.TUESDAY);
//        S4.setMenu(salat4);
//        M4.setMenu(salat4);
//        menuRepository.save(salat4);
//        menuTypeRepository.save(S4);
//        menuTypeRepository.save(M4);
//        andys.setActive(true);
//        Item mamaliga = new Item();
//        mamaliga.setName("Mamaliga");
//        menuItemRepository.save(mamaliga);
//
//        Item borsh = new Item();
//        borsh.setName("Borsh");
//        menuItemRepository.save(borsh);
//
//        Item poncho = new Item();
//        poncho.setName("Poncho");
//        menuItemRepository.save(poncho);
//
//        Item sashlic = new Item();
//        sashlic.setName("Sashlic");
//        menuItemRepository.save(sashlic);
//
//        M1.setItems(new HashSet<>());
//        M1.getItems().add(borsh);
//        M1.getItems().add(mamaliga);
//        menuTypeRepository.save(M1);
//
//        S1.setItems(new HashSet<>());
//        S1.getItems().add(mamaliga);
//        menuTypeRepository.save(S1);
//
//        M2.setItems(new HashSet<>());
//        M2.getItems().add(mamaliga);
//        M2.getItems().add(sashlic);
//        menuTypeRepository.save(M2);
//
//        S2.setItems(new HashSet<>());
//        S2.getItems().add(mamaliga);
//        menuTypeRepository.save(S2);
//
//        M3.setItems(new HashSet<>());
//        M3.getItems().add(mamaliga);
//        M3.getItems().add(sashlic);
//        M3.getItems().add(poncho);
//        menuTypeRepository.save(M3);
//
//        S3.setItems(new HashSet<>());
//        S3.getItems().add(mamaliga);
//        S3.getItems().add(sashlic);
//        menuTypeRepository.save(S3);
//
//        M4.setItems(new HashSet<>());
//        M4.getItems().add(mamaliga);
//        M4.getItems().add(sashlic);
//        M4.getItems().add(poncho);
//        menuTypeRepository.save(M4);
//
//        S4.setItems(new HashSet<>());
//        S4.getItems().add(mamaliga);
//        S4.getItems().add(sashlic);
//        menuTypeRepository.save(S4);
//        providerRepository.save(andys);
//
//        User user = new User();
//        user.setFirstName("Petea");
//
//
//        user.setLastName("Jhons");
//        user.setEmail("Spartak@gmail.com");
//        user.setSkypeId("JhonesId");
//        user.setPassword("$2y$12$Gytj7ahrI8suqFJxtYG2IevbkegKn.Zj3v2DMSOhROerDvAbLMoiy".toCharArray());
//        user.setOrders(new HashSet<>());
//        user.setNotificationEnabled(true);
//        user.setEnabled(true);
//
//        Role role = new Role();
//        role.setName("ROLE_supervisor");
//        //role.setId((long) 1);
//        roleRepository.save(role);
//
//        Role role1 = new Role();
//        role1.setName("ROLE_user");
//        roleRepository.save(role1);
//
//        String[] names = {"Petea","Sofia","Sanea","Oksana","Stela","Leha","Marat","Sqwozi","Babi","Tolea","Dasha","Masha","Lena","Poleno","Iulia","Gena","Jora","Jack","Chack","Nick","Marsel","Ionuts","Habib"};
//        String[] lastnames = {"Pulea","Dulea","Kebab","Razdorojnaia","Falco","Umanets","Cubric","Ronaldo","Messi","Putin","Tramp","Ecler","Cleon","Lupa","Pupa","Dodon","Medvedev","Chezar","Pizza","Dermendji"};
//        Provider [] providers = {dodopitsa,andys,burger,mac,salat5,olivia};
//
//        String[] menuName = {"Zapecanca","Borsh","Dieta","Post","Lost","Host","Sup","Kebab","Babki","Banan","Adler","Kefir","Kulici","Smikkers"};
//        String[] menu1 = {"Fcusnaia","Zapecionaia","Crivaia","Tupaia","Zelenia","Vialenaia","Kapcionii","Armeanscaia","Eudeiscaia","Iaponscaia","Percionaia"};
//        Random random = new Random();
//        DayOfWeek[] dayOfWeeks = {DayOfWeek.MONDAY,DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY};
//
//        for (int i = 0; i < 80; i++){
//            Menu s = new Menu();
//            s.setName(menuName[random.nextInt(menuName.length)] + " " + menu1[random.nextInt(menu1.length)]);
//            s.setProvider(providers[random.nextInt(providers.length)]);
//            s.setActive(true);
//            s.setDayOfWeek(dayOfWeeks[random.nextInt(dayOfWeeks.length)]);
//            menuRepository.save(s);
//
//            MenuType S47 = new MenuType();
//            S47.setPrice(100f);
//            S47.setType('S');
//
//            MenuType M47 = new MenuType();
//            M47.setPrice(50f);
//            M47.setType('M');
//
//            S47.setMenu(s);
//            M47.setMenu(s);
//
//            S47.setItems(new HashSet<>());
//            S47.getItems().add(mamaliga);
//            S47.getItems().add(sashlic);
//
//            M47.setItems(new HashSet<>());
//            M47.getItems().add(mamaliga);
//            M47.getItems().add(sashlic);
//            M47.getItems().add(poncho);
//
//            menuTypeRepository.save(S47);
//            menuTypeRepository.save(M47);
//
//
//        }
//        for(int i = 0; i < 100;i++){
//            User usr = new User();
//            String a = names[random.nextInt(names.length)];
//            String b = lastnames[random.nextInt(lastnames.length)];
//            usr.setFirstName(a);
//            usr.setLastName(b);
//            usr.setEmail(a + b + "@gmail.com");
//            usr.setSkypeId(b + a + "ID");
//            usr.setPassword("$2y$12$Gytj7ahrI8suqFJxtYG2IevbkegKn.Zj3v2DMSOhROerDvAbLMoiy".toCharArray());
//            usr.setOrders(new HashSet<>());
//            usr.setNotificationEnabled(true);
//            usr.setEnabled(true);
//            usr.setRoles(role1);
//            userRepository.save(usr);
//        }
//
//            user.setRoles(role);
//
//        Order order = new Order();
//        order.setMenuType(S3);
//        user.getOrders().add(order);
//        order.setUser(user);
//        order.setOrdered(false);
//
//        User user1 = new User();
//        user1.setFirstName("Vanea");
//
//        user1.setLastName("Sosiska");
//        user1.setEmail("Kubak@gmail.com");
//        user1.setSkypeId("JhonesId");
//        user1.setPassword("$2y$12$Gytj7ahrI8suqFJxtYG2IevbkegKn.Zj3v2DMSOhROerDvAbLMoiy".toCharArray());
//        user1.setOrders(new HashSet<>());
//        user1.setNotificationEnabled(true);
//        user1.setEnabled(true);
//        user1.setRoles(role1);
//
//
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDate localDate = LocalDate.now();
//
//
//        order.setDate(new Date(dtf.format(localDate)));
//
//        Order order2 = new Order();
//        order2.setMenuType(M3);
//        user.getOrders().add(order2);
//        order2.setUser(user);
//        order2.setOrdered(false);
//        order2.setDate(new Date(dtf.format(localDate)));
//
//
//        Provider provider = new Provider();
//        provider.setName("Andys");
//        salat3.setProvider(provider);
//        provider.setMenus(new HashSet<>());
//        provider.getMenus().add(salat3);
//
//        userRepository.save(user);
//        userRepository.save(user1);
//
//    }
//}
