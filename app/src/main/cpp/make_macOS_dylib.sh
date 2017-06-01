#!/usr/bin/env bash

OUTPUT=../../../build/intermediates/dylibs
mkdir -p ${OUTPUT}

TESTLIB=../../test/libs

# .o file
cc -c -I/System/Library/Frameworks/JavaVM.framework/Headers *.cpp -o ${OUTPUT}/libnative-lib.o

# .dylib file
g++ -dynamiclib -undefined suppress -flat_namespace ${OUTPUT}/*.o -o ${OUTPUT}/libnative-lib.dylib

cp ${OUTPUT}/libnative-lib.o  ${TESTLIB}/linux_x86-64/libnative-lib.o

cp ${OUTPUT}/libnative-lib.dylib ${TESTLIB}/macOS_x86-64/libnative-lib.dylib