import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {
    String[] header;
    List<HashMap<String, String>> data;
    JTable table;

    public Table() {

        header = new String[]{"name", "age"};
        String[][] data = new String[2][2];
//        for (int i = 0; i < data.size(); ++i) {
//
//        }

//        String[][] data = {{"akash", "20"}, {"pankaj", "24"}, {"pankaj", "24"}, {"pankaj", "24"}, {"pankaj", "24"}};

        DefaultTableModel model = new DefaultTableModel(data, header);

        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(450, 63));
        table.setFillsViewportHeight(true);

        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);
        add(js);
    }
}