# NA-Designer
Neuromorphic Architecture based Component Designer


## JDK

Java 1.8

## Installaion

### Download 
 
 Download the following files [here](https://github.com/neurom-iot/nadesigner/blob/main/doc/Installation.md).
 
 1. "NaDesigner IoT" installer  
 2. "NaDesigner AI" installer  
 3. python plugin



### Run the Installer
 Run the downloaded installer.

![install1](https://user-images.githubusercontent.com/45158796/170095361-ea3df8e7-300b-4017-8bbe-62e7900b871a.png)

![install2](https://user-images.githubusercontent.com/45158796/170095360-2922e3dd-ad21-4948-b379-84e78578be49.png)

![install3](https://user-images.githubusercontent.com/45158796/170095358-e864c7d7-2e68-4983-a652-bef67b0311d0.png)

![install4](https://user-images.githubusercontent.com/45158796/170095368-f4155685-d29a-48c6-9667-f63f50ca09cd.png)

![install5](https://user-images.githubusercontent.com/45158796/170095364-6a4b9d08-bad6-4159-83d9-83d734fda725.png)

![install6](https://user-images.githubusercontent.com/45158796/170095363-af2e5186-1761-4af9-b1e3-03541980711b.png)



## Configuration

### Edit the nadesigner.conf
 After installation is complete, edit the `C:\Program Files\nadesigner\etc\nadesigner.conf` file as follows.
 
 ```sh
 # options used by the launcher by default, can be overridden by explicit
 # command line switches
 default_options="--branding nadesigner -J-Xms1024m -J-Xmx2048m"
 # default location of JDK/JRE, can be overridden by using --jdkhome <dir> switch
 jdkhome="your/path/to/jdk"
 ```

### Install the plugin
  Download "Python plugin for nadesigner" installer. 
  Run nadesigner and install the plugin as follows. 

![plugin1](https://user-images.githubusercontent.com/45158796/170095413-5bbeee99-ce09-442f-abec-4685b929263f.png)

![plugin2](https://user-images.githubusercontent.com/45158796/170095410-1375959f-9987-49ef-9b12-db325d8a65fb.png)

![plugin3](https://user-images.githubusercontent.com/45158796/170095409-cbd736d5-8379-4a84-a965-288df2a12a9d.png)

![plugin4](https://user-images.githubusercontent.com/45158796/170095406-a939d98a-f529-402c-b04d-bc22d4acc711.png)

![plugin5](https://user-images.githubusercontent.com/45158796/170095403-478f9f3f-fc38-4855-bb97-f89cd276c0aa.png)

![plugin6](https://user-images.githubusercontent.com/45158796/170095397-0a373668-d0c8-4ea2-99e3-36670a1dc87d.png)

![plugin7](https://user-images.githubusercontent.com/45158796/170095391-7f1ccf32-9be6-4c2f-93a5-a4e6e05eab4d.png)


### Configure the python platform
  Configure the python environment in NaDesigner.

![platform1](https://user-images.githubusercontent.com/45158796/170095445-1db8170a-c307-4342-8396-b7ae33ce7a0f.png)

![platform2](https://user-images.githubusercontent.com/45158796/170095442-d8da6269-5c78-417a-a5c7-904f7c552d9e.png)

![platform3](https://user-images.githubusercontent.com/45158796/170095441-7cdecec7-6ad8-4f41-b72d-5f43ce8898c7.png)

![platform4](https://user-images.githubusercontent.com/45158796/170095448-81c5727f-3937-47cb-960f-4a9be5022375.png)


### Requirement
  Download requirement.txt and Install the python dependencies 
  ```
  pip install -r requirement.txt
  ```


## Example

### IoT Component 
  The following execercize explains how to use NaDesigner to create the IoT Component.
  
![nadesigner-iot1](https://user-images.githubusercontent.com/45158796/170095474-88f942dd-4f25-4ee4-8bff-d098c590ddac.png)

![nadesigner-iot2](https://user-images.githubusercontent.com/45158796/170095473-374623ff-d220-444b-bcb5-69c9cd781fa7.png)

![nadesigner-iot3](https://user-images.githubusercontent.com/45158796/170095472-8eeebf0b-bd52-4f7b-8e6f-58eb3c121c53.png)

![nadesigner-iot4](https://user-images.githubusercontent.com/45158796/170095468-3f78473c-c41c-4a14-be70-6f5dc3c083a7.png)

![nadesigner-iot5](https://user-images.githubusercontent.com/45158796/170095467-7bdad6fe-4ad6-48a2-919a-3d151ce7e466.png)

![nadesigner-iot6](https://user-images.githubusercontent.com/45158796/170095466-ed4a5203-3270-43d3-8143-6fa2ca6361ed.png)

![nadesigner-iot7](https://user-images.githubusercontent.com/45158796/170095463-58b4055d-48a8-4d8f-b7fb-ee58d100f7a6.png)


### AI Component
  The following execercize explains how to use NaDesigner to create the IoT Component.

#### Multi-Class Classification (MNIST)

![nadesigner-ai1](https://user-images.githubusercontent.com/45158796/170121560-1e660212-347d-44e9-9952-4a9675021250.png)

![nadesigner-ai2](https://user-images.githubusercontent.com/45158796/170121524-5152594f-9bbd-4876-a8c0-34ee94d53507.png)

![nadesigner-ai3](https://user-images.githubusercontent.com/45158796/170121531-76ddf581-b03c-45eb-b0f7-5c211441ad2f.png)

![nadesigner-ai4](https://user-images.githubusercontent.com/45158796/170121535-4c6a97df-b4a3-4dd1-af3f-2da1e544f23a.png)

![nadesigner-ai5](https://user-images.githubusercontent.com/45158796/170121540-ac67bee3-c021-44ad-beae-375e2d0828de.png)

![nadesigner-ai6](https://user-images.githubusercontent.com/45158796/170121544-2699da23-6aab-4d76-81a2-d30bbaa561fd.png)

![nadesigner-ai7](https://user-images.githubusercontent.com/45158796/170121546-908a7fef-c4c1-445c-b62b-b89cb0ce9a71.png)


### N3ML-BP-STDP (MNIST)

![nadesigner-ai1](https://user-images.githubusercontent.com/45158796/170121560-1e660212-347d-44e9-9952-4a9675021250.png)

![nadesigner-ai2](https://user-images.githubusercontent.com/45158796/170121524-5152594f-9bbd-4876-a8c0-34ee94d53507.png)

![nadesigner-ai3](https://user-images.githubusercontent.com/45158796/170121531-76ddf581-b03c-45eb-b0f7-5c211441ad2f.png)

![nadesigner-ai4](https://user-images.githubusercontent.com/45158796/170121535-4c6a97df-b4a3-4dd1-af3f-2da1e544f23a.png)


![nadesigner-ai8-1](https://user-images.githubusercontent.com/45158796/170122520-f320a8c4-65d3-4084-9859-e3ee4a055e16.png)


![nadesigner-ai9](https://user-images.githubusercontent.com/45158796/170121552-93419458-e565-43e1-ae92-a585d1a10d6d.png)

![nadesigner-ai10](https://user-images.githubusercontent.com/45158796/170121553-241de57a-c358-47bc-bc44-62f957a54048.png)

![nadesigner-ai11](https://user-images.githubusercontent.com/45158796/170121555-7e52cd6a-0405-48cd-9592-fc332456862e.png)


## Authors

  Contributors names and contact info
  
  - Seoyeon Kim
  - Jaehyeok Jeong
  - Jaehee Kim
  - Jinman Jung
