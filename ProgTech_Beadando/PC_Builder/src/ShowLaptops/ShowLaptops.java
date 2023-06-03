package ShowLaptops;
import org.apache.log4j.Logger;
import DBconnection.DBconnection;
import DBconnection.GetAllBuiltLaptopCommand;
import DBconnection.DeleteSelectedLaptopCommand;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class ShowLaptops {
    private Logger logger = Logger.getLogger("Show all built Laptops");
    private JPanel LaptopPanel;
    private JTable tableLaptops;
    private JButton btn_Refresh;
    private JButton btn_deleteSelected;
    private JButton btn_back;
    private JFrame frame;
    private String id;
    private DBconnection dBconnection;

    public ShowLaptops(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
        ConfigureFrame();
        CreateTable();
        RegisterListeners();
    }
    private void ConfigureFrame(){
        this.frame = new JFrame();
        frame.setContentPane(this.LaptopPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
        logger.info("Show Laptop frame configured");
    }
    private void RegisterListeners(){
        btn_back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Back();
            }
        });
        btn_deleteSelected.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DeleteSelected();
            }
        });
        btn_Refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RefreshTable();
            }
        });
    }
    private void CreateTable(){
        GetAllBuiltLaptopCommand getAllBuiltLaptopCommand = new GetAllBuiltLaptopCommand(
                this.dBconnection
        );
        getAllBuiltLaptopCommand.execute();

        DefaultTableModel model = new DefaultTableModel();
        tableLaptops.getTableHeader().setReorderingAllowed(false);
        model.addColumn("ID");
        model.addColumn("Alaplap");
        model.addColumn("CPU");
        model.addColumn("RAM");
        model.addColumn("GPU");
        model.addColumn("Háttértár");
        model.addColumn("Monitor");
        model.addColumn("Billentyűzet");
        model.addColumn("Touchpad");
        try{
            ResultSet resultSet = getAllBuiltLaptopCommand.getResults();

            while (resultSet.next()){
                id = resultSet.getString(1);
                String mobo = resultSet.getString(2);
                String cpu = resultSet.getString(3);
                String ram = resultSet.getString(4);
                String gpu = resultSet.getString(5);
                String hattertar = resultSet.getString(6);
                String monitor = resultSet.getString(7);
                String bill = resultSet.getString(8);
                String touch = resultSet.getString(9);
                String[] row = {id,mobo,cpu,ram, gpu,hattertar,monitor,bill,touch};
                model.addRow(row);
            }
            this.tableLaptops.setModel(model);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        this.tableLaptops.setModel(model);

    }
    private void DeleteSelected(){
        int row = tableLaptops.getSelectedRow();
        if(row !=-1){
            String selectedID = tableLaptops.getValueAt(row,0).toString();
            DeleteSelectedLaptopCommand deleteSelectedLaptopCommand = new DeleteSelectedLaptopCommand(
                    Integer.parseInt(selectedID),this.dBconnection
            );
            deleteSelectedLaptopCommand.execute();
            RefreshTable();
            return;
        }
        JOptionPane.showMessageDialog(frame,"Nincs kiválasztott elem!","HIBA",JOptionPane.ERROR_MESSAGE);
    }
    private void RefreshTable(){
        logger.info("Built Laptops table refreshed!");
        ClearTable();
        CreateTable();
    }
    private void ClearTable(){
        this.tableLaptops.setModel(new DefaultTableModel());
        logger.info("Laptops table cleared");
    }
    private void Back(){
        this.frame.dispose();
        logger.info("Show Laptop frame closed");
    }
}
