JARNAME = CredMgr.jar
TARGETDIR = ~/.cm/
BUILDDIR = ./build/libs/

FISH_FUNC_DIR = ~/.config/fish/functions/cm.fish
BASHRC = ~/.bashrc

JAVA_ARGS =

ALL: CLEAN BUILD

CLEAN:
	./gradlew clean

BUILD:
	./gradlew build

INSTALL:
	mkdir -p $(TARGETDIR) && cp $(BUILDDIR)$(JARNAME) $(TARGETDIR)$(JARNAME)

UNINSTALL:
	rm $(FISH_FUNC_DIR)
	rm $(TARGETDIR)$(JARNAME)

BASH:
	echo "alias cm='java -jar ~/.cm/CredMgr.jar $(JAVA_ARGS)'" >> $(BASHRC)

FISH:
	mv $(FISH_FUNC_DIR) $(FISH_FUNC_DIR).$(shell date +%s)
	echo "function cm -d 'Connect to a SSH remotehost using the CM'" >> $(FISH_FUNC_DIR)
	echo "	java -jar ~/.cm/CredMgr.jar $(JAVA_ARGS)" >> $(FISH_FUNC_DIR)
	echo "end" >> $(FISH_FUNC_DIR)

DOCS:
	cat ./Docs/makecommands.txt