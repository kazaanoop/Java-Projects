import javax.swing.*;

public class PasteCommand {
    String data_old = "";
    String data_new = "";
    int start = 0;
    int end = 0;

    public PasteCommand(JTextArea oldTa){
        data_old = oldTa.getText();
        oldTa.paste();
        data_new = oldTa.getText();
    }

    public void unExecute(JTextArea ta) {
        ta.setText(data_old);
    }

    public void Execute(JTextArea ta) {
        ta.setText(data_new);
    }
}
