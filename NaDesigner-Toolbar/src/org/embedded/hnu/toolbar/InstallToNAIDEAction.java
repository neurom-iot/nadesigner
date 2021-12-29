package org.embedded.hnu.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static org.embedded.hnu.main.dialog.SpikingNeuralNetworkDialog.userDir;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "NaDesigner",
        id = "org.embedded.hnu.toolbar.InstallToNAIDEAction"
)
@ActionRegistration(
        displayName = "#CTL_InstallToNAIDEAction"
)
@ActionReference(path = "Menu/Install", position = 200)
@Messages("CTL_InstallToNAIDEAction=Install to NAIDE")
public final class InstallToNAIDEAction implements ActionListener {

    private final Project context;

    public InstallToNAIDEAction(Project context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String projDir = FileUtil.getFileDisplayName(context.getProjectDirectory());
        String noderedDir = userDir + File.separatorChar + ".node-red";
        String noderedStartDir = userDir + File.separatorChar + "node-red";
        String[] cmd = new String[]{"cmd", "/c", "c:&cd/&cd "+noderedDir+"&npm install "+projDir+" --no-fund --no-audit"};
        
        String str = null;
        
        Process process;
        
        try{
            process = new ProcessBuilder(cmd).start();
            BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
            
            while( (str = stdOut.readLine()) != null ) {
                System.out.println(str);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
