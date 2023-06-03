package MainPage;
import AddNew.AddNew;
import DBconnection.DBconnection;
import DBconnection.GetAllBuiltPcCommand;
import DBconnection.GetAllBuiltLaptopCommand;
import ShowLaptops.ShowLaptops;
import ShowPCs.ShowPCs;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage {
    private static Logger logger = Logger.getLogger("Main page logger");
    private JPanel mainPanel;
    private JButton btn_ShowBuiltPC;
    private JButton btn_ShowBuiltLaptops;
    private JButton btn_Build;

    public MainPage() {
        JFrame frame = CreateFrame();
        ConfigureJFrame(frame);
        RegisterListeners();
    }
    private JFrame CreateFrame(){
        logger.info("Main page created");
        return new JFrame();
    }
    private void ConfigureJFrame(JFrame frame){
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        logger.info("Main page configured");
    }
    private DBconnection CreateConnection(){
        return new DBconnection("jdbc:mysql://localhost:3306/pc_builder",
                "root",
                "");
    }
    private void RegisterListeners(){
        btn_ShowBuiltPC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Show built PCs button clicked");
                ShowPCs showPCs = new ShowPCs(CreateConnection());
            }
        });
        btn_ShowBuiltLaptops.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Show built Laptops button clicked");
                ShowLaptops showLaptops = new ShowLaptops(CreateConnection());
            }
        });
        btn_Build.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Show builder clicker");
                AddNew addNew = new AddNew(CreateConnection());
            }
        });
    }

}
