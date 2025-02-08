import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class MainForm extends JFrame {
    private JTextField claveField, nombreEmpresaField, rfcField, telefonoField, emailField;
    private JComboBox<String> estatusComboBox; // Usamos un JComboBox para el Estatus
    private JButton insertButton, updateButton, deleteButton, listButton;
    private JTable table;
    private JScrollPane scrollPane;

    public MainForm() {
        setTitle("CRUD Clientes");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));


        claveField = new JTextField(20);
        nombreEmpresaField = new JTextField(20);
        rfcField = new JTextField(20);
        telefonoField = new JTextField(20);
        emailField = new JTextField(20);
        estatusComboBox = new JComboBox<>(new String[]{"A", "B", "M", "S"});
        estatusComboBox.setSelectedIndex(0);


        formPanel.add(new JLabel("Clave:"));
        formPanel.add(claveField);
        formPanel.add(new JLabel("Nombre de la empresa:"));
        formPanel.add(nombreEmpresaField);
        formPanel.add(new JLabel("RFC:"));
        formPanel.add(rfcField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Estatus:"));
        formPanel.add(estatusComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Botones centrados con espacios de 10px

        insertButton = new JButton("Insertar");
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");
        listButton = new JButton("Listar");

        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);


        table = new JTable();
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertCliente();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCliente();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCliente();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listClientes();
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseConnection.getInstance().closeConnection();
                System.exit(0);
            }
        });
    }

    private void insertCliente() {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO CLIE01 (CLAVE, NOMBRE, RFC, TELEFONO, EMAILPRED, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, claveField.getText());
            pstmt.setString(2, nombreEmpresaField.getText());
            pstmt.setString(3, rfcField.getText());
            pstmt.setString(4, telefonoField.getText());
            pstmt.setString(5, emailField.getText());
            pstmt.setString(6, (String) estatusComboBox.getSelectedItem());
            pstmt.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(this, "Cliente insertado correctamente");
            listClientes();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateCliente() {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);

            String sql = "UPDATE CLIE01 SET NOMBRE = ?, RFC = ?, TELEFONO = ?, EMAILPRED = ?, STATUS = ? WHERE CLAVE = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreEmpresaField.getText());
            pstmt.setString(2, rfcField.getText());
            pstmt.setString(3, telefonoField.getText());
            pstmt.setString(4, emailField.getText());
            pstmt.setString(5, (String) estatusComboBox.getSelectedItem());
            pstmt.setString(6, claveField.getText());
            pstmt.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
            listClientes();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteCliente() {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);

            String sql = "DELETE FROM CLIE01 WHERE CLAVE = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, claveField.getText());
            pstmt.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
            listClientes();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void listClientes() {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT CLAVE, NOMBRE, RFC, TELEFONO, EMAILPRED, STATUS FROM CLIE01";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();


            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();


            Vector<Vector<Object>> data = new Vector<>();
            Vector<String> columnNames = new Vector<>();


            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }


            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }


            table.setModel(new DefaultTableModel(data, columnNames));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}