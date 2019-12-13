import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class EditorUI extends Component implements ActionListener {
    String path ="";
    JFrame f;JFrame exitAlertf;
    JLabel alert;
    JButton b1;JButton b2;JButton b3;JButton bf;JButton br;JButton bf2;
    JMenuBar mb;
    JMenu file,edit,help;
    JMenuItem cut,copy,paste,selectAll,open,save,newFile,about,undo,redo,find,replace,exit,saveAs;
    JTextArea ta;
    JTextField tf;JTextField tf2;
    UndoRedo ur = new UndoRedo();
    EditorUI(){
        f=new JFrame();

        cut=new JMenuItem("Cut (Ctrl + X)");
        copy=new JMenuItem("Copy (Ctrl + C)");
        paste=new JMenuItem("Paste (Ctrl + V)");
        open=new JMenuItem("Open");
        open.setMnemonic(KeyEvent.VK_O);// assigning shortcut key
        save=new JMenuItem("Save");
        saveAs=new JMenuItem("Save As...");
        save.setMnemonic(KeyEvent.VK_S);// assigning shortcut key
        newFile=new JMenuItem("New");
        newFile.setMnemonic(KeyEvent.VK_N);// assigning shortcut key
        selectAll=new JMenuItem("SelectAll (Ctrl + A)");
        about = new JMenuItem("About");
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        find = new JMenuItem("Find...");
        replace = new JMenuItem("Replace...");
        exit = new JMenuItem("Exit");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
        open.addActionListener(this);
        newFile.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        find.addActionListener(this);
        replace.addActionListener(this);

        mb=new JMenuBar();
        file=new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);// assigning shortcut key
        edit=new JMenu("Edit");
        help=new JMenu("Help");

        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);edit.add(undo);edit.add(redo);edit.add(find);edit.add(replace);
        file.add(open);file.add(newFile);file.add(save);file.add(saveAs);file.add(exit);
        help.add(about);

        mb.add(file);mb.add(edit);mb.add(help);
        ta=new JTextArea();
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBounds(5,5,773,528);
        f.add(mb);f.add(ta);
        f.setJMenuBar(mb);

        //closing window handling
        /*addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(ta.getText().equals(""))
                    System.exit(0);
                else {
                    if(path.equals("")){
                        exitAlert1();
                    }
                    else{
                        exitAlert2();
                    }
                }
            }
        });*/

        f.setLayout(null);
        f.setSize(800,600);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut) {
            CutCommand cutC = new CutCommand(ta);
            ur.Undo.push(cutC);
            System.out.println("cut");
            ur.Redo.clear();
        }
        if(e.getSource()==paste) {
            PasteCommand pasteC = new PasteCommand(ta);
            ur.Undo.push(pasteC);
            System.out.println("paste");
            ur.Redo.clear();
        }
        if(e.getSource()==copy)
            ta.copy();
        if(e.getSource()==selectAll)
            ta.selectAll();
        if(e.getSource()==about){
            aboutInfo();
        }
        if(e.getSource()==exit){
            if(ta.getText().equals(""))
                System.exit(0);
            else {
                if(path.equals("")){
                    exitAlert1();
                }
                else{
                    exitAlert2();
                }
            }
        }
        if(e.getSource()==open){
            openFile();
        }
        if(e.getSource()==newFile){
            ta.setText("");
            path = "";
        }
        if(e.getSource()==saveAs){
            saveAsFile();
        }
        if(e.getSource()==save){
            saveFile();
        }
        if(e.getSource()==b1){
            saveAsFile();
            System.exit(0);
        }
        if(e.getSource()==b2){
            System.exit(0);
        }
        if(e.getSource()==b3){
            saveFile();
        }
        if(e.getSource()==undo){
            System.out.println("undo-in");
            ur.Redo.push(ur.doUndo(ta));

        }
        if(e.getSource()==redo){
            ur.Undo.push(ur.doRedo(ta));
        }
        if(e.getSource()==find){

            findWord();
        }
        if(e.getSource()==bf){
            try {
                FindCommand findC = new FindCommand(ta,tf);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
        /*if(e.getSource()==bf2){
            try {
                FindCommand findC = new FindCommand(ta,tf);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }*/
        if(e.getSource()==replace){
            replaceWord();
        }
        if(e.getSource()==br){
            ReplaceCommand replaceC = new ReplaceCommand(ta,tf,tf2);
        }
    }

    public void exitAlert1(){
        exitAlertf = new JFrame("Exit Alert");
        alert = new JLabel("You forgot to save the file!!!");
        alert.setBounds(50,50,300,30);
        b2 = new JButton("Exit");
        b1 = new JButton("Save As");
        b1.setBounds(50,100,80, 30);
        b2.setBounds(140,100,80, 30);
        b1.addActionListener(this);
        b2.addActionListener(this);
        exitAlertf.add(alert);
        exitAlertf.add(b1);exitAlertf.add(b2);
        exitAlertf.setLayout(null);
        exitAlertf.setSize(400,200);
        exitAlertf.setVisible(true);
    }

    public void exitAlert2(){
        exitAlertf = new JFrame("Exit Alert");
        alert = new JLabel("You forgot to save the file!!!");
        alert.setBounds(50,50,300,30);
        b2 = new JButton("Exit");
        b1 = new JButton("Save As");
        b3 = new JButton("Save");
        b1.setBounds(50,100,80, 30);
        b2.setBounds(140,100,80, 30);
        b3.setBounds(230,100,80, 30);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        exitAlertf.add(alert);
        exitAlertf.add(b3);exitAlertf.add(b1);exitAlertf.add(b2);
        exitAlertf.setLayout(null);
        exitAlertf.setSize(400,200);
        exitAlertf.setVisible(true);
    }

    public void findWord(){
        exitAlertf = new JFrame("Find");
        alert = new JLabel("Word");
        alert.setBounds(50,50,50,30);
        tf = new JTextField();
        tf.setBounds(100,50,100,30);
        bf = new JButton("Find");
        bf.setBounds(50,100,80, 30);
        bf.addActionListener(this);
        bf2 = new JButton("Remove highlight");
        bf2.setBounds(130,100,100, 30);
        bf2.addActionListener(this);
        exitAlertf.add(alert);
        exitAlertf.add(bf);//exitAlertf.add(bf2);
        exitAlertf.add(tf);
        exitAlertf.setLayout(null);
        exitAlertf.setSize(400,200);
        exitAlertf.setVisible(true);
    }

    public void replaceWord(){
        exitAlertf = new JFrame("Replace");
        alert = new JLabel("Word");
        alert.setBounds(50,50,50,30);
        tf = new JTextField();
        tf.setBounds(100,50,100,30);
        tf2 = new JTextField();
        tf2.setBounds(200,50,100,30);
        br = new JButton("Replace");
        br.setBounds(50,100,80, 30);
        br.addActionListener(this);
        exitAlertf.add(alert);
        exitAlertf.add(br);
        exitAlertf.add(tf);exitAlertf.add(tf2);
        exitAlertf.setLayout(null);
        exitAlertf.setSize(400,200);
        exitAlertf.setVisible(true);
    }

    public void saveFile(){
        if(path.equals("")){
            saveAsFile();
        }
        else {
            try {
                String data = ta.getText();
                FileWriter fw = new FileWriter(path);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(data);
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveAsFile(){
        JFileChooser j = new JFileChooser("C:");

        // Invoke the showsSaveDialog function to show the save dialog
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            // Set the label to the path of the selected directory
            File fi = new File(j.getSelectedFile().getAbsolutePath());

            try {
                // Create a file writer
                FileWriter wr = new FileWriter(fi, false);

                // Create buffered writer to write
                BufferedWriter w = new BufferedWriter(wr);

                // Write
                w.write(ta.getText());

                w.flush();
                w.close();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        // If the user cancelled the operation
        else
            JOptionPane.showMessageDialog(f, "the user cancelled the process");
    }

    public void openFile(){
        JFileChooser file = new JFileChooser();
        int i=file.showOpenDialog(this);
        if(i==JFileChooser.APPROVE_OPTION){
            File f=file.getSelectedFile();
            String filepath=f.getPath();
            path = filepath;
            try{
                BufferedReader br=new BufferedReader(new FileReader(filepath));
                String s1="",s2="";
                while((s1=br.readLine())!=null){
                    s2+=s1+"\n";
                }
                ta.setText(s2);
                br.close();
            }catch (Exception ex) {ex.printStackTrace();  }

        }

        }

    public void aboutInfo(){
        JFrame aboutFrame = new JFrame();
        JOptionPane.showMessageDialog(aboutFrame,"Hello, Welcome to Text Editor.\nThis program is developed by Anoop Kaza." +
                "\nThis Text Editor contains below features:\n1. Undo-Redo with unlimited stack size.\n2. Trie data structure to " +
                "highlight all words with regular expression.");
    }

    public static void main(String[] args) {
        new EditorUI();

    }


}
