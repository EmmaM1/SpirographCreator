import javax.swing.JFrame;

/**
 * Window class sets up the JFrame
 */
public class Window extends JFrame {

  /**
   * Constructor for Window which starts window initialisation
   */
  public Window() {
    initialiseUI();
  }

  /**
   * Method to set up the JFrame and adds panel to it
   */
  private void initialiseUI() {
    add(new Panel());
    pack();
    setLocationRelativeTo(null);
    setTitle("Spirograph Creator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  /**
   * Creates an instance of Window to start the program
   * @param args Unused
   */
  public static void main(String[] args) {
    new Window();
  }

}
