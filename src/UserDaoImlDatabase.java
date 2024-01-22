import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImlDatabase implements UserDao {
    private Connection connection;
    private final String dbUrl = "jdbc:mysql://localhost/consolecrud";
    private final String dbUser = "MYSQL_USER";
    private final String dbPassword = "MYSQL_PASSWORD";
    private Map<Long, User> userList = new HashMap<>();

    public UserDaoImlDatabase() {
        try {
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setPassword(result.getString(3));
                this.create(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(Long id) { return userList.get(id); }

    public List<User> getAll() {
        return new ArrayList<>(userList.values());
    }

    public void create(User user) {
        userList.put(user.getId(), user);
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)");
            stmt.setLong(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(User user) {
        if (userList.containsKey(user.getId())) {
            userList.put(user.getId(), user);
            try {
                PreparedStatement stmt = connection.prepareStatement("UPDATE SET name = ?, password = ? WHERE id = ?");
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setLong(3, user.getId());
                stmt.executeQuery();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            throw new RuntimeException("Такого пользователя нет в списке");
        }
    }

    public void delete(Long id) {
        userList.remove(id);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
