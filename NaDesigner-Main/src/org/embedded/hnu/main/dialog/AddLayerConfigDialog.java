package org.embedded.hnu.main.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.ws.Action;
import org.embedded.hnu.generator.ai.HSNNGenerator;
import org.embedded.hnu.generator.file.HFunctionGenerator;
import org.embedded.hnu.generator.file.HManifestGenerator;
import org.embedded.hnu.generator.file.HUIGenerator;
import org.embedded.hnu.ioconfig.ai.HSNNIOConfig;
import org.embedded.hnu.ioconfig.ai.HSNNLayerConfig;
import static org.embedded.hnu.main.dialog.SpikingNeuralNetworkDialog.LOADDATA;
import static org.embedded.hnu.main.dialog.SpikingNeuralNetworkDialog.srcFile;
import static org.embedded.hnu.main.dialog.SpikingNeuralNetworkDialog.destDir;
import static org.embedded.hnu.main.dialog.SpikingNeuralNetworkDialog.delim;

public class AddLayerConfigDialog extends javax.swing.JDialog {
    
    public static final int H_SNN = 1;
    
    public static final int HNUM_DL = 3; //nengoDL 소스코드생성시
    
    public static final int CONV = 0;
    public static final int POOL = 1;
    
    private static final int Linear = 1;
    private static final int Logistic = 2;
    private static final int MNIST = 3;   
    
    public static final int H_DATA_SET_TRAINING = 0;   
    public static final int H_DATA_SET_TEST = 1; 
    
    private int H_DL_Model = -1; //linear, logistic, mnist
    private int hDataSetOpt; //training or infer
    
    
    private String hDirectory;
    
    private int maxNumLayer = 0;
    private HSNNGenerator hSGen= new HSNNGenerator();
    private HSNNIOConfig hSIOConf = new HSNNIOConfig();
    private HSNNLayerConfig hSLCfg;
    private boolean saveLayer[];
    private boolean flag;
    
    private HManifestGenerator hMfGen;// = new HManifestGenerator();
    private HFunctionGenerator hFGen;
    private HUIGenerator hUGen;
    
    public AddLayerConfigDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        myInitComponents();    
    }
    
    public AddLayerConfigDialog(java.awt.Frame parent, boolean modal, int numLayer, HSNNGenerator hSGen, HSNNIOConfig hSIOConf, String dir, int model, int dataOpt) {
        super(parent, modal);
        maxNumLayer = numLayer;
        this.hSGen = hSGen;
        this.hSIOConf = hSIOConf;
        H_DL_Model = model;
        hDataSetOpt = dataOpt;
        hDirectory = dir;
        
        initComponents();
        myInitComponents();  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerOptionGroup = new javax.swing.ButtonGroup();
        //layerField[] = new javax.swing.JTextField[insertNumLayer];
        //layerLabel[] = new javax.swing.JLabel[insertNumLayer];
        //javax.swing.JLabel "layerLabel"+insertNumber = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        layerLabel = new javax.swing.JLabel();
        layerComboBox = new javax.swing.JComboBox();
        filterLabel = new javax.swing.JLabel();
        filterTextField = new javax.swing.JTextField();
        kernelSizeLabel = new javax.swing.JLabel();
        kernelSizeTextField = new javax.swing.JTextField();
        paddingLabel = new javax.swing.JLabel();
        paddingComboBox = new javax.swing.JComboBox();
        strideLabel = new javax.swing.JLabel();
        strideTextField = new javax.swing.JTextField();
        logLabel = new java.awt.Label();
        optionLabel = new javax.swing.JLabel();
        convRadioButton = new javax.swing.JRadioButton();
        poolingRadioButton = new javax.swing.JRadioButton();
        saveButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.title")); // NOI18N
        setIconImage(null);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(layerLabel, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.layerLabel.text")); // NOI18N

        String[] layerArr= new String[maxNumLayer];
        for(int i=0; i<maxNumLayer; i++){
            layerArr[i] = "Layer "+(i+1);
        }
        javax.swing.DefaultComboBoxModel layerData = new javax.swing.DefaultComboBoxModel(layerArr);
        layerComboBox.setModel(layerData);
        //for(int i=1; i<10; i++){
            //    if(!layerArr[i].equals(""))
            //        layerComboBox.add(layerArr[i]);
            //}
        layerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(filterLabel, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.filterLabel.text")); // NOI18N

        filterTextField.setText(org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.filterTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(kernelSizeLabel, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.kernelSizeLabel.text")); // NOI18N

        kernelSizeTextField.setText(org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.kernelSizeTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(paddingLabel, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.paddingLabel.text")); // NOI18N

        paddingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SAME", "VALID" }));

        org.openide.awt.Mnemonics.setLocalizedText(strideLabel, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.strideLabel.text")); // NOI18N

        strideTextField.setText(org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.strideTextField.text")); // NOI18N
        if(layerComboBox.getSelectedIndex() == 0){
            strideTextField.setText("0");
        }
        else{
            strideTextField.setText("2");
        }

        logLabel.setFont(new java.awt.Font("굴림", 0, 12)); // NOI18N
        logLabel.setForeground(new java.awt.Color(255, 0, 0));
        logLabel.setMinimumSize(new java.awt.Dimension(229, 19));
        logLabel.setText(org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.logLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(optionLabel, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.optionLabel.text")); // NOI18N

        layerOptionGroup.add(convRadioButton);
        convRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(convRadioButton, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.convRadioButton.text")); // NOI18N

        layerOptionGroup.add(poolingRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(poolingRadioButton, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.poolingRadioButton.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(layerLabel)
                            .addComponent(filterLabel)
                            .addComponent(kernelSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paddingLabel)
                            .addComponent(strideLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paddingComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(layerComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filterTextField)
                            .addComponent(kernelSizeTextField)
                            .addComponent(strideTextField)))
                    .addComponent(logLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(optionLabel)
                        .addGap(18, 18, 18)
                        .addComponent(convRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poolingRadioButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {filterLabel, kernelSizeLabel, layerLabel, paddingLabel, strideLabel});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(layerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(layerLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optionLabel)
                    .addComponent(convRadioButton)
                    .addComponent(poolingRadioButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(filterLabel)
                    .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(kernelSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kernelSizeLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(strideTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strideLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paddingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paddingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {filterLabel, filterTextField, kernelSizeLabel, kernelSizeTextField, layerComboBox, layerLabel, paddingComboBox, paddingLabel, strideLabel, strideTextField});

        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.saveButton.text")); // NOI18N
        saveButton.setMaximumSize(new java.awt.Dimension(100, 23));
        saveButton.setMinimumSize(new java.awt.Dimension(100, 23));
        saveButton.setPreferredSize(new java.awt.Dimension(100, 23));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(generateButton, org.openide.util.NbBundle.getMessage(AddLayerConfigDialog.class, "AddLayerConfigDialog.generateButton.text")); // NOI18N
        generateButton.setMaximumSize(new java.awt.Dimension(100, 23));
        generateButton.setMinimumSize(new java.awt.Dimension(100, 23));
        generateButton.setPreferredSize(new java.awt.Dimension(100, 23));
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {generateButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {generateButton, saveButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void myInitComponents(){
        logLabel.setText("");
        saveLayer = new boolean[maxNumLayer];
        layerComboBox.setSelectedIndex(0);
    }
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int layerN = layerComboBox.getSelectedIndex();
        setLayerConfig(layerN);
        saveLayer[layerN] = true;
        System.out.println("------------ saveButton ------------");
        for(int i=0; i<saveLayer.length;i++){
            System.out.println("Layer" + (i+1) + " = "  + saveLayer[i]);         
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        flag = true;
        int num = 1;
        for(int i=0 ; i<saveLayer.length ; i++){
            flag = flag && saveLayer[i];            
            if(flag && true){
                num = i+2;
            }
        }
        System.out.println("flag = " + flag);
        if(flag && true){
            if(LOADDATA == true){ copyFile(srcFile, destDir); }   
            logLabel.setText("false");
            configDataFromFile();
            generateSource();
            cancel();
        }else{
            System.out.println("** Layer"+num+" has not been saved.");
            logLabel.setText("Layer"+num+" has not been saved.");
        }
    }//GEN-LAST:event_generateButtonActionPerformed
  
    private void layerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerComboBoxActionPerformed
        javax.swing.JComboBox layerComboBox = (javax.swing.JComboBox)evt.getSource();
        Object selected = layerComboBox.getSelectedItem();
        if(selected.toString().equals("Layer 1")){
            filterTextField.setEnabled(true);
            kernelSizeLabel.setText("Kernel Size:");
            kernelSizeTextField.setText("3");
            filterTextField.setText("32");
            strideTextField.setText("0");
            convRadioButton.setSelected(true);
            paddingComboBox.setSelectedIndex(0);
            strideTextField.setText("0");
        }
        else if(selected.toString().equals("Layer 2")){
            filterTextField.setEnabled(true);
            kernelSizeLabel.setText("Kernel Size:");
            kernelSizeTextField.setText("3");
            filterTextField.setText("64");
            strideTextField.setText("0");
            convRadioButton.setSelected(true);
            paddingComboBox.setSelectedIndex(0);
            strideTextField.setText("0");
        }
        else if(selected.toString().equals("Layer 3")){ //pooling
            kernelSizeLabel.setText("Pool Size:");
            filterTextField.setEnabled(false);
            kernelSizeTextField.setText("2"); //pool size
            strideTextField.setText("2");
            poolingRadioButton.setSelected(true);
            paddingComboBox.setSelectedIndex(1);
            strideTextField.setText("2");
        }
        else if (selected.toString().equals("Layer 4")){
            filterTextField.setEnabled(true);
            kernelSizeLabel.setText("Kernel Size:");
            filterTextField.setText("128");
            kernelSizeTextField.setText("3");
            strideTextField.setText("2");
            convRadioButton.setSelected(true);
            strideTextField.setText("0");
            paddingComboBox.setSelectedIndex(0);
        }
        else if (selected.toString().equals("Layer 5")){ //pooling
            filterTextField.setEnabled(false);
            kernelSizeLabel.setText("Pool Size:");
            kernelSizeTextField.setText("2"); //pool size
            strideTextField.setText("2");
            poolingRadioButton.setSelected(true);
            paddingComboBox.setSelectedIndex(1);
            strideTextField.setText("2");
        }
    }//GEN-LAST:event_layerComboBoxActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddLayerConfigDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddLayerConfigDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddLayerConfigDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddLayerConfigDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddLayerConfigDialog dialog = new AddLayerConfigDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @Action
    public void cancel() {
        this.dispose();
    }
    
    public void setLayerConfig(int layerN){

        int filter = Integer.parseInt(filterTextField.getText());
        int kernel = Integer.parseInt(kernelSizeTextField.getText());
        int stride = 0;
        if(layerComboBox.getSelectedIndex() != 0)
            stride = Integer.parseInt(strideTextField.getText());
        String padding = (String)paddingComboBox.getSelectedItem();
        int option = -1; //conv? or pool?
        if(convRadioButton.isSelected())
            option = CONV;
        else
            option = POOL;
        
        hSLCfg = new HSNNLayerConfig(filter, kernel, stride, padding, option);
        
        hSGen.initLayerConfig(layerN, hSLCfg);
    }
    
    public void initGenerator(){
        hSGen.hSTATE = H_SNN;
        // hSGen.createFile(hSGen.modelDir); //-----nengoDL multifile수정 2021.09.19
        hSGen.createFile(hSGen.modelDir, true, H_DL_Model, hDataSetOpt);
        hMfGen = new HManifestGenerator(hSGen.hSTATE, HNUM_DL, hDirectory, H_DL_Model, hDataSetOpt);
            //2021.08.26 --SeoyeonKim NengoDL 이므로 html, js도 각각 3개씩 추가해야함. 그부분 추가 진행예정 //추가완료 09.12
            hFGen = new HFunctionGenerator(hSGen.hSTATE, HNUM_DL, hDirectory, H_DL_Model, hSIOConf, hDataSetOpt); // js 파일3개 생성
            hUGen = new HUIGenerator(hSGen.hSTATE, HNUM_DL, hDirectory, H_DL_Model, hSIOConf, hDataSetOpt); // html 파일3개 생성
    }
    
    public void generateSource(){
        initGenerator();
        //hSGen.writeSNNGen_Temp(); //-----nengoDL multifile수정 2021.09.19
        hSGen.writeSNNGenDL(H_DL_Model, hDirectory, hDataSetOpt);
    }
    
    public void configDataFromFile(){
        hSGen.setData(srcFile.getName(), delim);
    }
    
    public void copyFile(File srcFile, File destDir){
        System.out.println("--------------- LoadData ---------------");
        System.out.println("------------ copyFile() ------------");
        System.out.println("AddLayerConfigDialog srcFile.getAbsolutePath() : "+srcFile.getAbsolutePath());
        System.out.println("AddLayerConfigDialog destDir.getAbsolutePath() : "+destDir.getAbsolutePath());

        destDir.mkdirs();
        
        try{
            FileInputStream fin = new FileInputStream(srcFile.getAbsolutePath());
            FileOutputStream fout = new FileOutputStream(destDir.getAbsolutePath() + File.separatorChar + srcFile.getName());

            int tmp = 0;
            while ((tmp = fin.read()) != -1) {
                fout.write(tmp);
            }
            fin.close();
            fout.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("------------ copyFile() Finish------------");
    }
    
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton convRadioButton;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JTextField filterTextField;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel kernelSizeLabel;
    private javax.swing.JTextField kernelSizeTextField;
    private javax.swing.JComboBox layerComboBox;
    private javax.swing.JLabel layerLabel;
    private javax.swing.ButtonGroup layerOptionGroup;
    private java.awt.Label logLabel;
    private javax.swing.JLabel optionLabel;
    private javax.swing.JComboBox paddingComboBox;
    private javax.swing.JLabel paddingLabel;
    private javax.swing.JRadioButton poolingRadioButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel strideLabel;
    private javax.swing.JTextField strideTextField;
    // End of variables declaration//GEN-END:variables
}
