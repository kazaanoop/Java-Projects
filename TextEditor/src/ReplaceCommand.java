import javax.swing.*;

public class ReplaceCommand {
    public ReplaceCommand(JTextArea ta, JTextField tf, JTextField tf2) {
        String data = ta.getText();
        String word = tf.getText();
        String replace = tf2.getText();
        String new_data = data.replace(word,replace);
        ta.setText(new_data);
    }
}
