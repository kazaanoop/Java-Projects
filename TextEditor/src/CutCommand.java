import javax.swing.*;

public class CutCommand {
    String data_old = "";
    String data_new = "";
    int start = 0;
    int end = 0;

    public CutCommand(JTextArea oldTa){
        data_old = oldTa.getText();
        oldTa.cut();
        start = oldTa.getSelectionStart();
        end = oldTa.getSelectionEnd();
        data_new = oldTa.getText();
    }

    public void unExecute(JTextArea ta) {
        ta.setText(data_old);
    }

    public void Execute(JTextArea ta) {
        ta.setText(data_new);
        ta.setSelectionStart(start);
        ta.setSelectionEnd(end);
    }
}
