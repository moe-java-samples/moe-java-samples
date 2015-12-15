Intel Multi-OS Engine LibGDX Samples
========================

Setup environment for LibGDX
----------------------------

Please follow [Game Development using LibGDX](https://software.intel.com/en-us/articles/intel-inde-multi-os-engine-libgdx-example) Multi-OS Engine tutorial to prepare environment to build the samples. A brief instruction is below:
- Install Ant and Maven
- Clone libGDX from github repository:
``` sh
git clone https://github.com/libgdx/libgdx.git
```
- Go to libGDX folder and apply "add_moe_support.patch" patch located in this folder:
``` sh
  git apply path_to_patch/add_moe_support.patch
``` 
- Update libGDX version in <libgdx_root_path>/backends/gdx-backend-moe/pom.xml:7 file to your current libGDX version. You can find the version in the file <libgdx_root_path>/backends/gdx-backend-android/pom.xml:7.
- build the libGDX library using the following commands:
``` sh
  ant -f fetch.xml
  mvn install
```

Legal Information
-----------------

The Samples are distributed under BSD 3 Clause license. The Samples incorporate, or dynamically links against, some third-party software. See NOTICE.txt in the root folder for details.

*Other names and brands may be claimed as the property of others. 

Copyright 2015, Intel Corporation All Rights Reserved.
