import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel class sets up the GUI and displays the spirograph on screen
 */
public class Panel extends JPanel implements ActionListener {

  final private int width = 800; // width of JPanel
  final private int height = 800; // height of JPanel
  private int timerDelay = 25; // gap between executions of code in the timer

  // labels
  private JLabel fixedRadiusLabel;
  private JLabel movingRadiusLabel;
  private JLabel offsetLabel;
  private JLabel warningLabel;

  // text fields
  private JTextField fixedRadiusField;
  private JTextField movingRadiusField;
  private JTextField offsetField;

  // buttons
  private JButton enterButton;
  private JButton clearButton;
  private JButton pauseButton;
  private JButton playButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JButton penColourButton;
  private JButton backgroundColourButton;
  private JButton hideControlsButton;

  private Color penColour; // current colour of pen
  private Hypocycloid hypocycloid; // hypocycloid object
  final private int startX = 400; // x coordinate the hypocycloid is centred around
  final private int startY = 350; // y coordinate the hypocycloid is centred around
  private ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>(); // contains all coordinates in the hypocycloid
  private ArrayList<Color> colours = new ArrayList<>(); // contains all colours of hypocycloid coordinates
  private Boolean controlsShowing = true; // indicates whether controls are currently visible

  /**
   * Constructor for Panel which starts the screen initialisation
   */
  public Panel() {
    initialiseScreen();
  }

  /**
   * Method to set up the JPanel and add components to it
   */
  public void initialiseScreen() {
    setLayout(null);
    setPreferredSize(new Dimension(width, height));
    setBackground(new Color(51, 102, 153));

    addLabels();
    addFields();
    addButtons();
  }

  /**
   * Method to create all the labels and add them to the JPanel
   */
  public void addLabels() {
    fixedRadiusLabel = createLabel("Fixed radius: ", 30);
    movingRadiusLabel = createLabel("Moving radius: ", 50);
    offsetLabel = createLabel("Offset: ", 70);
    warningLabel = createLabel("", 300);
    warningLabel.setForeground(Color.RED);

    this.add(fixedRadiusLabel);
    this.add(movingRadiusLabel);
    this.add(offsetLabel);
    this.add(warningLabel);
  }

  /**
   * Method to create all the text fields and add them to the JPanel
   */
  public void addFields() {
    fixedRadiusField = createField(45);
    movingRadiusField = createField(65);
    offsetField = createField(85);

    this.add(fixedRadiusField);
    this.add(movingRadiusField);
    this.add(offsetField);
  }

  /**
   * Method to create all the buttons and add them to the JPanel
   */
  public void addButtons() {
    hideControlsButton = createButton("Hide controls", 10, 10, 160);
    enterButton = createButton("Draw", 10, 120, 160);
    clearButton = createButton("Clear", 10,150, 160);
    pauseButton = createButton("Pause", 10, 180, 80);
    playButton = createButton( "Play", 90, 180, 80);
    decreaseSpeedButton = createButton( "Slower", 10, 210, 80);
    increaseSpeedButton = createButton( "Faster", 90, 210, 80);
    penColourButton = createButton( "Change pen colour", 10, 240, 160);
    backgroundColourButton = createButton("Change background", 10, 270, 160);

    this.add(hideControlsButton);
    this.add(enterButton);
    this.add(clearButton);
    this.add(pauseButton);
    this.add(playButton);
    this.add(increaseSpeedButton);
    this.add(decreaseSpeedButton);
    this.add(penColourButton);
    this.add(backgroundColourButton);
  }

  /**
   * Method to create JButtons using the given parameters
   * @param text Text to be displayed inside the button
   * @param xCoordinate x coordinate of the button on the screen
   * @param yCoordinate y coordinate of the button on the screen
   * @param width Width of the button
   * @return JButton Returns the button that has been created
   */
  public JButton createButton(String text, int xCoordinate, int yCoordinate, int width) {
    JButton button = new JButton(text);
    button.setBounds(xCoordinate, yCoordinate, width, 30);
    button.addActionListener(this);
    return button;
  }

  /**
   * Method to create JLabels using the given parameters
   * @param text Text to be displayed inside the label
   * @param yCoordinate yCoordinate of the label on the screen
   * @return JLabel Returns the label that has been created
   */
  public JLabel createLabel(String text, int yCoordinate) {
    JLabel label = new JLabel(text);
    label.setBounds(10, yCoordinate, 150, 50);
    return label;
  }

  /**
   * Method to create JTextFields using the given parameters
   * @param yCoordinate  yCoordinate of the text field on the screen
   * @return JTextField Returns the text field that has been created
   */
  public JTextField createField(int yCoordinate) {
    JTextField field = new JTextField();
    field.setBounds(120, yCoordinate, 50, 20);
    return field;
  }

  /**
   * Method to determine what happens when each button is clicked
   * @param actionEvent Event which has triggered method
   */
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == enterButton) {
      timer.stop();
      String fixedRadius = fixedRadiusField.getText();
      String movingRadius = movingRadiusField.getText();
      String offset = offsetField.getText();
      if (fixedRadius.equals("") || movingRadius.equals("") || offset.equals("")) {
        warningLabel.setText("Must enter all values");
        timer.stop();
      } else {
        try {
          hypocycloid = new Hypocycloid(Integer.parseInt(fixedRadius), Integer.parseInt(movingRadius), Integer.parseInt(offset));
          warningLabel.setText("");
          timer.restart();
        } catch (NumberFormatException e) {
          warningLabel.setText("Must enter integers");
        }
      }
    } else if (actionEvent.getSource() == clearButton) {
      timer.stop();
      hypocycloid = null;
      coordinates = new ArrayList<>();
      Panel.this.repaint();
    } else if (actionEvent.getSource() == pauseButton) {
      timer.stop();
    } else if (actionEvent.getSource() == playButton) {
      timer.start();
    } else if (actionEvent.getSource() == increaseSpeedButton) {
      if ((timerDelay - 10) > 0) {
        timerDelay -= 10;
        warningLabel.setText("");
      } else {
        warningLabel.setText("Max speed reached");
      }
      timer.setDelay(timerDelay);
    } else if (actionEvent.getSource() == decreaseSpeedButton) {
      timerDelay += 10;
      warningLabel.setText("");
      timer.setDelay(timerDelay);
    } else if (actionEvent.getSource() == penColourButton) {
        penColour = JColorChooser.showDialog(this, "Select a colour", Color.BLACK);
        System.out.println(penColour);
    } else if (actionEvent.getSource() == backgroundColourButton) {
        setBackground(JColorChooser.showDialog(this, "Select a colour", Color.BLACK));
    } else if (actionEvent.getSource() == hideControlsButton) {
        if (controlsShowing) {
          enterButton.setVisible(false);
          clearButton.setVisible(false);
          pauseButton.setVisible(false);
          playButton.setVisible(false);
          increaseSpeedButton.setVisible(false);
          decreaseSpeedButton.setVisible(false);
          penColourButton.setVisible(false);
          backgroundColourButton.setVisible(false);

          fixedRadiusField.setVisible(false);
          movingRadiusField.setVisible(false);
          offsetField.setVisible(false);

          fixedRadiusLabel.setVisible(false);
          movingRadiusLabel.setVisible(false);
          offsetLabel.setVisible(false);

          controlsShowing = false;
          hideControlsButton.setText("Show controls");
        } else {
          enterButton.setVisible(true);
          clearButton.setVisible(true);
          pauseButton.setVisible(true);
          playButton.setVisible(true);
          increaseSpeedButton.setVisible(true);
          decreaseSpeedButton.setVisible(true);
          penColourButton.setVisible(true);
          backgroundColourButton.setVisible(true);

          fixedRadiusField.setVisible(true);
          movingRadiusField.setVisible(true);
          offsetField.setVisible(true);

          fixedRadiusLabel.setVisible(true);
          movingRadiusLabel.setVisible(true);
          offsetLabel.setVisible(true);

          controlsShowing = true;
          hideControlsButton.setText("Hide controls");
        }
    }
  }

  /**
   * Timer which runs code block at intervals specified by timerDelay parameter
   */
  javax.swing.Timer timer = new javax.swing.Timer(timerDelay, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent arg0) {
      hypocycloid.update();
      ArrayList<Integer> currentCoordinates = new ArrayList<>();
      currentCoordinates.add(hypocycloid.getX());
      currentCoordinates.add(hypocycloid.getY());
      coordinates.add(currentCoordinates);
      colours.add(penColour);
      Panel.this.repaint();
    }
  });

  /**
   * Method to update what is painted on JPanel
   * @param g Graphics to be painted
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (hypocycloid != null) {
      for (int i = 0; i < coordinates.size(); i++) {
        penColour = colours.get(i);
        g.setColor(penColour);
        g.fillOval(startX + coordinates.get(i).get(0), startY + coordinates.get(i).get(1), 5, 5);
      }
    }
  }

}
