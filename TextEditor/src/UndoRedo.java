import javax.swing.*;
import java.util.Stack;

public class UndoRedo {
    Stack<Object> Undo;
    Stack<Object> Redo;
    UndoRedo(){
        Undo = new Stack<>();
        Redo = new Stack<>();
    }

    public Object doUndo(JTextArea oldTa){
        Object temp = null;
        if(canUndo()) {
            temp = Undo.pop();

            if(temp instanceof CutCommand){
                System.out.println("Cut instance");
                ((CutCommand) temp).unExecute(oldTa);
            }
            else if(temp instanceof PasteCommand){
                System.out.println("Paste instance");
                ((PasteCommand) temp).unExecute(oldTa);
            }
        }
        return temp;
    }
    public Object doRedo(JTextArea oldTa){
        Object temp = null;
        if(canRedo()) {
            temp = Redo.pop();

            if(temp instanceof CutCommand){
                System.out.println("instance");
                ((CutCommand) temp).Execute(oldTa);
            }
            else if(temp instanceof PasteCommand){
                System.out.println("Paste instance");
                ((PasteCommand) temp).unExecute(oldTa);
            }
        }
        return temp;
    }

    public boolean canUndo(){
        System.out.println(Undo.peek());
        if(Undo.peek()!=null)
            return true;
        return false;
    }
    public boolean canRedo(){
        if(Redo.peek()!=null)
            return true;
        return false;
    }
}
