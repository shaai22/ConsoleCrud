import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserDao {
    User getUser(Long id);
    List<User> getAll();
    void create(User user);
    void update(User user);
    void delete(Long id);
}
