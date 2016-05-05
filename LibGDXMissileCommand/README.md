Intel Multi-OS Engine LibGDX Sample
===================================

Environment setup for LibGDX
----------------------------

- Install Ant and Maven
- Install Multi-OS Engne version 1.0.648 or later
- Clone libGDX "moe" branch from github repository:
``` sh
git clone -b moe https://github.com/libgdx/libgdx.git
```
- Copy intel-moe-core.jar and intel-moe-ios.jar files from /Applications/Intel/multi_os_engine/sdk to <libgdx_repo_folder>/backends/gdx-backend-moe/libs folder (overwriting existing files)
- Replace "com.intel.inde.moe.natj" with "com.intel.moe.natj" in all *.java files in <libgdx_repo_folder>/backends/gdx-backend-moe folder
- Build libGDX libraries using the following commands:
``` sh
  ant -f fetch.xml
  mvn install
```
- Clone moe-java-samples repo and create "moe-java-samples/LibGDXMissileCommand/ios/build/xcode/LibGDXMissileCommand.xcodeproj" folder:
``` sh
mkdir -p LibGDXMissileCommand/ios/build/xcode/LibGDXMissileCommand.xcodeproj
```
- Import LibGDXMissileCommand sample in Android Studio (as a gradle project), create "Multi-OS Engine iOS Application" build configuration and build the project.