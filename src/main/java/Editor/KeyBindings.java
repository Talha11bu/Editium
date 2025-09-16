package Editor;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyBindings implements KeyListener{

    private final Actions action;

    KeyBindings(Actions action) {
        this.action = action;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && KeyEvent.VK_N == e.getKeyCode()) action.createNewFile();
        if(e.isControlDown() && KeyEvent.VK_O == e.getKeyCode()) action.openFile();
        if(e.isControlDown() && KeyEvent.VK_S == e.getKeyCode()) action.saveFile();
        if(e.isControlDown() && KeyEvent.VK_A == e.getKeyCode()) action.saveFileAs();
        if(e.isControlDown() && KeyEvent.VK_Z == e.getKeyCode()) action.undoAction();
        if(e.isControlDown() && KeyEvent.VK_Y == e.getKeyCode()) action.redoAction();
        if(e.isControlDown() && KeyEvent.VK_F == e.getKeyCode()) action.findText();
        if(e.isControlDown() && KeyEvent.VK_R == e.getKeyCode()) action.replaceText();
        if(e.isControlDown() && KeyEvent.VK_MINUS == e.getKeyCode()){
            action.gui.textArea.setFont(new Font(action.gui.textArea.getFont().getFamily(), Font.PLAIN, action.gui.textArea.getFont().getSize()-1));
            action.gui.fontSizer.setValue(action.gui.textArea.getFont().getSize());
        }
        if(e.isControlDown() && KeyEvent.VK_EQUALS == e.getKeyCode()){
            action.gui.textArea.setFont(new Font(action.gui.textArea.getFont().getFamily(), Font.PLAIN, action.gui.textArea.getFont().getSize()+1));
            action.gui.fontSizer.setValue(action.gui.textArea.getFont().getSize());
        }        
        if(e.isControlDown() && KeyEvent.VK_C == e.getKeyCode()) action.setFontColor();
        if(e.isControlDown() && KeyEvent.VK_B == e.getKeyCode()) action.setBgColor();
        if(e.isControlDown() && KeyEvent.VK_W == e.getKeyCode()) action.setWordWrap();
        if(e.isControlDown() && KeyEvent.VK_Q == e.getKeyCode()) action.exit();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}