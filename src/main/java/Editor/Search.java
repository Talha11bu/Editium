package Editor;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

class Search{

    Actions action;
    JTextField findTextField, replaceTextField;
    JButton findNextButton, replaceButton, replaceAllButton;
    JDialog findWord, replaceWord;
    JCheckBox matchCaseCheckBox, wholeWordCheckBox;
    JLabel findLabel, replaceLabel;
    
    Search(Actions action){
        this.action = action;
    }
    
    void openSearchDialog(){
        findWord = new JDialog(action.gui.frame, "Find");
        findWord.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        findWord.setSize(400, 200);
        findWord.setResizable(false);
        findWord.setLocationRelativeTo(action.gui.frame);
        initFindLabel();
        findWord.add(findLabel);
        findWord.setVisible(true);
    }

    void openReplaceDialog(){
        replaceWord = new JDialog(action.gui.frame, "Replace");
        replaceWord.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        replaceWord.setSize(400, 200);
        replaceWord.setResizable(false);
        replaceWord.setLocationRelativeTo(action.gui.frame);
        initReplaceLabel();
        replaceWord.add(replaceLabel);
        replaceWord.setVisible(true);
    }

    private void initFindLabel() {
        findLabel = new JLabel();
        findLabel.setLayout(null);

        findTextField = new JTextField(20);
        findTextField.setBounds(50, 20, 300, 30);
        findLabel.add(findTextField);

        findNextButton = new JButton("Find Next");
        findNextButton.setBounds(50, 70, 100, 30);
        findLabel.add(findNextButton);

        matchCaseCheckBox = new JCheckBox("Match Case");
        matchCaseCheckBox.setBounds(160, 70, 100, 30);
        findLabel.add(matchCaseCheckBox);

        wholeWordCheckBox = new JCheckBox("Whole Word");
        wholeWordCheckBox.setBounds(270, 70, 100, 30);
        findLabel.add(wholeWordCheckBox);
    }

    private void initReplaceLabel() {
        replaceLabel = new JLabel();
        replaceLabel.setLayout(null);

        findTextField = new JTextField(20);
        findTextField.setBounds(50, 20, 300, 30);
        replaceLabel.add(findTextField);

        replaceTextField = new JTextField(20);
        replaceTextField.setBounds(50, 60, 300, 30);
        replaceLabel.add(replaceTextField);

        replaceButton = new JButton("Replace");
        replaceButton.setBounds(50, 110, 100, 30);
        replaceLabel.add(replaceButton);

        replaceAllButton = new JButton("Replace All");
        replaceAllButton.setBounds(160, 110, 100, 30);
        replaceLabel.add(replaceAllButton);

        matchCaseCheckBox = new JCheckBox("Match Case");
        matchCaseCheckBox.setBounds(270, 110, 100, 30);
        replaceLabel.add(matchCaseCheckBox);
    }
    
}