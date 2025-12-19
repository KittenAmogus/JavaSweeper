JAVAC	=	javac
JAVA	=	java
JAR		=	jar

SRC_DIR		=	src
BUILD_DIR	=	classes
JAR_NAME	=	JavaSweeper.jar

MAIN_CLASS	=	com.kittenamogus.Main

MANIFEST_FILE	=	Manifest.mf
MANIFEST_CONT	=	Main-Class: $(MAIN_CLASS)

.PHONY: all build jar run clean


all: run

build: $(BUILD_DIR) $(MANIFEST_FILE)
	@echo "--- Building... ---"
	$(JAVAC) -d $(BUILD_DIR) $(shell find $(SRC_DIR) -name "*.java")

jar: build
	@echo "--- Creating $(JAR_NAME) ---"
	$(JAR) -cfm $(JAR_NAME) $(MANIFEST_FILE) -C $(BUILD_DIR) .

run: jar
	$(JAVA) -jar $(JAR_NAME)

norebuildrun: $(JAR_NAME)
	@echo "--- Running jar without rebuild! --- "
	$(JAVA) -jar $(JAR_NAME)

clean:
	@echo "--- Cleaning up... --- "
	@rm -rf $(BUILD_DIR) $(JAR_NAME) $(MANIFEST_FILE)

# Files and dirs

$(BUILD_DIR):
	@mkdir -p $(BUILD_DIR)

$(MANIFEST_FILE):
	@echo "$(MANIFEST_CONT)" > $(MANIFEST_FILE)

