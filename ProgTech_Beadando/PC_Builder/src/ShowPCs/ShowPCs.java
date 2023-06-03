package ShowPCs;
import org.apache.log4j.Logger;

import DBconnection.DBconnection;
import DBconnection.GetAllBuiltPcCommand;
import DBconnection.DeleteSelectedPcCommand;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class ShowPCs {
    private Logger logger = Logger.getLogger("Show all built PCs");
    private JPanel PCpanel;
    private JTable tablePCs;
    private JButton btn_Refresh;
    private JButton btn_back;
    private JButton btn_deleteSelected;
    private JFrame frame;
    private DBconnection dBconnection;
    private String id;

    public ShowPCs(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
        ConfigureFrame();
        CreateTable();
        RegisterListeners();
    }
    private void ConfigureFrame(){
        this.frame = new JFrame();
        frame.setContentPane(this.PCpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
        logger.info("Show PC frame configured");
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
        GetAllBuiltPcCommand getAllBuiltPcCommand = new GetAllBuiltPcCommand(
                this.dBconnection
        );
        getAllBuiltPcCommand.execute();

        DefaultTableModel model = new DefaultTableModel();
        tablePCs.getTableHeader().setReorderingAllowed(false);
        model.addColumn("ID");
        model.addColumn("Alaplap");
        model.addColumn("CPU");
        model.addColumn("RAM");
        model.addColumn("GPU");
        model.addColumn("Hattertar");
        model.addColumn("Tap");
        model.addColumn("Gephaz");
        try{
            ResultSet resultSet = getAllBuiltPcCommand.getResults();

            while (resultSet.next()){
                id = resultSet.getString(1);
                String mobo = resultSet.getString(2);
                String cpu = resultSet.getString(3);
                String ram = resultSet.getString(4);
                String gpu = resultSet.getString(5);
                String hattertar = resultSet.getString(6);
                String tap = resultSet.getString(7);
                String gephaz = resultSet.getString(8);
                String[] row = {id,mobo,cpu,ram, gpu,hattertar,tap,gephaz};
                model.addRow(row);
            }
            this.tablePCs.setModel(model);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    private void DeleteSelected(){
        int row = tablePCs.getSelectedRow();
        if(row !=-1){
            String selectedID = tablePCs.getValueAt(row,0).toString();
            DeleteSelectedPcCommand deleteSelectedPcCommand = new DeleteSelectedPcCommand(
                    Integer.parseInt(selectedID),this.dBconnection
            );
            deleteSelectedPcCommand.execute();
            RefreshTable();
            return;
        }
        JOptionPane.showMessageDialog(frame,"Nincs kiválasztott elem!","HIBA",JOptionPane.ERROR_MESSAGE);
    }
    private void RefreshTable(){
        logger.info("Built PCs table refreshed!");
        ClearTable();
        CreateTable();
    }
    private void ClearTable(){
        this.tablePCs.setModel(new DefaultTableModel());
        logger.info("PCs table cleared");
    }
    private void Back(){
        this.frame.dispose();
        logger.info("Show PC frame closed");
    }
}
