# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.2

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Produce verbose output by default.
VERBOSE = 1

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp

# Include any dependencies generated for this target.
include CMakeFiles/cmTryCompileExec1020322828.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/cmTryCompileExec1020322828.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/cmTryCompileExec1020322828.dir/flags.make

CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o: CMakeFiles/cmTryCompileExec1020322828.dir/flags.make
CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o: /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/feature_tests.c
	$(CMAKE_COMMAND) -E cmake_progress_report /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp/CMakeFiles $(CMAKE_PROGRESS_1)
	@echo "Building C object CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o"
	/usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o   -c /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/feature_tests.c

CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.i: cmake_force
	@echo "Preprocessing C source to CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.i"
	/usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -E /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/feature_tests.c > CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.i

CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.s: cmake_force
	@echo "Compiling C source to assembly CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.s"
	/usr/bin/cc  $(C_DEFINES) $(C_FLAGS) -S /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/feature_tests.c -o CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.s

CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.requires:
.PHONY : CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.requires

CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.provides: CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.requires
	$(MAKE) -f CMakeFiles/cmTryCompileExec1020322828.dir/build.make CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.provides.build
.PHONY : CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.provides

CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.provides.build: CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o

# Object files for target cmTryCompileExec1020322828
cmTryCompileExec1020322828_OBJECTS = \
"CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o"

# External object files for target cmTryCompileExec1020322828
cmTryCompileExec1020322828_EXTERNAL_OBJECTS =

cmTryCompileExec1020322828: CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o
cmTryCompileExec1020322828: CMakeFiles/cmTryCompileExec1020322828.dir/build.make
cmTryCompileExec1020322828: CMakeFiles/cmTryCompileExec1020322828.dir/link.txt
	@echo "Linking C executable cmTryCompileExec1020322828"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/cmTryCompileExec1020322828.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/cmTryCompileExec1020322828.dir/build: cmTryCompileExec1020322828
.PHONY : CMakeFiles/cmTryCompileExec1020322828.dir/build

CMakeFiles/cmTryCompileExec1020322828.dir/requires: CMakeFiles/cmTryCompileExec1020322828.dir/feature_tests.c.o.requires
.PHONY : CMakeFiles/cmTryCompileExec1020322828.dir/requires

CMakeFiles/cmTryCompileExec1020322828.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/cmTryCompileExec1020322828.dir/cmake_clean.cmake
.PHONY : CMakeFiles/cmTryCompileExec1020322828.dir/clean

CMakeFiles/cmTryCompileExec1020322828.dir/depend:
	cd /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp /home/tricycle/Programs/OpenCV/Exer8/CMakeFiles/CMakeTmp/CMakeFiles/cmTryCompileExec1020322828.dir/DependInfo.cmake
.PHONY : CMakeFiles/cmTryCompileExec1020322828.dir/depend

