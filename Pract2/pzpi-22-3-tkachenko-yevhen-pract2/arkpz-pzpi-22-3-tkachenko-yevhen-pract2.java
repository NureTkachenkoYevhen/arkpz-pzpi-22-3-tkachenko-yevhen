package yevhen.tkachenko.parp;

import java.util.Base64;

public class User {
    public String email;

    public User(String email){
        this.email = email;
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User("example@gmail.com");

        user.email = "email.com";
        System.out.println(user.email);
    }
}

public class User {
    private String email;

    public User(String email) {
        setEmail(email);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            User user = new User("example@gmail.com");
            user.setEmail("email.com");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

public class UserService {
    public String hashPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public User createUser(String username, String password) {
        String hashedPassword = hashPassword(password);
        User user = new User(username, hashedPassword);
        return user;
    }
}
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        String password = userService.hashPassword("123456");
        User user = userService.createUser("TestUsername", password);
    }
}

public class UserService {
    private String hashPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public User createUser(String username, String password) {
        String hashedPassword = hashPassword(password);
        User user = new User(username, hashedPassword);
        return user;
    }
}

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.createUser("TestUsername", "123456");
    }
}

public class Team {
    private String name;
    private List<User> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public List<User> getMembers() {
        return members;
    }
}

public class Main {
    public static void main(String[] args) {
        Team team = new Team("KharkivTeam");
        team.getMembers().add(new User("Yevhen"));
        team.getMembers().add(new User("Yevhen"));
        team.getMembers().clear();
    }
}

public class Team {
    private String name;
    private List<User> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public List<User> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public void addMember(User member) {
        if (!members.contains(member)) {
            members.add(member);
        }
    }

    public void removeMember(User member) {
        members.remove(member);
    }
}

public class Main {
    public static void main(String[] args) {
        Team team = new Team("KharkivTeam");
        team.addMember(new User("Yevhen"));
        team.addMember(new User("Yevhen"));
        team.getMembers().clear(); // UnsupportedOperationException
    }
}