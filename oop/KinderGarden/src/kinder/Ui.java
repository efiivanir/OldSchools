package kinder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Year;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Ui extends JFrame {
  private boolean ok;
  private boolean idOk;

  private JPanel areaPanel;;
  private JPanel gardenPanel;
  private JPanel stuffPanel;
  private JPanel childPanel;

  private JTable gardenTable;
  private JTable stuffTable;
  private JTable childTable;
  private JTable areaTable;
  /**
   * Constructor of Gui interface
   *
   * @return
   */
  public Ui() {
    super("City Kinder Garden management system");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    getContentPane().add(mainPanel);

    itemAreaPanel();
    itemGardenPanel();
    itemStuffPanel();
    itemChildPanel();

    JTabbedPane tabPane = new JTabbedPane();
    tabPane.addTab("Area", areaPanel);
    tabPane.addTab("Garden", gardenPanel);
    tabPane.addTab("Stuff", stuffPanel);
    tabPane.addTab("Child", childPanel);
    mainPanel.add(tabPane);
  }

  /** Create Area panel */
  public void itemAreaPanel() {

    int i;

    String b;
    String[] row = new String[3];

    AreaQueries a = new AreaQueries();
    Vector<Area> all_area = a.getAll();

    String[] columnNames = {"Id", "Name", "Manager Id"};

    areaPanel = new JPanel();

    // setLayout(new BorderLayout());
    // JTable areaTable = new JTable(data , columnNames);
    areaTable = new JTable();

    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);

    // set the model to the table
    areaTable.setModel(model);

    // Change A JTable Background Color, Font Size, Font Color, Row Height
    areaTable.setBackground(Color.LIGHT_GRAY);
    areaTable.setForeground(Color.black);
    Font font = new Font("", 1, 22);
    areaTable.setFont(font);
    areaTable.setRowHeight(30);
    areaPanel.add(areaTable);
    areaPanel.setSize(900, 500);

    areaPanel.setVisible(true);
    JScrollPane pane = new JScrollPane(areaTable);
    pane.setBounds(0, 0, 880, 200);
    areaPanel.setLayout(null);
    areaPanel.add(pane);
    for (i = 0; i < all_area.size(); i++) {
      b = all_area.elementAt(i).getId();
      row[0] = b;
      b = all_area.elementAt(i).getName();
      row[1] = b;
      b = all_area.elementAt(i).getMngId();
      row[2] = b;
      model.addRow(row);
    }
    // create JTextFields
    JLabel labelId = new JLabel("ID");
    JLabel labelName = new JLabel("Name");
    JLabel labelMngId = new JLabel("Mng ID");

    JTextField textId = new JTextField();
    JTextField textName = new JTextField();
    JTextField textMngId = new JTextField();

    // create JButtons
    JButton btnAdd = new JButton("Add");
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnPrint = new JButton("Print");

    labelId.setBounds(0, 220, 100, 25);
    labelName.setBounds(0, 250, 100, 25);
    labelMngId.setBounds(0, 280, 100, 25);

    textId.setBounds(130, 220, 100, 25);
    textName.setBounds(130, 250, 100, 25);
    textMngId.setBounds(130, 280, 100, 25);

    btnAdd.setBounds(270, 220, 100, 25);
    btnUpdate.setBounds(270, 265, 100, 25);
    btnDelete.setBounds(270, 310, 100, 25);
    btnPrint.setBounds(270, 355, 100, 25);

    // add JTextFields to the jframe
    areaPanel.add(textId);
    areaPanel.add(textName);
    areaPanel.add(textMngId);

    areaPanel.add(labelId);
    areaPanel.add(labelName);
    areaPanel.add(labelMngId);

    areaPanel.add(btnAdd);
    areaPanel.add(btnDelete);
    areaPanel.add(btnUpdate);
    areaPanel.add(btnPrint);

    // button add row
    btnAdd.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            row[0] = textId.getText();
            row[1] = textName.getText();
            row[2] = textMngId.getText();

            // add row to the model
            model.addRow(row);
            a.addArea(row[0], row[1], row[2]);
          }
        });
    // button remove row
    btnDelete.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = areaTable.getSelectedRow();
            String delId = areaTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              // remove a row from jtable
              model.removeRow(i);
              a.removeArea(delId);
            } else {
              System.out.println("Delete Error");
            }
          }
        });

    // get selected row data From table to textfields
    areaTable.addMouseListener(
        new MouseAdapter() {

          @Override
          public void mouseClicked(MouseEvent e) {

            // i = the index of the selected row
            int i = areaTable.getSelectedRow();

            textId.setText(model.getValueAt(i, 0).toString());
            textName.setText(model.getValueAt(i, 1).toString());
            textMngId.setText(model.getValueAt(i, 2).toString());
          }
        });

    // button update row
    btnUpdate.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = areaTable.getSelectedRow();
            String addId = areaTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              model.setValueAt(textId.getText(), i, 0);
              model.setValueAt(textName.getText(), i, 1);
              model.setValueAt(textMngId.getText(), i, 2);

              a.removeArea(addId);
              a.addArea(textId.getText(), textName.getText(), textMngId.getText());
            } else {
              System.out.println("Update Error");
            }
          }
        });
    // button print table
    btnPrint.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            printTable("Area.txt", areaTable);
          }
        });
  }

  // --------------------------------------------------------------------------------------------------------------

  /** Create Garden panel */
  public void itemGardenPanel() {

    int i;

    String b;
    String[] row = new String[8];

    GardenQueries a = new GardenQueries();
    Vector<Garden> all_garden = a.getAll();
    a.printAll();

    String[] columnNames = {
      "Id", "Name", "Address", "Area_Id", "Manager_Id", "Assist_1_Id", "Assist_2_Id", "Assist_3_Id"
    };

    gardenPanel = new JPanel();

    JTable gardenTable = new JTable();

    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);

    // set the model to the table
    gardenTable.setModel(model);

    // Change A JTable Background Color, Font Size, Font Color, Row Height
    gardenTable.setBackground(Color.LIGHT_GRAY);
    gardenTable.setForeground(Color.black);
    Font font = new Font("", 1, 22);
    gardenTable.setFont(font);
    gardenTable.setRowHeight(30);
    gardenPanel.add(gardenTable);
    gardenPanel.setSize(900, 500);

    gardenPanel.setVisible(true);
    JScrollPane pane = new JScrollPane(gardenTable);
    pane.setBounds(0, 0, 880, 200);
    gardenPanel.setLayout(null);
    gardenPanel.add(pane);
    for (i = 0; i < all_garden.size(); i++) {
      System.out.println(i);

      b = all_garden.elementAt(i).getId();
      row[0] = b;
      b = all_garden.elementAt(i).getName();
      row[1] = b;
      b = all_garden.elementAt(i).getAddress();
      row[2] = b;

      b = all_garden.elementAt(i).getAreaId();
      row[3] = b;

      b = all_garden.elementAt(i).getMngId();
      row[4] = b;

      b = all_garden.elementAt(i).getAss1Id();
      row[5] = b;

      b = all_garden.elementAt(i).getAss2Id();
      row[6] = b;

      b = all_garden.elementAt(i).getAss3Id();
      row[7] = b;
      model.addRow(row);
    }
    // create JTextFields
    JLabel labelId = new JLabel("ID");
    JLabel labelName = new JLabel("Name");
    JLabel labelAddress = new JLabel("Address");
    JLabel labelAreaId = new JLabel("Area ID");
    JLabel labelMngId = new JLabel("Mng ID");
    JLabel labelAss1Id = new JLabel("Assis ID1");
    JLabel labelAss2Id = new JLabel("Assis ID2");
    JLabel labelAss3Id = new JLabel("Assis ID3");

    JTextField textId = new JTextField();
    JTextField textName = new JTextField();
    JTextField textAddress = new JTextField();
    JTextField textAreaId = new JTextField();
    JTextField textMngId = new JTextField();
    JTextField textAss1Id = new JTextField();
    JTextField textAss2Id = new JTextField();
    JTextField textAss3Id = new JTextField();

    // create JButtons
    JButton btnAdd = new JButton("Add");
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnPrint = new JButton("Print");
    JButton btnPrintGardenChilds = new JButton("Print Garden Childs");
    JButton btnPrintAllGardens = new JButton("Print All Gardens");

    labelId.setBounds(0, 220, 100, 25);
    labelName.setBounds(0, 250, 100, 25);
    labelAddress.setBounds(0, 280, 100, 25);
    labelAreaId.setBounds(0, 310, 100, 25);
    labelMngId.setBounds(0, 340, 100, 25);
    labelAss1Id.setBounds(0, 370, 100, 25);
    labelAss2Id.setBounds(0, 400, 100, 25);
    labelAss3Id.setBounds(0, 430, 100, 25);

    textId.setBounds(130, 220, 100, 25);
    textName.setBounds(130, 250, 100, 25);
    textAddress.setBounds(130, 280, 100, 25);
    textAreaId.setBounds(130, 310, 100, 25);
    textMngId.setBounds(130, 340, 100, 25);
    textAss1Id.setBounds(130, 370, 100, 25);
    textAss2Id.setBounds(130, 400, 100, 25);
    textAss3Id.setBounds(130, 430, 100, 25);

    btnAdd.setBounds(270, 220, 100, 25);
    btnUpdate.setBounds(270, 265, 100, 25);
    btnDelete.setBounds(270, 310, 100, 25);
    btnPrint.setBounds(270, 355, 100, 25);
    btnPrintGardenChilds.setBounds(270, 400, 180, 25);
    btnPrintAllGardens.setBounds(270, 445, 180, 25);

    // add JTextFields to the jframe
    gardenPanel.add(textId);
    gardenPanel.add(textName);
    gardenPanel.add(textAddress);
    gardenPanel.add(textAreaId);
    gardenPanel.add(textMngId);
    gardenPanel.add(textAss1Id);
    gardenPanel.add(textAss2Id);
    gardenPanel.add(textAss3Id);

    gardenPanel.add(labelId);
    gardenPanel.add(labelName);
    gardenPanel.add(labelAddress);
    gardenPanel.add(labelAreaId);
    gardenPanel.add(labelMngId);
    gardenPanel.add(labelAss1Id);
    gardenPanel.add(labelAss2Id);
    gardenPanel.add(labelAss3Id);

    gardenPanel.add(btnAdd);
    gardenPanel.add(btnDelete);
    gardenPanel.add(btnUpdate);
    gardenPanel.add(btnPrint);
    gardenPanel.add(btnPrintGardenChilds);
    gardenPanel.add(btnPrintAllGardens);

    // button add row
    btnAdd.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            row[0] = textId.getText();
            row[1] = textName.getText();
            row[2] = textAddress.getText();
            row[3] = textAreaId.getText();
            row[4] = textMngId.getText();
            row[5] = textAss1Id.getText();
            row[6] = textAss2Id.getText();
            row[7] = textAss3Id.getText();

            // add row to the model
            model.addRow(row);
            a.addGarden(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
          }
        });
    // button remove row
    btnDelete.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = gardenTable.getSelectedRow();
            String delId = gardenTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              // remove a row from jtable
              model.removeRow(i);
              a.removeGarden(delId);
            } else {
              System.out.println("Delete Error");
            }
          }
        });

    // get selected row data From table to textfields
    gardenTable.addMouseListener(
        new MouseAdapter() {

          @Override
          public void mouseClicked(MouseEvent e) {

            // i = the index of the selected row
            int i = gardenTable.getSelectedRow();

            textId.setText(model.getValueAt(i, 0).toString());
            textName.setText(model.getValueAt(i, 1).toString());
            textAddress.setText(model.getValueAt(i, 2).toString());
            textAreaId.setText(model.getValueAt(i, 3).toString());
            textMngId.setText(model.getValueAt(i, 4).toString());
            textAss1Id.setText(model.getValueAt(i, 5).toString());
            textAss2Id.setText(model.getValueAt(i, 6).toString());
            textAss3Id.setText(model.getValueAt(i, 7).toString());
          }
        });

    // button update row
    btnUpdate.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = gardenTable.getSelectedRow();
            String addId = gardenTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              model.setValueAt(textId.getText(), i, 0);
              model.setValueAt(textName.getText(), i, 1);
              model.setValueAt(textAddress.getText(), i, 2);
              model.setValueAt(textAreaId.getText(), i, 3);
              model.setValueAt(textMngId.getText(), i, 4);
              model.setValueAt(textAss1Id.getText(), i, 5);
              model.setValueAt(textAss2Id.getText(), i, 6);
              model.setValueAt(textAss3Id.getText(), i, 7);

              a.removeGarden(addId);
              a.addGarden(
                  textId.getText(),
                  textName.getText(),
                  textAddress.getText(),
                  textAreaId.getText(),
                  textMngId.getText(),
                  textAss1Id.getText(),
                  textAss2Id.getText(),
                  textAss3Id.getText());
            } else {
              System.out.println("Update Error");
            }
          }
        });
    // button print table
    btnPrint.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            printTable("Garden.txt", gardenTable);
          }
        });
    // button print all child per garden
    btnPrintGardenChilds.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            int i = gardenTable.getSelectedRow();
            String garden_id = gardenTable.getModel().getValueAt(i, 0).toString();
            String mng_id = gardenTable.getModel().getValueAt(i, 4).toString();
            String ass_1_id = gardenTable.getModel().getValueAt(i, 5).toString();
            String ass_2_id = gardenTable.getModel().getValueAt(i, 6).toString();
            String ass_3_id = gardenTable.getModel().getValueAt(i, 7).toString();

            Vector<String> childs = a.ChildsPerGarden(garden_id);
            printChildTable(garden_id, mng_id, ass_1_id, ass_2_id, ass_3_id, childs);
            for (i = 0; i < childs.size(); i++) {
              System.out.println(childs.get(i));
            }
          }
        });

    // button print all Gardens
    btnPrintAllGardens.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            HashMap<String, String> h = a.GardensPerArea();
            printAllGardens(h);
          }
        });
  }
  // --------------------------------------------------------------------------------------------------------------

  /** Ctreate Stuff panel */
  public void itemStuffPanel() {
    int i;
    int startYearInt;
    int currentYear;
    int seniority;
    int addedHours;

    String b;
    String[] row = new String[10];

    StuffQueries a = new StuffQueries();
    Vector<Stuff> all_stuff = a.getAll();
    a.printAll();

    String[] columnNames = {
      "Id",
      "First Name",
      "Last Name",
      "Address",
      "City",
      "Phone",
      "Birth Year",
      "Start Year",
      "Added Hours",
      "Seniority"
    };

    stuffPanel = new JPanel();

    JTable stuffTable = new JTable();

    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);

    // set the model to the table
    stuffTable.setModel(model);

    // Change A JTable Background Color, Font Size, Font Color, Row Height
    stuffTable.setBackground(Color.LIGHT_GRAY);
    stuffTable.setForeground(Color.black);
    Font font = new Font("", 1, 22);
    stuffTable.setFont(font);
    stuffTable.setRowHeight(30);
    stuffPanel.add(stuffTable);
    stuffPanel.setSize(900, 500);

    stuffPanel.setVisible(true);
    JScrollPane pane = new JScrollPane(stuffTable);
    pane.setBounds(0, 0, 880, 200);
    stuffPanel.setLayout(null);
    stuffPanel.add(pane);
    for (i = 0; i < all_stuff.size(); i++) {
      System.out.println(i);

      b = all_stuff.elementAt(i).getId();
      row[0] = b;
      b = all_stuff.elementAt(i).getFirstName();
      row[1] = b;
      b = all_stuff.elementAt(i).getLastName();
      row[2] = b;

      b = all_stuff.elementAt(i).getAddress();
      row[3] = b;

      b = all_stuff.elementAt(i).getCity();
      row[4] = b;

      b = all_stuff.elementAt(i).getPhone();
      row[5] = b;

      b = all_stuff.elementAt(i).getBirthYear();
      row[6] = b;

      b = all_stuff.elementAt(i).getStartYear();
      row[7] = b;

      b = all_stuff.elementAt(i).getAddedHours();
      row[8] = b;

      startYearInt = Integer.parseInt(row[7]);
      currentYear = Year.now().getValue();
      seniority = currentYear - startYearInt;

      row[9] = Integer.toString(seniority);
      model.addRow(row);
    }
    // create JTextFields

    JLabel labelId = new JLabel("ID");
    JLabel labelFirstName = new JLabel("First Name");
    JLabel labelLastName = new JLabel("Last Name");
    JLabel labelAddress = new JLabel("Address");
    JLabel labelCity = new JLabel("City");
    JLabel labelPhone = new JLabel("Phone");
    JLabel labelBirthYear = new JLabel("Birth Year");
    JLabel labelStart = new JLabel("Start Year");
    JLabel labelAddedHours = new JLabel("Added Hours");

    JTextField textId = new JTextField();
    JTextField textFirstName = new JTextField();
    JTextField textLastName = new JTextField();
    JTextField textAddress = new JTextField();
    JTextField textCity = new JTextField();
    JTextField textPhone = new JTextField();
    JTextField textBirthYear = new JTextField();
    JTextField textStart = new JTextField();
    JTextField textAddedHours = new JTextField();

    // create JButtons
    JButton btnAdd = new JButton("Add");
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnPrint = new JButton("Print");
    JButton btnPrintAscPlc = new JButton("Print Work Places");

    labelId.setBounds(0, 220, 100, 25);
    labelFirstName.setBounds(0, 250, 100, 25);
    labelLastName.setBounds(0, 280, 100, 25);
    labelAddress.setBounds(0, 310, 100, 25);
    labelCity.setBounds(0, 340, 100, 25);
    labelPhone.setBounds(0, 370, 100, 25);
    labelBirthYear.setBounds(0, 400, 100, 25);
    labelStart.setBounds(0, 430, 100, 25);
    labelAddedHours.setBounds(0, 460, 100, 25);

    textId.setBounds(130, 220, 100, 25);
    textFirstName.setBounds(130, 250, 100, 25);
    textLastName.setBounds(130, 280, 100, 25);
    textAddress.setBounds(130, 310, 100, 25);
    textCity.setBounds(130, 340, 100, 25);
    textPhone.setBounds(130, 370, 100, 25);
    textBirthYear.setBounds(130, 400, 100, 25);
    textStart.setBounds(130, 430, 100, 25);
    textAddedHours.setBounds(130, 460, 100, 25);

    btnAdd.setBounds(270, 220, 100, 25);
    btnUpdate.setBounds(270, 265, 100, 25);
    btnDelete.setBounds(270, 310, 100, 25);
    btnPrint.setBounds(270, 355, 100, 25);
    btnPrintAscPlc.setBounds(270, 400, 150, 25);

    // add JTextFields to the jframe
    stuffPanel.add(textId);
    stuffPanel.add(textFirstName);
    stuffPanel.add(textLastName);
    stuffPanel.add(textAddress);
    stuffPanel.add(textCity);
    stuffPanel.add(textPhone);
    stuffPanel.add(textBirthYear);
    stuffPanel.add(textStart);
    stuffPanel.add(textAddedHours);

    stuffPanel.add(labelId);
    stuffPanel.add(labelFirstName);
    stuffPanel.add(labelLastName);
    stuffPanel.add(labelAddress);
    stuffPanel.add(labelCity);
    stuffPanel.add(labelPhone);
    stuffPanel.add(labelBirthYear);
    stuffPanel.add(labelStart);
    stuffPanel.add(labelAddedHours);

    stuffPanel.add(btnAdd);
    stuffPanel.add(btnDelete);
    stuffPanel.add(btnUpdate);
    stuffPanel.add(btnPrint);
    stuffPanel.add(btnPrintAscPlc);

    // button add row
    btnAdd.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            row[0] = textId.getText();
            row[1] = textFirstName.getText();
            row[2] = textLastName.getText();
            row[3] = textAddress.getText();
            row[4] = textCity.getText();
            row[5] = textPhone.getText();
            row[6] = textBirthYear.getText();
            row[7] = textStart.getText();
            row[8] = textAddedHours.getText();

            // add row to the model
            model.addRow(row);
            a.addStuff(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]);
          }
        });
    // button remove row
    btnDelete.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = stuffTable.getSelectedRow();
            String delId = stuffTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              // remove a row from jtable
              model.removeRow(i);
              a.removeStuff(delId);
            } else {
              System.out.println("Delete Error");
            }
          }
        });

    // get selected row data From table to textfields
    stuffTable.addMouseListener(
        new MouseAdapter() {

          @Override
          public void mouseClicked(MouseEvent e) {

            // i = the index of the selected row
            int i = stuffTable.getSelectedRow();

            textId.setText(model.getValueAt(i, 0).toString());
            textFirstName.setText(model.getValueAt(i, 1).toString());
            textLastName.setText(model.getValueAt(i, 2).toString());
            textAddress.setText(model.getValueAt(i, 3).toString());
            textCity.setText(model.getValueAt(i, 4).toString());
            textPhone.setText(model.getValueAt(i, 5).toString());
            textBirthYear.setText(model.getValueAt(i, 6).toString());
            textStart.setText(model.getValueAt(i, 7).toString());
            textAddedHours.setText(model.getValueAt(i, 8).toString());
          }
        });

    // button update row
    btnUpdate.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = stuffTable.getSelectedRow();
            String addId = stuffTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              model.setValueAt(textId.getText(), i, 0);
              model.setValueAt(textFirstName.getText(), i, 1);
              model.setValueAt(textLastName.getText(), i, 2);
              model.setValueAt(textAddress.getText(), i, 3);
              model.setValueAt(textCity.getText(), i, 4);
              model.setValueAt(textPhone.getText(), i, 5);
              model.setValueAt(textBirthYear.getText(), i, 6);
              model.setValueAt(textStart.getText(), i, 7);
              model.setValueAt(textAddedHours.getText(), i, 8);

              a.removeStuff(addId);
              a.addStuff(
                  textId.getText(),
                  textFirstName.getText(),
                  textLastName.getText(),
                  textAddress.getText(),
                  textCity.getText(),
                  textPhone.getText(),
                  textBirthYear.getText(),
                  textStart.getText(),
                  textAddedHours.getText());
            } else {
              System.out.println("Update Error");
            }
          }
        });
    // button print table
    btnPrint.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            printTable("Stuff.txt", stuffTable);
          }
        });

    // button print work places
    btnPrintAscPlc.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            int i = stuffTable.getSelectedRow();
            String stuff_id = stuffTable.getModel().getValueAt(i, 0).toString();
            String first_name = stuffTable.getModel().getValueAt(i, 1).toString();
            String last_name = stuffTable.getModel().getValueAt(i, 2).toString();

            Vector<String> mng_area_v = a.WorksPerStuffId(stuff_id, "mng_area");
            Vector<String> mng_garden_v = a.WorksPerStuffId(stuff_id, "mng_garden");
            Vector<String> ass_1_v = a.WorksPerStuffId(stuff_id, "ass_1");
            Vector<String> ass_2_v = a.WorksPerStuffId(stuff_id, "ass_2");
            Vector<String> ass_3_v = a.WorksPerStuffId(stuff_id, "ass_3");

            printStuffIdTable(
                stuff_id,
                first_name,
                last_name,
                mng_garden_v,
                ass_1_v,
                ass_2_v,
                ass_3_v,
                mng_area_v);
          }
        });
  }

  // --------------------------------------------------------------------------------------------------------------

  /** Create child panel */
  public void itemChildPanel() {
    int i;

    String b;
    String[] row = new String[9];

    ChildQueries a = new ChildQueries();
    Vector<Child> all_child = a.getAll();
    a.printAll();

    String[] columnNames = {
      "Id",
      "First Name",
      "Last Name",
      "Address",
      "Area",
      "Phone",
      "Birth Year",
      "Start Year",
      "Garden ID"
    };

    childPanel = new JPanel();

    JTable childTable = new JTable();

    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);

    // set the model to the table
    childTable.setModel(model);

    // Change A JTable Background Color, Font Size, Font Color, Row Height
    childTable.setBackground(Color.LIGHT_GRAY);
    childTable.setForeground(Color.black);
    Font font = new Font("", 1, 22);
    childTable.setFont(font);
    childTable.setRowHeight(30);
    childPanel.add(childTable);
    childPanel.setSize(900, 500);

    childPanel.setVisible(true);
    JScrollPane pane = new JScrollPane(childTable);
    pane.setBounds(0, 0, 880, 200);
    childPanel.setLayout(null);
    childPanel.add(pane);
    for (i = 0; i < all_child.size(); i++) {
      System.out.println(i);

      b = all_child.elementAt(i).getId();
      row[0] = b;
      b = all_child.elementAt(i).getFirstName();
      row[1] = b;
      b = all_child.elementAt(i).getLastName();
      row[2] = b;

      b = all_child.elementAt(i).getAddress();
      row[3] = b;

      b = all_child.elementAt(i).getAreaId();
      row[4] = b;

      b = all_child.elementAt(i).getPhone();
      row[5] = b;

      b = all_child.elementAt(i).getBirthYear();
      row[6] = b;

      b = all_child.elementAt(i).getStartYear();
      row[7] = b;

      b = all_child.elementAt(i).getGardenId();
      row[8] = b;

      model.addRow(row);
    }
    // create JTextFields
    JLabel labelId = new JLabel("ID");
    JLabel labelFirstName = new JLabel("First Name");
    JLabel labelLastName = new JLabel("Last Name");
    JLabel labelAddress = new JLabel("Address");
    JLabel labelAreaId = new JLabel("Area ID");
    JLabel labelPhone = new JLabel("Phone");
    JLabel labelBirthYear = new JLabel("Birth Year");
    JLabel labelStart = new JLabel("Start Year");
    JLabel labelGardenId = new JLabel("Garden ID");

    JTextField textId = new JTextField();
    JTextField textFirstName = new JTextField();
    JTextField textLastName = new JTextField();
    JTextField textAddress = new JTextField();
    JTextField textAreaId = new JTextField();
    JTextField textPhone = new JTextField();
    JTextField textBirthYear = new JTextField();
    JTextField textStart = new JTextField();
    JTextField textGardenId = new JTextField();

    // create JButtons
    JButton btnAdd = new JButton("Add");
    JButton btnDelete = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnPrint = new JButton("Print");

    labelId.setBounds(0, 220, 100, 25);
    labelFirstName.setBounds(0, 250, 100, 25);
    labelLastName.setBounds(0, 280, 100, 25);
    labelAddress.setBounds(0, 310, 100, 25);
    labelAreaId.setBounds(0, 340, 100, 25);
    labelPhone.setBounds(0, 370, 100, 25);
    labelBirthYear.setBounds(0, 400, 100, 25);
    labelStart.setBounds(0, 430, 100, 25);
    labelGardenId.setBounds(0, 460, 100, 25);

    textId.setBounds(130, 220, 100, 25);
    textFirstName.setBounds(130, 250, 100, 25);
    textLastName.setBounds(130, 280, 100, 25);
    textAddress.setBounds(130, 310, 100, 25);
    textAreaId.setBounds(130, 340, 100, 25);
    textPhone.setBounds(130, 370, 100, 25);
    textBirthYear.setBounds(130, 400, 100, 25);
    textStart.setBounds(130, 430, 100, 25);
    textGardenId.setBounds(130, 460, 100, 25);

    btnAdd.setBounds(270, 220, 100, 25);
    btnUpdate.setBounds(270, 265, 100, 25);
    btnDelete.setBounds(270, 310, 100, 25);
    btnPrint.setBounds(270, 355, 100, 25);

    // add JTextFields to the jframe
    childPanel.add(textId);
    childPanel.add(textFirstName);
    childPanel.add(textLastName);
    childPanel.add(textAddress);
    childPanel.add(textAreaId);
    childPanel.add(textPhone);
    childPanel.add(textBirthYear);
    childPanel.add(textStart);
    childPanel.add(textGardenId);

    childPanel.add(labelId);
    childPanel.add(labelFirstName);
    childPanel.add(labelLastName);
    childPanel.add(labelAddress);
    childPanel.add(labelAreaId);
    childPanel.add(labelPhone);
    childPanel.add(labelBirthYear);
    childPanel.add(labelStart);
    childPanel.add(labelGardenId);

    childPanel.add(btnAdd);
    childPanel.add(btnDelete);
    childPanel.add(btnUpdate);
    childPanel.add(btnPrint);

    // button add row
    btnAdd.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            row[0] = textId.getText();
            row[1] = textFirstName.getText();
            row[2] = textLastName.getText();
            row[3] = textAddress.getText();
            row[4] = textAreaId.getText();
            row[5] = textPhone.getText();
            row[6] = textBirthYear.getText();
            row[7] = textStart.getText();
            row[8] = textGardenId.getText();

            ok = a.isMaxLimit(textGardenId.getText());
            idOk = a.isChildIdNotExists(textId.getText());

            if (ok) {
              if (idOk) {
                // add row to the model
                model.addRow(row);
                a.addChild(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]);
              } else {
                JOptionPane.showMessageDialog(childPanel, "Child Id exists.");
              }

            } else {
              JOptionPane.showMessageDialog(
                  childPanel, "This garden limit the 20 childs, try other Garden.");
            }
          }
        });
    // button remove row
    btnDelete.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = childTable.getSelectedRow();
            String delId = childTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              // remove a row from jtable
              model.removeRow(i);
              a.removeChild(delId);
            } else {
              System.out.println("Delete Error");
            }
          }
        });

    // get selected row data From table to textfields
    childTable.addMouseListener(
        new MouseAdapter() {

          @Override
          public void mouseClicked(MouseEvent e) {

            // i = the index of the selected row
            int i = childTable.getSelectedRow();

            textId.setText(model.getValueAt(i, 0).toString());
            textFirstName.setText(model.getValueAt(i, 1).toString());
            textLastName.setText(model.getValueAt(i, 2).toString());
            textAddress.setText(model.getValueAt(i, 3).toString());
            textAreaId.setText(model.getValueAt(i, 4).toString());
            textPhone.setText(model.getValueAt(i, 5).toString());
            textBirthYear.setText(model.getValueAt(i, 6).toString());
            textStart.setText(model.getValueAt(i, 7).toString());
            textGardenId.setText(model.getValueAt(i, 8).toString());
          }
        });

    // button update row
    btnUpdate.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {

            // i = the index of the selected row
            int i = childTable.getSelectedRow();
            String addId = childTable.getModel().getValueAt(i, 0).toString();
            if (i >= 0) {
              model.setValueAt(textId.getText(), i, 0);
              model.setValueAt(textFirstName.getText(), i, 1);
              model.setValueAt(textLastName.getText(), i, 2);
              model.setValueAt(textAddress.getText(), i, 3);
              model.setValueAt(textAreaId.getText(), i, 4);
              model.setValueAt(textPhone.getText(), i, 5);
              model.setValueAt(textBirthYear.getText(), i, 6);
              model.setValueAt(textStart.getText(), i, 7);
              model.setValueAt(textGardenId.getText(), i, 8);

              ok = a.isMaxLimit(textGardenId.getText());
              if (ok) {
                a.removeChild(addId);
                a.addChild(
                    textId.getText(),
                    textFirstName.getText(),
                    textLastName.getText(),
                    textAddress.getText(),
                    textAreaId.getText(),
                    textPhone.getText(),
                    textBirthYear.getText(),
                    textStart.getText(),
                    textGardenId.getText());
              } else {
                JOptionPane.showMessageDialog(
                    childPanel, "This garden limit the 20 childs, try other Garden.");
              }
            } else {
              System.out.println("Update Error");
            }
          }
        });
    // button print table
    btnPrint.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            printTable("Child.txt", childTable);
          }
        });
  }

  /**
   * Print table JTable content content for each panel
   *
   * @param fileName
   * @param table
   */
  public void printTable(String fileName, JTable table) {
    try {
      // the file path
      File file = new File(fileName);
      // if the file not exist create one
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      // loop for jtable rows
      for (int i = 0; i < table.getRowCount(); i++) {
        // loop for jtable column
        for (int j = 0; j < table.getColumnCount(); j++) {
          bw.write(table.getModel().getValueAt(i, j) + " ");
        }
        // break line at the begin
        // break line at the end
        bw.write("\n_________\n");
      }
      // close BufferedWriter
      bw.close();
      // close FileWriter
      fw.close();
      JOptionPane.showMessageDialog(null, "Data Exported");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void printChildTable(
      String garden_id,
      String mng_id,
      String ass_1_id,
      String ass_2_id,
      String ass_3_id,
      Vector<String> childs) {
    try {
      // the file path
      File file = new File("Childs_per_Garden.txt");
      // if the file not exist create one
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      bw.write("Children List For Garden " + garden_id + " :\n");
      // loop for jtable rows
      for (int i = 0; i < childs.size(); i++) {
        bw.write(childs.get(i));
        bw.write("\n_________\n");
      }
      bw.write("Stuff List :\n");
      // loop for jtable rows
      bw.write("Manager ID: " + mng_id + "\n");
      bw.write("Manager ID: " + ass_1_id + "\n");
      bw.write("Manager ID: " + ass_2_id + "\n");
      bw.write("Manager ID: " + ass_3_id + "\n");

      // close BufferedWriter
      bw.close();
      // close FileWriter
      fw.close();
      JOptionPane.showMessageDialog(null, "Data Exported");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void printStuffIdTable(
      String stuff_id,
      String first_name,
      String last_name,
      Vector<String> mng_garden,
      Vector<String> ass_1,
      Vector<String> ass_2,
      Vector<String> ass_3,
      Vector<String> mng_area) {

    try {
      // the file path
      File file = new File("Works_per_Stuff_id.txt");
      // if the file not exist create one
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      bw.write("Worker: " + stuff_id + " " + first_name + " " + last_name + ":\n");
      bw.write("Garden manager:\n\n");
      for (int i = 0; i < mng_garden.size(); i++) {
        bw.write(mng_garden.get(i) + "\n");
      }
      bw.write("\n");

      bw.write("Assistent 1:\n");
      for (int i = 0; i < ass_1.size(); i++) {
        bw.write(ass_1.get(i) + "\n");
      }
      bw.write("\n");

      bw.write("Assistent 2:\n");
      for (int i = 0; i < ass_2.size(); i++) {
        bw.write(ass_2.get(i) + "\n");
      }
      bw.write("\n");

      bw.write("Assistent 3:\n");
      for (int i = 0; i < ass_3.size(); i++) {
        bw.write(ass_3.get(i) + "\n");
      }
      bw.write("\n");

      bw.write("Area management:\n");
      for (int i = 0; i < mng_area.size(); i++) {
        bw.write(mng_area.get(i) + "\n");
      }
      bw.write("\n");

      // close BufferedWriter
      bw.close();
      // close FileWriter
      fw.close();
      JOptionPane.showMessageDialog(null, "Data Exported");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void printAllGardens(HashMap<String, String> h) {
    try {
      // the file path
      File file = new File("All_Gardens.txt");
      // if the file not exist create one
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      Set<String> keys = h.keySet();
      for (String k : keys) {
        bw.write("Area:" + k + "\n");
        bw.write(h.get(k) + "\n");
        System.out.println("Value of " + k + " is: " + h.get(k));
      }

      // close BufferedWriter
      bw.close();
      // close FileWriter
      fw.close();
      JOptionPane.showMessageDialog(null, "Data Exported");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
