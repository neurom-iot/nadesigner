/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.embedded.hnu.generator.ai;

import org.embedded.hnu.generator.HAIGenerator;
import org.embedded.hnu.generator.HGenerator;
import org.embedded.hnu.generator.n3ml.HN3MLBpStdpGenerator;
import org.embedded.hnu.generator.n3ml.HN3MLResumeGenerator;
import org.embedded.hnu.generator.n3ml.HN3MLSoftLIFGenerator;
import org.embedded.hnu.generator.n3ml.HN3MLSpikePropGenerator;
import org.embedded.hnu.generator.n3ml.HN3MLStbpGenerator;
import org.embedded.hnu.generator.n3ml.HN3MLStdpGenerator;
import org.embedded.hnu.ioconfig.ai.HN3MLIOConfig;

/**
 *
 * @author SeoyeonKim
 */
public class HSNNN3MLGenerator extends HAIGenerator {
    
    public static final int H_N3ML_NM_LIF_MODEL = 0; //*------- SeoyeonKim 2021.06.01 -------*
    public static final int H_N3ML_NM_Soft_LIF_MODEL = 1;
    public static final int H_N3ML_NM_IF_MODEL = 2;
    public static final int H_N3ML_NM_SRM_MODEL = 3;
    public static final int H_N3ML_NM_Adaptive_LIF_MODEL = 4;
    
    public static final int H_N3ML_A_softLIF = 0;
    public static final int H_N3ML_A_SpikeProp = 1;
    public static final int H_N3ML_A_STDP = 2;
    public static final int H_N3ML_A_BP_STDP = 3;
    public static final int H_N3ML_A_STBP = 4;
    
    
    private HN3MLIOConfig hn3mlCfg=new HN3MLIOConfig();

    public String modelName = "";
    public String modelDir = "";
    
    private int hN3mlNeuronModel = 0;  //*------- SeoyeonKim 2021.06.01 -------*
    private int hN3mlAlgorithm = 0;
    //public String componentName = "";

//    public HSNNN3MLGenerator(HN3MLIOConfig hn3mlCfg, String mName, String mDir) {
//        super();
//        this.hn3mlCfg = hn3mlCfg;
//        modelName = mName;
//        modelDir = mDir;
//
//    }
    
    public HSNNN3MLGenerator(String mName, String mDir) {
        super();
        modelName = mName;
        modelDir = mDir;
    }
    
    public void setModelAlgorithm(int neuronmodel, int algorithm){
        hN3mlNeuronModel = neuronmodel;
        hN3mlAlgorithm = algorithm;
    }
    
    public void inputN3MLIO(HN3MLIOConfig hn3mlCfg){
        this.hn3mlCfg = hn3mlCfg;
    }

    public void writeN3MLGen() {
        switch (hN3mlAlgorithm) {
//            case H_N3ML_A_ReSuMe:
//                HN3MLResumeGenerator n3mlResumeGen = new HN3MLResumeGenerator(hn3mlCfg, hN3mlNeuronModel);
//                writeFile(n3mlResumeGen.createResume());
//                break;

            case H_N3ML_A_softLIF:
                HN3MLSoftLIFGenerator n3mlSoftLifGen = new HN3MLSoftLIFGenerator(hn3mlCfg, hN3mlNeuronModel);
                writeFile(n3mlSoftLifGen.createSoftLIF());
                break;
            case H_N3ML_A_SpikeProp:
                HN3MLSpikePropGenerator n3mlSpikePropGen = new HN3MLSpikePropGenerator(hn3mlCfg, hN3mlNeuronModel);
                writeFile(n3mlSpikePropGen.createSpikeProp());
                break;
            case H_N3ML_A_STDP:
                HN3MLStdpGenerator n3mlStdpGen = new HN3MLStdpGenerator(hn3mlCfg, hN3mlNeuronModel);
                writeFile(n3mlStdpGen.createSTDP());
                break;
            case H_N3ML_A_BP_STDP:
                HN3MLBpStdpGenerator n3mlBpStdpGen = new HN3MLBpStdpGenerator(hn3mlCfg, hN3mlNeuronModel);
                writeFile(n3mlBpStdpGen.createBpSTDP());
                break;
            case H_N3ML_A_STBP:
                HN3MLStbpGenerator n3mlStbpGen = new HN3MLStbpGenerator(hn3mlCfg, hN3mlNeuronModel);
                writeFile(n3mlStbpGen.createSTBP());
                break;
            default:
                writeFile(mainTest());
                break;
        }

    }

    

    public String mainTest() {
        String source = "if __name__ == '__main__':\r\n"
                + "    parser = argparse.ArgumentParser()\r\n"
                + "\r\n"
                + "    parser.add_argument('--data', default='../DataSets')\r\n"
                + "    parser.add_argument('--num_classes', default="+hn3mlCfg.getNumofClasses()+", type=int)\r\n"
                + "    parser.add_argument('--batch_size', default="+hn3mlCfg.getBatch_size()+", type=int)\r\n"
                + "    parser.add_argument('--simulation_times', default="+hn3mlCfg.getSimulationTime()+", type=float)  # 2s\r\n"
                + "    parser.add_argument('--time_steps', default="+hn3mlCfg.getTimeSteps()+", type=float)  # 1ms\r\n"
                + "    parser.add_argument('--time_frames', default="+hn3mlCfg.getTimeFrames()+", type=float)  # 50ms\r\n"
                + "\r\n"
                + "    app(parser.parse_args())\r\n";
        return source + "\r\n";
    }
    
}
