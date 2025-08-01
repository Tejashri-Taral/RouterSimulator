import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.UnknownHostException;

public class RouterGUI extends JFrame {
    private Router router;
    private DefaultTableModel tableModel;
    private JTextField destField, maskField, hopField, metricField, packetDestField;
    private JTextArea outputArea;

    public RouterGUI() {
        super("Simple Router Simulator");
        router = new Router();

        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 5, 5));
        destField = new JTextField();
        maskField = new JTextField();
        hopField = new JTextField();
        metricField = new JTextField();
        JButton addButton = new JButton("Add Route");

        inputPanel.add(new JLabel("Destination"));
        inputPanel.add(new JLabel("Subnet Mask"));
        inputPanel.add(new JLabel("Next Hop"));
        inputPanel.add(new JLabel("Metric"));
        inputPanel.add(new JLabel());  // spacer

        inputPanel.add(destField);
        inputPanel.add(maskField);
        inputPanel.add(hopField);
        inputPanel.add(metricField);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Routing table
        tableModel = new DefaultTableModel(new Object[]{"Destination", "Mask", "Next Hop", "Metric"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel sendPanel = new JPanel();
        packetDestField = new JTextField(15);
        JButton sendButton = new JButton("Send Packet");
        JButton deleteButton = new JButton("Delete Selected Route");

        sendPanel.add(new JLabel("Destination IP:"));
        sendPanel.add(packetDestField);
        sendPanel.add(sendButton);
        sendPanel.add(deleteButton);

        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        bottomPanel.add(sendPanel, BorderLayout.NORTH);
        bottomPanel.add(outputScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event handling
        addButton.addActionListener(e -> {
            try {
                String dest = destField.getText();
                String mask = maskField.getText();
                String hop = hopField.getText();
                int metric = Integer.parseInt(metricField.getText());
                router.addRoute(new RouteEntry(dest, mask, hop, metric));
                tableModel.addRow(new Object[]{dest, mask, hop, metric});
                log("Added route: " + dest);
                clearFields();
            } catch (NumberFormatException ex) {
                log("Metric must be a number.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String dest = (String) tableModel.getValueAt(selectedRow, 0);
                router.deleteRoute(dest);
                tableModel.removeRow(selectedRow);
                log("Deleted route: " + dest);
            } else {
                log("No route selected.");
            }
        });

        sendButton.addActionListener(e -> {
            String destIp = packetDestField.getText();
            try {
                router.forwardPacket(destIp);
            } catch (UnknownHostException ex) {
                log("Invalid destination IP: " + destIp);
            }
        });

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void clearFields() {
        destField.setText("");
        maskField.setText("");
        hopField.setText("");
        metricField.setText("");
    }

    private void log(String message) {
        outputArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RouterGUI::new);
    }
}

