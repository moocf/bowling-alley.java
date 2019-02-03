package main;
import iiit.util.*;
import iiit.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class RegistrationDesk extends EventEmitter implements ActionListener {
  private final JFrame frame;
  private final JInputPanel id, name, email;
  private final JButtonPanel register, cancel;

  
  public RegistrationDesk() {
    frame = new JFrame("Registration Desk");
    frame.getContentPane().setLayout(new BorderLayout());
    ((JPanel) frame.getContentPane()).setOpaque(false);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JPanel patron = new JPanel();
    patron.setLayout(new GridLayout(3, 1));
    patron.setBorder(new TitledBorder("New Patron"));

    patron.add(id = new JInputPanel("Nickname", 15));
    patron.add(name = new JInputPanel("Fullname", 15));
    patron.add(email = new JInputPanel("Email", 15));

    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(4, 1));
    buttons.add(register = new JButtonPanel("Register", this));
    buttons.add(cancel = new JButtonPanel("Cancel", this));

    panel.add(patron, "Center");
    panel.add(buttons, "East");
    frame.getContentPane().add("Center", panel);
    frame.pack();
    JFrames.screenCenter(frame);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source.equals(cancel.button)) {
      frame.setVisible(false);
    }
    if(source.equals(register.button)) {
      emit("end", new Bowler(
        id.textField.getText(),
        name.textField.getText(),
        email.textField.getText()
      ));
      frame.setVisible(false);
    }
  }
}
