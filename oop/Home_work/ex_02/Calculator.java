import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Calculator extends JFrame implements ActionListener {
  JTextField jtx;
    
  int temp,temp1,result;
  
  
  int z=0;
  char ch;
  
  String[] std_btn = {
    "7","8","9",
    "4","5","6",
    "1","2","3",
    "C","0","="
  };
  JButton[] std_btn_obj ;
  
  String[] logic_btn = {
    "&",
    "|",
    "^",
    "~"
  };
  JButton[] logic_btn_obj ;

  String[] reg_btn = {
    "+",
    "-",
    "/",
    "*"
  };
  JButton[] reg_btn_obj ;

  
  
  JMenuBar bar;
  JMenu mode;
  JMenuItem exit;
  JRadioButtonMenuItem standard,logic;
  ButtonGroup bg;
  Container cont;
  JPanel text_panel,logic_panel,std_panel,reg_panel;
  
  Calculator() {
    int i;
    cont = getContentPane();
    cont.setLayout(new BorderLayout());
    JPanel text_panel = new JPanel();
    Font font = new Font("Arial",Font.PLAIN,18);
    jtx = new JTextField(25);
    jtx.setFont(font);
    jtx.setHorizontalAlignment(SwingConstants.RIGHT);

    jtx.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent keyevent) {
          char c = keyevent.getKeyChar();
          if(c >= '0' && c <= '9') {
          }
          else {
            keyevent.consume();
          }
        }
      });
    text_panel.add(jtx);

    
    std_panel = new JPanel();
    std_panel.setLayout(new GridLayout(4,4,2,2));
    boolean t = true;
    logic_panel = new JPanel();
    logic_panel.setLayout(new GridLayout(4,1));
    reg_panel = new JPanel();
    reg_panel.setLayout(new GridLayout(4,1));
    
    bar = new JMenuBar();
    mode = new JMenu("Mode");

    standard = new JRadioButtonMenuItem("Standard",true);
    standard.addItemListener(new radiohandler());
    
    logic = new JRadioButtonMenuItem("Logic");
    logic.addItemListener(new radiohandler());

    exit = new JMenuItem("Exit");
    exit.addActionListener(this);
    
    bg = new ButtonGroup();
    bg.add(standard);
    bg.add(logic);
    mode.add(standard);
    mode.add(logic);
    mode.add(exit);
    bar.add(mode);
    setJMenuBar(bar);

    std_btn_obj = new JButton[16];
    for(i = 0; i < std_btn.length;i++){
      std_btn_obj[i] = new JButton(std_btn[i]);
      std_panel.add(std_btn_obj[i]);
      std_btn_obj[i].addActionListener(this);
    }
    

    logic_btn_obj = new JButton[4];
    for(i = 0; i < logic_btn.length;i++){
      logic_btn_obj[i] = new JButton(logic_btn[i]);
      logic_panel.add(logic_btn_obj[i]);
      logic_btn_obj[i].addActionListener(this);
    }
    reg_btn_obj = new JButton[4];
    for(i = 0; i < reg_btn.length;i++){
      reg_btn_obj[i] = new JButton(reg_btn[i]);
      reg_panel.add(reg_btn_obj[i]);
      reg_btn_obj[i].addActionListener(this);
    }
    
    cont.add("Center",std_panel);
    cont.add("East",reg_panel);
    
    cont.add("North",text_panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  class radiohandler implements ItemListener {
    public void itemStateChanged(ItemEvent ie) {
      AbstractButton button = (AbstractButton)ie.getItem();
      String label = button.getText(); {
        if(label.equals("Standard")) {
          cont.remove(logic_panel);
          cont.add("West",reg_panel);
          
          validate();
        }
        if(label.equals("Logic")) {
          cont.remove(reg_panel);
          cont.add("East",logic_panel);
          validate();
        }
      }
    }
  }

  
  public void actionPerformed(ActionEvent e) {
    boolean need_double = false;
    double res;
    String s = e.getActionCommand();
    if(s.equals("Exit")) {
      System.exit(0);
    }

    if(s.matches("\\d")) {
      if(z == 0) {
        jtx.setText(jtx.getText() + s);
      } else {
        jtx.setText("");
        jtx.setText(jtx.getText() + s);
        z = 0;
      }
    }
    
    
    if(s.equals("C")) {
      jtx.setText("");
      
      z=0;
    }
    
    if(s.equals("+")) {
      temp = Integer.valueOf(jtx.getText());
      jtx.setText("");
      ch = '+';
      jtx.requestFocus();
    }
    if(s.equals("-")) {
      temp = Integer.valueOf(jtx.getText());
      jtx.setText("");
      ch='-';
      jtx.requestFocus();
    }
    if(s.equals("/")) {
      temp = Integer.valueOf(jtx.getText());
      ch = '/';
      jtx.setText("");
      jtx.requestFocus();
    }
    if(s.equals("*")) {
      temp = Integer.valueOf(jtx.getText());
      ch = '*';
      jtx.setText("");
      jtx.requestFocus();
    }
    if(s.equals("&")) {
      temp = Integer.valueOf(jtx.getText());
      ch = '&';
      jtx.setText("");
      jtx.requestFocus();
    }
    if(s.equals("|")) {
      temp = Integer.valueOf(jtx.getText());
      ch = '|';
      jtx.setText("");
      jtx.requestFocus();
    }
    if(s.equals("^")) {
      temp = Integer.valueOf(jtx.getText());
      ch = '^';
      jtx.setText("");
      jtx.requestFocus();
    }
    if(s.equals("~")) {
      temp = Integer.valueOf(jtx.getText());
      ch = '~';
      result = ~temp;
      jtx.setText("");
      jtx.setText(Integer.toString(result));
      
    }
    
    if(s.equals("=")) {
      if(jtx.getText().equals("")) {
        jtx.setText("");
      }
      else {
        temp1 = Integer.valueOf(jtx.getText());
        switch(ch) {
          case '+':
            result = temp + temp1;
            break;
          case '-':
            result = temp - temp1;
            break;
          case '/':
            need_double = true;
            break;
          case '*':
            result = temp * temp1;
            break;
          case '&':
            result = temp & temp1;
            break;
          case '|':
            result = temp | temp1;
            break;
          case '^':
            result = temp ^ temp1;
            break;
        }
        if(need_double){
          jtx.setText("");
          try {
            res = (double)temp / (double)temp1;
          }
          catch( ArithmeticException ae ) {
            res = 0;
          }
          jtx.setText(jtx.getText() + res);
          z=1;
          need_double = false;
        } else {
          jtx.setText("");
          jtx.setText(jtx.getText() + result);
          z=1;
        }
      }
      jtx.requestFocus();
    }
  }
  public static void main(String args[]) {
    Calculator calc = new Calculator();
    calc.setTitle("CALCULATOR");
    calc.setSize(370,250);
    calc.setResizable(false);
    calc.setVisible(true);
  }
}

