package Editor;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;

import javax.swing.JColorChooser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Actions{

    private SearchBox lookup = new SearchBox(this);
    WorkArea gui;
    private String fileName, fileAddress;
    private FileDialog fileDialog;
    private FileWriter fileSaver;

    Actions(WorkArea gui){
        this.gui = gui;
    }

    void createNewFile(){
        gui.frame.setTitle("New");
        gui.textArea.setText("");
        fileName = null;
        fileAddress = null;
    }

    void openFile(){
        fileDialog = new FileDialog(gui.frame, "Open", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if(fileDialog.getFile() != null){
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
            gui.frame.setTitle(fileName);
        }

        try (BufferedReader buffRead = new BufferedReader(new FileReader(fileAddress + fileName))) {
            gui.textArea.setText("");

            String line;
            while((line = buffRead.readLine()) != null)
                gui.textArea.append(line + "\n");
        }catch(IOException e){
            System.out.println("unable to open file");
        }
    }

    void saveFile(){
        if(fileName == null){
            saveFileAs();
        }
        try{
            fileSaver = new FileWriter(fileAddress + fileName);
            fileSaver.write(gui.textArea.getText());
            gui.frame.setTitle(fileName);
        }catch(IOException e){
            System.out.println("Something ");
        }
    }
    
    void saveFileAs(){
        fileDialog = new FileDialog(gui.frame, "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);

        if(fileDialog.getFile() != null){
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
            gui.frame.setTitle(fileName);
        }

        try{
            fileSaver = new FileWriter(fileAddress + fileName);
            fileSaver.write(gui.textArea.getText());
        }catch(IOException e){
            System.out.println("File writing problems");
        }
    }

    void exit(){
        System.exit(0);
    }

    void setFontSize(){
        gui.textArea.setFont(new Font(gui.textArea.getFont().getFamily(), Font.PLAIN, (int)gui.fontSizer.getValue()));
    }

    void setFontColor(){
        Color color = JColorChooser.showDialog(gui.frame, "Choose Text Color", gui.textArea.getForeground());
        gui.textArea.setForeground(color);
    }

    void setFont(){
        String selectedFont = (String) gui.fontList.getSelectedItem();
        gui.fontList.setSelectedItem(selectedFont);
        gui.textArea.setFont(new Font(selectedFont, Font.PLAIN, (int)gui.fontSizer.getValue()));
    }

    void setWordWrap(){
        gui.textArea.setLineWrap(!gui.textArea.getLineWrap());
        gui.textArea.setWrapStyleWord(!gui.textArea.getWrapStyleWord());
        gui.wordWrap.setText("Word Wrap :" + (gui.textArea.getLineWrap() ? " ON" : " OFF"));
    }

    void setBgColor(){
        Color color = JColorChooser.showDialog(gui.frame, "Choose Background Color", gui.textArea.getBackground());
        gui.textArea.setBackground(color);
    }

    void undoAction(){
        gui.undoMgr.undo();
    }

    void redoAction(){
        gui.undoMgr.redo();
    }
    
    void findText(){
        lookup.openSearchDialog();
    }

    void replaceText(){
        lookup.openReplaceDialog();
    }
}