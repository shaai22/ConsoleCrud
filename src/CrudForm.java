import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class CrudForm {
    private JPanel rootPanel;
    private JTextField nameBox;
    private JPasswordField passwordBox;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JList userList;

    public CrudForm() {
        JFrame frame = new JFrame("Регистрация пользователя");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300,400));
        frame.pack();
        frame.setVisible(true);

        createButton.addActionListener(this::createButtonActionPerformed);
    }

    private void createButtonActionPerformed(ActionEvent e) {
        UserDaoImlDatabase udid = new UserDaoImlDatabase();
        User newUser = new User();
        newUser.setId(new Random().nextLong(100L));
        newUser.setName(nameBox.getText());
        newUser.setPassword(passwordBox.getPassword().toString());
        udid.create(newUser);
    }
}
