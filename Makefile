JC	=	javac
JVM	=	java
JAR	=	jar

BUILD_DIR	=	classes
SRC_DIR		=	src

JARNAME		=	javasweeper.jar
JARDIR		=	lib

JARFILE		=	$(JARDIR)/$(JARNAME)

JARFLAGS	=	--create --file

JAVA_FILES	:=	$(shell find $(SRC_DIR) -name "*.java")
CLASS_FILES	:=	$(patsubst $(SRC_DIR)/%.java,$(BUILD_DIR)/%.class,$(JAVA_FILES))

MAIN_CLASS	=	com.kittenamogus.Main

.PHONY: all run

all: $(CLASS_FILES)

jar: all $(JARDIR)
	@echo "Creating $(JARNAME)..."
	$(JAR) $(JARFLAGS) $(JARFILE) -C $(BUILD_DIR) .

$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java | $(BUILD_DIR)
	@echo "Compiling $<..."
	$(JC) -d $(BUILD_DIR) -cp $(BUILD_DIR) $<

$(BUILD_DIR):
	@mkdir -p $(BUILD_DIR)

$(JARDIR):
	@mkdir -p $(JARDIR)

run: all
	@echo "Running $(MAIN_CLASS)..."
	$(JVM) -cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	@echo "Cleaning up..."
	@rm -rf $(BUILD_DIR) $(JARDIR)

