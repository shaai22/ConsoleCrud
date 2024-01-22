import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.*;
import java.io.*;

public class UserDaoImlFile implements UserDao {
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    private Map<Long, User> userList = new HashMap<>();
    private Type userListType = new TypeToken<Map<Long, User>>() {}.getType();

    public UserDaoImlFile() {
        File targetFile = new File("users.json");
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try (JsonReader reader = new JsonReader(new FileReader("users.json"))) {
            userList = gson.fromJson(reader, this.userListType);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public User getUser(Long id) {
        return userList.get(id);
    }

    public List<User> getAll() {
        return new ArrayList<>(userList.values());
    }

    public void create(User user) {
        userList.put(user.getId(), user);
        try {
            this.writeJSON();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void update(User user) {
        if (userList.containsKey(user.getId())) {
            userList.put(user.getId(), user);
            try {
                this.writeJSON();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            throw new RuntimeException("Такого пользователя нет в списке");
        }
    }

    public void delete(Long id) {
        userList.remove(id);
    }

    private void writeJSON() throws IOException {
        try (Writer writer = new FileWriter("users.json")) {
            gson.toJson(this.getAll(), writer);
        }
    }
}
