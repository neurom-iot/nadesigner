package org.embedded.hnu.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.embedded.hnu.generator.HGenerator;
import org.embedded.hnu.generator.ai.HSNNGenRun;
import org.embedded.hnu.main.dialog.SpikingNeuralNetworkDialog;
import org.embedded.hnu.main.dialog.ArtificialNeuralNetworkDialog;
import org.embedded.hnu.main.dialog.ParameterWarningDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;


@ActionID(
        category = "NaDesigner",
        id = "org.embedded.hnu.toolbar.SetNetworkToolbarAction"
)
@ActionRegistration(
        iconBase = "org/embedded/hnu/toolbar/icons/icon_Net24.png",
        displayName = "#CTL_SetNetworkToolbarAction"
)
@ActionReference(path = "Toolbars/NaDesigner", position = 100)
@Messages("CTL_SetNetworkToolbarAction=Set Network")
public final class SetNetworkToolbarAction implements ActionListener {
    
    private final Project context;
    
    public SetNetworkToolbarAction(Project context) {
        this.context = context;
    }
    
    public static final int H_SNN = 1;
    public static final int H_ANN = 2;
    public static final int H_IoT = 3; 
    
    public static final int H_IoT_Param_Error = 2;    
    
    private int errorCode=0;
    
    HGenerator hGenerator = new HGenerator();
    HSNNGenRun hSnnGenRun = new HSNNGenRun();
    private String hDir = "";
    private int hState = 0;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println("--------- click SetNetworkBtn ---------");
        FileObject f = context.getProjectDirectory();
        
        hDir = FileUtil.getFileDisplayName(f);
        
        File hAnnDirectory = new File(hDir + File.separatorChar + "ann");
        File hSnnDirectory = new File(hDir + File.separatorChar + "snn");
        
        if (hAnnDirectory.isDirectory())
            hState = H_ANN;
        else if (hSnnDirectory.isDirectory())
            hState = H_SNN;
        else
            hState = H_IoT;
        
        System.out.println("Directory::"+hDir);
        if(hState==H_ANN){    
            System.out.println("ANN:"+hState);
            showArtificialNeuralNetworkDialog(hDir);
        }
        else if(hState==H_SNN){
            System.out.println("SNN:"+hState);
            showSpikingNeuralNetowrkDialog(hDir);
        }
        else{
            errorCode = H_IoT_Param_Error;
            showParameterWarningDialog(errorCode);
        }
    }
     
    private void showArtificialNeuralNetworkDialog(String hDirectory){
        System.out.println("------- SetNetworkToolbarAction -------");
        ArtificialNeuralNetworkDialog dialog = new ArtificialNeuralNetworkDialog(null, true, hDirectory);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private void showSpikingNeuralNetowrkDialog(String hDirectory){
        System.out.println("------- SetNetworkToolbarAction -------");
        SpikingNeuralNetworkDialog dialog = new SpikingNeuralNetworkDialog(null, true, hDirectory);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private void showParameterWarningDialog(int errorCode){
        ParameterWarningDialog dialog = new ParameterWarningDialog(null, true, errorCode);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    
    }
}