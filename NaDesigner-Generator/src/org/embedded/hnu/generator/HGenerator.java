package org.embedded.hnu.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HGenerator{
    
    public static int hSTATE=0; 
    public static boolean hMultiFile= false;
    
    public static final int H_SNN = 1;   
    public static final int H_ANN = 2;   
    public static final int H_IoT = 3;
    
    public static final int H_DATA_SET_TRAINING = 0;   
    public static final int H_DATA_SET_TEST = 1; 
    private int hDataSetOpt = -1; //dataset 옵션 트레이닝 or 테스트
    
    private int H_NengoDL_EX = -1; //nengoDL에서 온게 아니면 -1임.
    
    private static final int Linear = 1;
    private static final int Logistic = 2;
    private static final int MNIST = 3;    
    
    public String hProjectDirectory;
    public String hProjectName;
    public FileWriter hFW;
    public BufferedWriter hBW;
    public PrintWriter hPW;
    

    public FileWriter[] hFWarr;
    public BufferedWriter[] hBWarr;
    public PrintWriter[] hPWarr;
    
    
    private String[] hFileName;// = new String[3];
    
    public HGenerator(){      
    }

    public void createFile(String hDir){
        
        String nnFile="UNTITLED";
        System.out.println("상태:"+hSTATE);
        if(hSTATE != H_SNN && hSTATE != H_ANN && hSTATE != H_IoT)
            return;
        if(hSTATE == H_SNN)
            nnFile = "snn-component";
        else if(hSTATE == H_ANN)
            nnFile = "ann-component";
        else if(hSTATE==H_IoT)
            nnFile = "iot-component";
        String dirTemp = hDir;
        hProjectDirectory = hDir+"\\src\\hnu\\"+nnFile+".py";
        
        System.out.println("파일:"+hProjectDirectory);
        initFile();
        deleteFiles(dirTemp);
    }
    public void createFile(String hDir, boolean multiFile, int type){ //NengoDL 런타임으로 들어온 경우
        hMultiFile = multiFile;
        H_NengoDL_EX = type;
        hProjectDirectory = hDir;
        hProjectName = hDir.substring(hDir.lastIndexOf("\\")+1);        
//        String nnFile="UNTITLED";
//        System.out.println("상태:"+hSTATE);
        createNewFiles();
//        hProjectDirectory = hDir+"\\src\\hnu\\"+nnFile+".py";
//        System.out.println("파일:"+hProjectDirectory);
//        initFile();
    }
    public void createFile(String hDir, boolean multiFile, int type, int dataSetOpt){ //NengoDL과 dataset(train or test)로 구분. 최종으로 이 메소드만 남을것임. //SeoyeonKim
        hMultiFile = multiFile;
        H_NengoDL_EX = type;
        hProjectDirectory = hDir;
        hProjectName = hDir.substring(hDir.lastIndexOf("\\")+1);   
        hDataSetOpt = dataSetOpt;
        
        if(H_NengoDL_EX != -1 && hDataSetOpt == H_DATA_SET_TEST) //nengoDL
            createNewFiles();
        else {
            String nnFile = "UNTITLED";            
            if (hSTATE != H_SNN && hSTATE != H_ANN && hSTATE != H_IoT) {
                return;
            }
            if (hSTATE == H_SNN) {
                nnFile = "snn-component";
            } else if (hSTATE == H_ANN) {
                nnFile = "ann-component";
            } else if (hSTATE == H_IoT) {
                nnFile = "iot-component";
            }
            String dirTemp = hDir;
            hProjectDirectory = hDir + "\\src\\hnu\\" + nnFile + ".py";
            initFile();
            deleteFiles(dirTemp);
        }

    }
    public void createFileArduino(String hDir){
        String nnFile="UNTITLED";
        System.out.println("상태:"+hSTATE);
        if(hSTATE != H_SNN && hSTATE != H_ANN && hSTATE != H_IoT)
            return;
        if(hSTATE == H_SNN)
            nnFile = "snn-component";
        else if(hSTATE == H_ANN)
            nnFile = "ann-component";
        else if(hSTATE==H_IoT)
            nnFile = "iot-component";
        hProjectDirectory = hDir+"\\src\\hnu\\"+nnFile+".ino";
         System.out.println("파일:"+hProjectDirectory);
        initFile();
    }
    public void createFileJS(String hDir){
        String nnFile="UNTITLED";
        System.out.println("상태:"+hSTATE);
        if(hSTATE != H_SNN && hSTATE != H_ANN && hSTATE != H_IoT)
            return;
        if(hSTATE == H_SNN)
            nnFile = "snn-component";
        else if(hSTATE == H_ANN)
            nnFile = "ann-component";
        else if(hSTATE==H_IoT)
            nnFile = "iot-component";
        hProjectDirectory = hDir+"\\src\\hnu\\"+nnFile+".js";
         System.out.println("파일:"+hProjectDirectory);
        initFile();
    }
    
    public void createNewFiles(){ //---------SeoyeonKim 2021.08.22 
        //File file = new File()
        String prjDir = hProjectDirectory+ File.separatorChar + "src" +File.separatorChar + "hnu"+File.separatorChar +hProjectName;
        if(H_NengoDL_EX == Logistic){
        }
        else{
            deleteFile();
            int fNum = 8;
            
            hFileName = new String[fNum];
            hFileName[0] = prjDir+"-data.py";
            hFileName[1] = prjDir+"-imp.py";
            hFileName[2] = prjDir+"-dataset.html";
            hFileName[3] = prjDir+"-dataset.js";
            hFileName[4] = prjDir+"-implement.html";
            hFileName[5] = prjDir+"-implement.js";
            hFileName[6] = prjDir+"-print.html";
            hFileName[7] = prjDir+"-print.js";
            
            for(int i=0; i<fNum; i++){
                File file = new File(hFileName[i]);
                try {
                    if(file.createNewFile()){
                        System.out.println(hFileName[i]+" success!!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(HGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            initFile(fNum, hFileName);
            //writeFile(hFileName[1], fNum, "#This is Test!!\r\n");
        }
    }
    
    public void writeFile(String fileName, int fileMax, String contents){
        for(int i=0;i<fileMax;i++){
            if(fileName.equals(hFileName[i])){
                if (hPWarr[i] != null) {
                    hPWarr[i].print(contents);
                    hPWarr[i].flush();
                }
            }
        }
   
        
    }

    public void initFile(){

        if(hProjectDirectory!=null&&hFW==null){    
            try{
                hFW = new FileWriter(hProjectDirectory, false);
                hBW = new BufferedWriter(hFW);
                hPW = new PrintWriter(hBW);
            } catch(IOException ex) {
            }
        }
    }
    
    public void initFiles(){

        if(hProjectDirectory!=null&&hFW==null){    
            try{
                hFW = new FileWriter(hProjectDirectory, false);
                hBW = new BufferedWriter(hFW);
                hPW = new PrintWriter(hBW);
            } catch(IOException ex) {
            }
        }
    }
    
    public void test(){
        //initFile(1, null);
    }
    
    public void initFile(int num, String[] filenames){
        if(filenames!=null && hFWarr == null){
            try{
                hFWarr = new FileWriter[num];
                hBWarr = new BufferedWriter[num];
                hPWarr = new PrintWriter[num];
                for(int i=0; i<num; i++){
                    hFWarr[i] = new FileWriter(filenames[i], false);
                    hBWarr[i] = new BufferedWriter(hFWarr[i]);
                    hPWarr[i] = new PrintWriter(hBWarr[i]);
                }
            } catch(IOException ex){
                
            }
        }
    }
    
    public void getParameter(){        
    }
    
    public void writeFile(String contents){
        if(hPW!=null){
            hPW.print(contents);
            hPW.flush();
        }
    }
    
    public void writeFile(String contents, int num){
        if(hPWarr[num]!=null){
            hPWarr[num].print(contents);
            hPWarr[num].flush();
        }
    }
    
    public void destroy(){
        if(hPW!=null && hBW!=null && hFW!=null){
            try { 
                hPW.close();
                hBW.close();
                hFW.close(); 
            } catch (IOException ex) {
            }
        }
    }
    
    public void destroy(int num){
        if(hPWarr!=null && hBWarr!=null && hFWarr!=null){
            try { 
                for(int i=0; i<num; i++){
                hPWarr[i].close();
                hBWarr[i].close();
                hFWarr[i].close(); 
                }
            } catch (IOException ex) {
            }
        }
    }
    
    public void deleteFile(){ //data-imp-print구조로 된 파일 생성할 때 단일 파일들 삭제
        String prjDir = hProjectDirectory + File.separatorChar + "src" + File.separatorChar + "hnu" + File.separatorChar  +"snn-component";
        String[] deleteFile = new String[3];

        deleteFile[0] = prjDir + ".py";
        deleteFile[1] = prjDir + ".js";
        deleteFile[2] = prjDir + ".html";

        for (int i = 0; i < 3; i++) {
            File file = new File(deleteFile[i]);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println(deleteFile[i] + " delete success!!");
                }else{
                    System.gc();
                    System.runFinalization();
                    file.delete();
                }
            }
        }

    }
    
    public void deleteFiles(String tempDir){ //snn-component 단일생성할 때 이미 있는 파일들 삭제
        int fNum = 8;
        //hProjectDirectory = (java.lang.String) "C:\Users\SeoyeonKim\Documents\NaDesigner\Projects\SNNComponent_naal1\src\hnu\snn-component.py"
        String tempName = tempDir.substring(tempDir.lastIndexOf("\\")+1);   
        String prjDir = tempDir + File.separatorChar + "src" + File.separatorChar + "hnu" + File.separatorChar  +tempName;
        String[] deleteFile = new String[fNum];

        
        deleteFile[0] = prjDir + "-data.py";
        deleteFile[1] = prjDir + "-imp.py";
        deleteFile[2] = prjDir + "-dataset.html";
        deleteFile[3] = prjDir + "-dataset.js";
        deleteFile[4] = prjDir + "-implement.html";
        deleteFile[5] = prjDir + "-implement.js";
        deleteFile[6] = prjDir + "-print.html";
        deleteFile[7] = prjDir + "-print.js";

        for (int i = 0; i < fNum; i++) {
            File file = new File(deleteFile[i]);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println(deleteFile[i] + " delete success!!");
                }
                else{
                    System.gc();
                    System.runFinalization();
                    file.delete();
                }
            }
        }

    }
    
}
