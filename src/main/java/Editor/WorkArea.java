package Editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

class WorkArea {

    private final Actions action = new Actions(this);
    UndoManager undoMgr = new UndoManager();

    JFrame frame;
    JTextArea textArea;
    JScrollPane scrollBar;
    JMenuBar menuBar;
    JMenu fileMenu, formatMenu;
    JMenuItem newFile, openFile, saveFile, saveAs, exitFile, findText, replaceText, changeTextColor, backgroundColor, wordWrap, undoChange, redoChange;
    JSpinner fontSizer;
    JComboBox<String> fontList;

    WorkArea(){
        createFrame();
        createTextArea();  
        createMenuBar(); 
        frame.setVisible(true);
    }

    private void createFrame(){
        frame = new JFrame("Editium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        frame.setSize(800, 600);
        frame.setIconImage(new ImageIcon("src\\main\\resources\\Icon.png").getImage());
        frame.setLocationRelativeTo(null);

    }

    private void createTextArea(){
        textArea = new JTextArea();
        textArea.addKeyListener(new KeyBindings(action));
        textArea.getDocument().addUndoableEditListener(e -> undoMgr.addEdit(e.getEdit()));
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setBorder(BorderFactory.createEmptyBorder());

        frame.add(scrollBar);
    }

    private void createMenuBar(){
        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createEmptyBorder());
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.setOpaque(false);
        menuBar.setMargin(new Insets(0, 0, 0, 0));

        fileMenu = new JMenu("File");
        fileMenu.setPreferredSize(new Dimension(40,20));
        createFileMenu();
        fileMenu.setFocusable(false);
        menuBar.add(fileMenu);

        formatMenu = new JMenu("Format");
        formatMenu.setPreferredSize(new Dimension(50,20));
        createFmtMenu();
        formatMenu.setFocusable(false);
        menuBar.add(formatMenu);

        fontSizer = new JSpinner();
        fontSizer.setPreferredSize(new Dimension(50, 20));
        fontSizer.setValue(15);
        fontSizer.addChangeListener(e -> action.setFontSize());
        menuBar.add(fontSizer);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontList = new JComboBox<>(fonts);
        fontList.setPreferredSize(new Dimension(200, 20));
        fontList.addActionListener(e -> action.setFont());
        menuBar.add(fontList);

        frame.setJMenuBar(menuBar);
    }

    private void createFileMenu(){
        newFile = new JMenuItem("New");
        newFile.setFocusable(false);
        newFile.addActionListener(e -> action.createNewFile());
        fileMenu.add(newFile);

        openFile = new JMenuItem("Open");
        openFile.setFocusable(false);
        openFile.addActionListener(e -> action.openFile());
        fileMenu.add(openFile);

        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(e -> action.saveFile());
        fileMenu.add(saveFile);

        saveAs = new JMenuItem("SaveAs");
        saveAs.addActionListener(e -> action.saveFileAs());
        fileMenu.add(saveAs);

        exitFile = new JMenuItem("Exit");
        exitFile.addActionListener(e -> action.exit());
        fileMenu.add(exitFile);
    }

    private void createFmtMenu(){
        findText = new JMenuItem("Find");
        findText.addActionListener(e -> action.findText());
        formatMenu.add(findText);

        replaceText = new JMenuItem("Replace");
        replaceText.addActionListener(e -> action.replaceText());
        formatMenu.add(replaceText);

        wordWrap = new JMenuItem("Word Wrap : OFF");
        wordWrap.addActionListener(e -> action.setWordWrap());
        formatMenu.add(wordWrap);

        changeTextColor = new JMenuItem("Text Color");
        changeTextColor.addActionListener(e -> action.setFontColor());
        formatMenu.add(changeTextColor);

        backgroundColor = new JMenuItem("Background Color");
        backgroundColor.addActionListener(e -> action.setBgColor());
        formatMenu.add(backgroundColor);

        undoChange = new JMenuItem("Undo");
        undoChange.addActionListener(e -> action.undoAction());
        formatMenu.add(undoChange);

        redoChange = new JMenuItem("Redo");
        redoChange.addActionListener(e -> action.redoAction());
        formatMenu.add(redoChange);

     }
}