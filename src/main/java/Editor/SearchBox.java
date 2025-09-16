package Editor;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

class SearchBox{

    private final Actions action;
    private JTextField findTextField, replaceTextField;
    private JButton findNextButton, replaceButton, replaceAllButton;
    private JDialog findWord, replaceWord;
    private JCheckBox matchCaseCheckBox, wholeWordCheckBox;
    private JLabel findLabel, replaceLabel;
    
    SearchBox(Actions action){
        this.action = action;
    }
    
    void openSearchDialog(){
        findWord = new JDialog(action.gui.frame, "Find");
        findWord.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        findWord.setSize(350, 125);
        findWord.setResizable(false);
        findWord.setLocationRelativeTo(action.gui.frame);
        findWord.setBackground(new Color(242, 242, 242));
        initFindLabel();
        findWord.add(findLabel);
        findWord.setVisible(true);
    }

    void openReplaceDialog(){
        replaceWord = new JDialog(action.gui.frame, "Replace");
        replaceWord.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        replaceWord.setSize(350, 150);
        replaceWord.setResizable(false);
        replaceWord.setLocationRelativeTo(action.gui.frame);
        replaceWord.setBackground(new Color(242, 242, 242));
        initReplaceLabel();
        replaceWord.add(replaceLabel);
        replaceWord.setVisible(true);
    }

    private void initFindLabel() {
        findLabel = new JLabel();
        findLabel.setLayout(null);

        findTextField = new JTextField(20);
        findTextField.setBounds(5, 10, 200, 20);
        findTextField.setText("Find");
        findTextField.setCaretPosition(0);
        findTextField.setForeground(Color.LIGHT_GRAY);
        findTextField.addKeyListener(new Keys());
        findLabel.add(findTextField);

        findNextButton = new JButton("Find");
        findNextButton.setBounds(210, 10 , 100, 20);
        findNextButton.setBackground(new Color(242, 242, 242));
        findNextButton.setFocusable(false);
        findNextButton.addActionListener(e -> findWord(findTextField.getText()));

        findLabel.add(findNextButton);

        matchCaseCheckBox = new JCheckBox("Match Case");
        matchCaseCheckBox.setBounds(5 ,35 , 100, 20);
        matchCaseCheckBox.setBackground(new Color(242, 242, 242));
        matchCaseCheckBox.setFocusable(false);
        findLabel.add(matchCaseCheckBox);

        wholeWordCheckBox = new JCheckBox("Whole Word");
        wholeWordCheckBox.setBounds(100, 35, 100, 20);
        wholeWordCheckBox.setBackground(new Color(242, 242, 242));
        wholeWordCheckBox.setFocusable(false);
        findLabel.add(wholeWordCheckBox);
    }

    private void initReplaceLabel() {
        replaceLabel = new JLabel();
        replaceLabel.setLayout(null);

        findTextField = new JTextField(20);
        findTextField.setBounds(5, 10, 200, 20);
        findTextField.setText("Find");
        findTextField.setCaretPosition(0);
        findTextField.setForeground(Color.LIGHT_GRAY);
        findTextField.addKeyListener(new Keys());
        replaceLabel.add(findTextField);

        replaceTextField = new JTextField(20);
        replaceTextField.setBounds(5, 35, 200, 20);
        replaceTextField.setText("Replace with");
        replaceTextField.setCaretPosition(0);
        replaceTextField.setForeground(Color.LIGHT_GRAY);
        replaceTextField.addKeyListener(new Keys());
        replaceLabel.add(replaceTextField);

        replaceButton = new JButton("Replace");        
        replaceButton.setBounds(210, 10 , 100, 20);
        replaceButton.setBackground(new Color(242, 242, 242));
        replaceButton.setFocusable(false);
        replaceButton.addActionListener(e -> ReplaceWord(findTextField.getText(), replaceTextField.getText()));
        replaceLabel.add(replaceButton);

        replaceAllButton = new JButton("Replace All");
        replaceAllButton.setBounds(210, 35, 100, 20);
        replaceAllButton.setBackground(new Color(242, 242, 242));
        replaceAllButton.setFocusable(false);
        replaceAllButton.addActionListener(e -> ReplaceAllWord(findTextField.getText(), replaceTextField.getText()));
        replaceLabel.add(replaceAllButton);

        matchCaseCheckBox = new JCheckBox("Match Case");
        matchCaseCheckBox.setBounds(5, 60, 100, 20);
        matchCaseCheckBox.setBackground(new Color(242, 242, 242));
        matchCaseCheckBox.setFocusable(false);
        replaceLabel.add(matchCaseCheckBox);
    }
    
    private void findWord(String find){
        find = "\\b" + find + "\\b";
        int flags = matchCaseCheckBox.isEnabled()? 0 : Pattern.CASE_INSENSITIVE;
        Document doc = action.gui.textArea.getDocument();
        Highlighter highlighter = action.gui.textArea.getHighlighter();
        highlighter.removeAllHighlights();

        Pattern pattern = Pattern.compile(find, flags);
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);

        try {
            Matcher matcher = pattern.matcher(doc.getText(0, doc.getLength()));
            int pos = 0;
            while (matcher.find(pos)) {
            int start = matcher.start();
            int end   = matcher.end();
            highlighter.addHighlight(start, end, painter);
            pos = end;
            }
        }catch (BadLocationException ex){
        }
        
    }

    private void ReplaceWord(String find, String replaceWith){
        find = "\\b" + find + "\\b";
        int flags = matchCaseCheckBox.isEnabled()? 0 : Pattern.CASE_INSENSITIVE;
        Document doc = action.gui.textArea.getDocument();
        Highlighter highlighter = action.gui.textArea.getHighlighter();
        highlighter.removeAllHighlights();

        Pattern pattern = Pattern.compile(find, flags);
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);

        try {
            Matcher matcher = pattern.matcher(doc.getText(0, doc.getLength()));
            int pos = 0;
            while (matcher.find(pos)) {
            int start = matcher.start();
            int end   = matcher.end();
            highlighter.addHighlight(start, end, painter);
            pos = end;
            }
           action.gui.textArea.setText(matcher.replaceAll(replaceWith));
           }catch (BadLocationException ex){
        }

    }

    private void ReplaceAllWord(String find, String replaceWith){
        find = "\\b" + find + "\\b";
        int flags = matchCaseCheckBox.isEnabled()? 0 : Pattern.CASE_INSENSITIVE;
        Document doc = action.gui.textArea.getDocument();
        Highlighter highlighter = action.gui.textArea.getHighlighter();
        highlighter.removeAllHighlights();

        Pattern pattern = Pattern.compile(find, flags);
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);

        try {
            Matcher matcher = pattern.matcher(doc.getText(0, doc.getLength()));
            int pos = 0;
            while (matcher.find(pos)) {
            int start = matcher.start();
            int end   = matcher.end();
            highlighter.addHighlight(start, end, painter);
            pos = end;
            }
            action.gui.textArea.setText(matcher.replaceAll(replaceWith));
        }catch (BadLocationException ex){
        }

    }

    private class Keys implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if(findTextField != null){
                if(findTextField.getText().equals("Find")){
                    findTextField.setText("");
                    findTextField.setForeground(Color.BLACK);
                }
            }
            
            if(replaceTextField != null){
                if(replaceTextField.getText().equals("Replace with")){
                    replaceTextField.setText("");
                    replaceTextField.setForeground(Color.BLACK);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}