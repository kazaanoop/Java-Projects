import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class FindCommand {
    FindCommand(JTextArea ta,JTextField tf) throws BadLocationException {
        String data = ta.getText();
        String word = tf.getText();
        Highlighter h = ta.getHighlighter();
        int start =0;
        int end =0;
        for(int i =0;i<data.length()-word.length()+1;i++){
            if(data.substring(i,i+word.length()).equalsIgnoreCase(word)){
                start = i;
                end = i+word.length();
            }
            h.addHighlight(start, end, DefaultHighlighter.DefaultPainter);
        }
    }
}
