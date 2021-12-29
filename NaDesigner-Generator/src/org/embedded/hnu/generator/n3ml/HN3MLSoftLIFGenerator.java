/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.embedded.hnu.generator.n3ml;

import org.embedded.hnu.ioconfig.ai.HN3MLIOConfig;

/**
 *
 * @author SeoyeonKim
 */
public class HN3MLSoftLIFGenerator extends HN3MLGeneratorConfig{
        
    
    private int hN3mlNeuronModel = 0; 
    
    private HN3MLIOConfig hn3mlCfg=new HN3MLIOConfig();
    
    public HN3MLSoftLIFGenerator(HN3MLIOConfig n3mlCfg, int neuronmodel) {
        super();
        hN3mlNeuronModel = neuronmodel;
        hn3mlCfg = n3mlCfg;
    }

    public String createSoftLIF() {
        String source = importLibrary() + CreatePlot() + CreateValidate() + 
                CreateTrain() + CreateApp() + mainTest();
        return source;
    }

    private String importLibrary() {
        String source = "import time\r\n"
                + "import argparse\r\n"
                + "\r\n"
                + "import numpy as np\r\n"
                + "\r\n"
                + "import matplotlib.pyplot as plt\r\n"
                + "\r\n"
                + "import torch\r\n"
                + "import torchvision\r\n"
                + "import torch.nn as nn\r\n"
                + "import torch.optim as optim\r\n"
                + "\r\n"
                + "from n3ml.model import Hunsberger2015\r\n";
        return source + "\r\n";
    }

    private String CreatePlot() {
        String source = "class Plot:\r\n"
                + "    def __init__(self):\r\n"
                + "        plt.ion()\r\n"
                + "        self.fig, self.ax = plt.subplots(figsize=(10, 10))\r\n"
                + "        self.ax2 = self.ax.twinx()\r\n"
                + "        plt.title('Soft LIF')\r\n"
                + "\r\n"
                + "    def update(self, y1, y2):\r\n"
                + "        x = torch.arange(y1.shape[0]) * 64 * 100\r\n"
                + "\r\n"
                + "        ax1 = self.ax\r\n"
                + "        ax2 = self.ax2\r\n"
                + "\r\n"
                + "        ax1.plot(x, y1, 'g')\r\n"
                + "        ax2.plot(x, y2, 'b')\r\n"
                + "\r\n"
                + "        ax1.set_xlabel('number of images')\r\n"
                + "        ax1.set_ylabel('accuracy', color='g')\r\n"
                + "        ax2.set_ylabel('loss', color='b')\r\n"
                + "\r\n"
                + "        self.fig.canvas.draw()\r\n"
                + "        self.fig.canvas.flush_events()\r\n";
        return source + "\r\n";
    }

    private String CreateValidate() {
        String source = "def validate(val_loader, model, criterion):\r\n"
                + "    model.eval()\r\n"
                + "\r\n"
                + "    total_images = 0\r\n"
                + "    num_corrects = 0\r\n"
                + "    total_loss = 0\r\n"
                + "\r\n"
                + "    for step, (images, labels) in enumerate(val_loader):\r\n"
                + "        images = images.cuda()\r\n"
                + "        labels = labels.cuda()\r\n"
                + "\r\n"
                + "        preds = model(images)\r\n"
                + "\r\n"
                + "        loss = criterion(preds, labels)\r\n"
                + "\r\n"
                + "        num_corrects += torch.argmax(preds, dim=1).eq(labels).sum(dim=0)\r\n"
                + "        total_loss += loss.cpu().detach().numpy() * images.size(0)\r\n"
                + "        total_images += images.size(0)\r\n"
                + "\r\n"
                + "    val_acc = num_corrects.float() / total_images\r\n"
                + "    val_loss = total_loss / total_images\r\n"
                + "\r\n"
                + "    return val_acc, val_loss\r\n";
        return source + "\r\n";
    }

    private String CreateTrain() {
        String source = "def train(train_loader, model, criterion, optimizer, list_acc, list_loss, plotter):\r\n"
                + "    model.train()\r\n"
                + "\r\n"
                + "    total_images = 0\r\n"
                + "    num_corrects = 0\r\n"
                + "    total_loss = 0\r\n"
                + "\r\n"
                + "    for step, (images, labels) in enumerate(train_loader):\r\n"
                + "        images = images.cuda()\r\n"
                + "        labels = labels.cuda()\r\n"
                + "\r\n"
                + "        preds = model(images)\r\n"
                + "\r\n"
                + "        loss = criterion(preds, labels)\r\n"
                + "\r\n"
                + "        optimizer.zero_grad()\r\n"
                + "        loss.backward()\r\n"
                + "        optimizer.step()\r\n"
                + "\r\n"
                + "        num_corrects += torch.argmax(preds, dim=1).eq(labels).sum(dim=0)\r\n"
                + "        total_loss += loss.cpu().detach().numpy() * images.size(0)\r\n"
                + "        total_images += images.size(0)\r\n"
                + "\r\n"
                + "        if step > 0 and step % 100 == 0:\r\n"
                + "            list_loss.append(total_loss / total_images)\r\n"
                + "            list_acc.append(num_corrects.float() / total_images)\r\n"
                + "\r\n"
                + "            plotter.update(y1=np.array(list_acc), y2=np.array(list_loss))\r\n"
                + "\r\n"
                + "    train_acc = num_corrects.float() / total_images\r\n"
                + "    train_loss = total_loss / total_images\r\n"
                + "\r\n"
                + "    return train_acc, train_loss\r\n";
        return source + "\r\n";
    }

    private String CreateApp() {
        String source = "def app(opt):\r\n"
                + "    print(opt)\r\n"
                + "\r\n"
                + "    # torchvision.transforms.Normalize((0.5071, 0.4867, 0.4408), (0.2675, 0.2565, 0.2761))\r\n"
                + "    train_loader = torch.utils.data.DataLoader(\r\n"
                + "        torchvision.datasets.CIFAR10(\r\n"
                + "            opt.data,\r\n"
                + "            train=True,\r\n"
                + "            transform=torchvision.transforms.Compose([\r\n"
                + "                torchvision.transforms.RandomCrop(24),\r\n"
                + "                torchvision.transforms.ToTensor()])),\r\n"
                + "        batch_size=opt.batch_size,\r\n"
                + "        shuffle=True,\r\n"
                + "        num_workers=opt.num_workers)\r\n"
                + "\r\n"
                + "    val_loader = torch.utils.data.DataLoader(\r\n"
                + "        torchvision.datasets.CIFAR10(\r\n"
                + "            opt.data,\r\n"
                + "            train=False,\r\n"
                + "            transform=torchvision.transforms.Compose([\r\n"
                + "                torchvision.transforms.CenterCrop(24),\r\n"
                + "                torchvision.transforms.ToTensor()])),\r\n"
                + "        batch_size=opt.batch_size,\r\n"
                + "        num_workers=opt.num_workers)\r\n"
                + "\r\n"
                + "    model = Hunsberger2015(num_classes=opt.num_classes, amplitude=opt.amplitude, tau_ref=opt.tau_ref,\r\n"
                + "                           tau_rc=opt.tau_rc, gain=opt.gain, sigma=opt.sigma).cuda()\r\n"
                + "\r\n"
                + "    criterion = nn.CrossEntropyLoss()\r\n"
                + "\r\n"
                + "    optimizer = torch.optim.SGD(model.parameters(), lr=opt.lr, momentum=opt.momentum)\r\n"
                + "\r\n"
                + "    lr_scheduler = optim.lr_scheduler.MultiStepLR(optimizer, milestones=[30, 60, 90])\r\n"
                + "\r\n"
                + "    # for plot\r\n"
                + "    plotter = Plot()\r\n"
                + "\r\n"
                + "    list_loss = []\r\n"
                + "    list_acc = []\r\n"
                + "\r\n"
                + "    best_acc = 0\r\n"
                + "\r\n"
                + "    for epoch in range(opt.num_epochs):\r\n"
                + "        start = time.time()\r\n"
                + "        train_acc, train_loss = train(train_loader, model, criterion, optimizer, list_acc, list_loss, plotter)\r\n"
                + "        end = time.time()\r\n"
                + "        print('total time: {:.2f}s - epoch: {} - accuracy: {} - loss: {}'.format(end-start, epoch, train_acc, train_loss))\r\n"
                + "\r\n"
                + "        val_acc, val_loss = validate(val_loader, model, criterion)\r\n"
                + "\r\n"
                + "        if val_acc > best_acc:\r\n"
                + "            best_acc = val_acc\r\n"
                + "            state = {\r\n"
                + "                'epoch': epoch,\r\n"
                + "                'model': model.state_dict(),\r\n"
                + "                'best_acc': best_acc,\r\n"
                + "                'optimizer': optimizer.state_dict()}\r\n"
                + "            torch.save(state, opt.pretrained)\r\n"
                + "            print('in test, epoch: {} - best accuracy: {} - loss: {}'.format(epoch, best_acc, val_loss))\r\n"
                + "\r\n"
                + "        lr_scheduler.step()\r\n"
                + "\r\n";
        return source + "\r\n";
    }

    private String mainTest() {
        String source = "if __name__ == '__main__':\r\n"
                + "    parser = argparse.ArgumentParser()\r\n"
                + "\r\n"
                + "    parser.add_argument('--data', default='../DataSets')\r\n"
                + "    parser.add_argument('--num_classes', default="+hn3mlCfg.getNumofClasses()+", type=int)\r\n"
                + "    parser.add_argument('--num_epochs', default="+hn3mlCfg.getEpoch()+", type=int)\r\n"
                + "    parser.add_argument('--batch_size', default="+hn3mlCfg.getBatch_size()+", type=int)\r\n"
                + "    parser.add_argument('--num_workers', default=8, type=int)\r\n"
                + "    parser.add_argument('--lr', default="+hn3mlCfg.getLearning_rate()+", type=float)\r\n"
                + "    parser.add_argument('--momentum', default="+hn3mlCfg.getMomentum()+", type=float)\r\n"
                + "    parser.add_argument('--pretrained', default='pretrained/softlif.pt')\r\n"
                + "\r\n"
                + "    parser.add_argument('--amplitude', default="+hn3mlCfg.getAmplitude()+", type=float)\r\n"
                + "    parser.add_argument('--tau_ref', default="+hn3mlCfg.getTauRef()+", type=float)\r\n"
                + "    parser.add_argument('--tau_rc', default="+hn3mlCfg.getTau_rc()+", type=float)\r\n"
                + "    parser.add_argument('--gain', default="+hn3mlCfg.getGain()+", type=float)\r\n"
                + "    parser.add_argument('--sigma', default="+hn3mlCfg.getSigma()+", type=float)\r\n"
                + "\r\n"
                + "    app(parser.parse_args())\r\n";
        return source + "\r\n";
    }
        
}
