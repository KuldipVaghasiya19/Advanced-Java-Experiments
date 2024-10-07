TelephoneDirectoryAdminApp.java
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelephoneDirectoryAdminApp extends Frame {
    private TextField idField, nameField, cityField, stateField, pincodeField, productField, classField, deleteIdField;
    private TextArea resultArea;

    public TelephoneDirectoryAdminApp() {
        setTitle("Telephone Directory Admin");
        setSize(600, 500);
        setLayout(new GridLayout(4, 1));

        // Create insert panel
        Panel insertPanel = new Panel();
        insertPanel.setLayout(new GridLayout(8, 2, 5, 5));

        insertPanel.add(new Label("Customer ID:"));
        idField = new TextField();
        insertPanel.add(idField);

        insertPanel.add(new Label("Name:"));
        nameField = new TextField();
        insertPanel.add(nameField);
 
 

        insertPanel.add(new Label("City:"));
        cityField = new TextField();
        insertPanel.add(cityField);

        insertPanel.add(new Label("State:"));
        stateField = new TextField();
        insertPanel.add(stateField);

        insertPanel.add(new Label("Pincode:"));
        pincodeField = new TextField();
        insertPanel.add(pincodeField);

        insertPanel.add(new Label("Product:"));
        productField = new TextField();
        insertPanel.add(productField);

        insertPanel.add(new Label("Class:"));
        classField = new TextField();
        insertPanel.add(classField);

        Button insertButton = new Button("Insert");
        insertButton.addActionListener(new InsertButtonListener());
        insertPanel.add(insertButton);

        add(insertPanel);

        // Create delete panel
        Panel deletePanel = new Panel();
        deletePanel.setLayout(new GridLayout(2, 2, 5, 5));
        deletePanel.add(new Label("Delete Customer ID:"));
        deleteIdField = new TextField();
        deletePanel.add(deleteIdField);

        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(new DeleteButtonListener());
        deletePanel.add(deleteButton);

        add(deletePanel);

        // Create result area
        resultArea = new TextArea();
        resultArea.setEditable(false);
        add(resultArea);

        // Set window close operation
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private class InsertButtonListener implements ActionListener {
 

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String name = nameField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String pincode = pincodeField.getText();
            String product = productField.getText();
            String classType = classField.getText();

            if (id.isEmpty() || name.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty() || product.isEmpty() || classType.isEmpty()) {
                showErrorDialog("All fields are required for insertion.");
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO CUSTOMERS (CID, CNAME, CITY, STATE, PINCODE, PRODUCT, CLASS) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, city);
                stmt.setString(4, state);
                stmt.setInt(5, Integer.parseInt(pincode));
                stmt.setString(6, product);
                stmt.setString(7, classType);
                int rowsInserted = stmt.executeUpdate();
                resultArea.setText("Inserted " + rowsInserted + " row(s).");
            } catch (SQLException ex) {
                ex.printStackTrace();
                showErrorDialog("Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                showErrorDialog("Invalid pincode format.");
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = deleteIdField.getText();

            if (id.isEmpty()) {
                showErrorDialog("Customer ID is required for deletion.");
                return;
            }

            // Verification step
            Dialog verifyDialog = new Dialog(TelephoneDirectoryAdminApp.this, "Verify Deletion", true);
            verifyDialog.setLayout(new FlowLayout());
            verifyDialog.setSize(300, 150);
            verifyDialog.add(new Label("Are you sure you want to delete customer ID: " + id + "?"));
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");

            yesButton.addActionListener(event -> {
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM CUSTOMERS WHERE CID = ?")) {
                    stmt.setString(1, id);
                    int rowsDeleted = stmt.executeUpdate();
                    resultArea.setText("Deleted " + rowsDeleted + " row(s).");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    showErrorDialog("Error: " + ex.getMessage());
                }
                verifyDialog.setVisible(false);
            });

            noButton.addActionListener(event -> verifyDialog.setVisible(false));

            verifyDialog.add(yesButton);
            verifyDialog.add(noButton);
            verifyDialog.setVisible(true);
        }
    }

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog(this, "Error", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 150);
        dialog.add(new Label(message));
        Button okButton = new Button("OK");
        okButton.addActionListener(event -> dialog.setVisible(false));
        dialog.add(okButton);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new TelephoneDirectoryAdminApp().setVisible(true);
    }
}

class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
        String url = "jdbc:mysql://localhost:3306/shopping";
        String user = "root";
        String password = "Kuldip19";
        return DriverManager.getConnection(url, user, password);
    }
}
