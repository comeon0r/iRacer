LOADMODULE=~/existCFG/llvm-3.5.0.src/Release+Asserts/lib


CFGPASS=~/existCFG/llvm-3.5.0.src/lib/Transforms/buildCFG

LLVM_BIN =~/existCFG/llvm-3.5.0.src/Release+Asserts/bin
CC =$(LLVM_BIN)/clang
C++ =$(LLVM_BIN)/clang++
OPT = $(LLVM_BIN)/opt
LD = $(LLVM_BIN)/llvm-link
DIS=$(LLVM_BIN)/llvm-dis
CFLAGS=  -emit-llvm  -g -O0
NAME =  
SUBPATHLENGTH = 8

all:    buildCFG  



$(NAME).ll:$(NAME).bc
	$(DIS) $(NAME).bc
 
 

$(NAME)_final.ll:$(NAME)_final
	$(DIS) $(NAME)_final.bc

 

buildCFG:$(NAME).bc $(LOADMODULE)/buildCFG.so
	$(OPT)   -load $(LOADMODULE)/buildCFG.so  -buildCFG <$(NAME).bc >/dev/null
#	$(OPT) -dot-cfg  -load $(LOADMODULE)/buildCFG.so  -buildCFG <$(NAME).bc >/dev/null

 

 
 
 

 

$(LOADMODULE)/buildCFG.so:
	make -C $(CFGPASS)/ 

$(NAME).bc:$(NAME).c
	$(CC) -c $(CFLAGS) $(NAME).c -o $(NAME).bc

clean:
	rm -f *.bc
	rm -f *.ll
	rm -f *_ld
	rm -f $(LOADMODULE)/buildCFG.so
