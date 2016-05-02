TITLE PGM4_1.5: SAMPLE INPUT
.MODEL SMALL
.STACK 100H
.DATA

STRING DB  100h dup(?)
LEN DW ?
NUM DW ?  
TOTAL DW ? 
VAR1 DW ?
STRING1 DW 'Number of strings: $'
STRING2 DW 'Length of each string: $'
.CODE
MAIN PROC
    
    MOV AX,@DATA
    MOV ES,AX    
    MOV DS,AX 
    
    
    
    
    
    
    
    LEA DX,STRING1
    MOV AH,9
    INT 21H  
    
    CALL INPUT
    MOV NUM,AX
    
    LEA DX,STRING2 
    MOV AH,9
    INT 21H
    CALL INPUT
    MOV LEN,AX 
    
    LEA DI,STRING
    MOV CX,NUM
    TAKING_STRING_INPUT:
                CALL INPUT_STR 
                  
                LOOP TAKING_STRING_INPUT
                
                  
   
   
    CALL SORT 
    
    
    PRINTING_OUTPUT:
                    
                    CMP BX,NUM
                    JG OUTPUT_DONE:
                    CALL GETSI  
                    MOV SI,AX
                    CALL OUTPUT_STR
                    INC BX
                    JMP PRINTING_OUTPUT
    
    OUTPUT_DONE:
    
    
   
    
    MOV AH,4CH
    INT 21H
      
    
    
    
    
MAIN ENDP  




; THIS PROC TAKES INPUT FROM SCREEN UNTILL A ENTER IS READ
; NO INPUT IS REQUIRED
; OUTPUT WILL BE IN AX
; USED INSIDE MAIN PROC
INPUT PROC  
    
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH TOTAL
    
    MOV TOTAL,0
     
   INPUT1_LOOP_CONDITION: 
 
   MOV AH,1
   INT 21H 
   CMP AL, 0DH  ;IF ENTER THEN TERMINATE
   JE INPUT1_END  
   
   ;JMP INPUT1_LOOP
   INPUT1_LOOP:
   ;CMP AL,1AH   ; CHECKING EOF
   ;JE OUT_OF_THE_LOOP 
   MOV AH,0     ;CLEARING TO STORE IN VAR1
   MOV VAR1,AX  ; MOVING TO VAR1 AS AX WILL BE USED LATER
   SUB VAR1,'0' ; EXTRACTING NUMBER
   MOV AL,10    ; MULTIPLICATOR
   MUL TOTAL    ; MULTIPLICATION 
   ADD AX,VAR1  ; ADDING EXTRACTED NUMBER
   MOV TOTAL,AX ; READY FOR NEXT
   JMP INPUT1_LOOP_CONDITION  
   INPUT1_END: 
   MOV AX,TOTAL ;AS PROHIBITED  
   PUSH AX
   
   ;INPUT1_END   
   MOV DL,0DH
   MOV AH,2
   INT 21H
   MOV DL,0AH
   INT 21H
   POP AX
   POP TOTAL
   POP DX
   POP CX
   POP BX
   RET
INPUT ENDP  


;THIS PROC READS A STRING
;PARAMETER WILL BE IN LEN WHICH IS LENGTH OF THE STRING
;OUTPUT WILL BE IN DI
;USED INSIDE MAIN
    
INPUT_STR proc 
    
    PUSH AX
    PUSH CX
    
    PUSH LEN
    
    CLD
    MOV AH,1
    INT 21H 
    MOV CX,LEN
    DEC CX
    TAKE_INPUT:
               STOSB 
               INT 21H
               LOOP TAKE_INPUT  
               
     
    STOSB           
    MOV DL,0DH
    MOV AH,2
    INT 21H
    MOV DL,0AH
    INT 21H  
    
    POP LEN
   
    POP CX
    POP AX
    
    RET
INPUT_STR ENDP 


; THIS PROC WILL COMPARE TWO STRING
; INPUT WILL BE IN SI FIRST STRING DI SECOND STRING  SEGMENT
; WILL BE DS   LENGTH MUST BE SPECIFIED IN LEN
; OUTPUT WILL BE IN AX IF EQUAL ZERO STR1>STR2 ONE OR ELSE TWO
; USED INSIDE MAIN


STRCMP PROC 
    
    
    PUSH CX
    PUSH BX
    PUSH DX 
    PUSH SI
    PUSH DI
    PUSH LEN  
    
    ;MOV SI,AX
    ;MOV DI,CX
    MOV BX,DS
    MOV ES,BX
    CLD  
    MOV CX,LEN
    REPE CMPSB
    JL STR1
    JG STR2  
    MOV AX,0   
         
    POP LEN     
    POP DI
    POP SI
    POP DX
    POP BX
    POP CX
    RET 
    STR1:
        MOV AX,1 
        POP LEN
        POP DI
        POP SI
        POP DX
        POP BX
        POP CX
        RET
    STR2:
        MOV AX,2  
        POP LEN
        POP DI
        POP SI
        POP DX
        POP BX
        POP CX
        RET
    
    
STRCMP ENDP 
; THIS PROC WILL EXCHANGE TWO STRINGS
; INPUT IS TWO STRINGS POINTED BY SI AND DI LENGTH WILL BE I  LEN
;   AND SEGMENT IS DS
; NO OUTPUT ONLY TWO STRINGS WILL EXCHANGE THEIR POSITION IN 
;   MEMORY
; USED INSIDE MAIN

STREX PROC
    
    PUSH CX
    PUSH BX
    PUSH DX 
    PUSH SI
    PUSH DI
    PUSH LEN  
    ;MOV SI,AX
    ;MOV DI,CX
   
    MOV CX,LEN
    
    COPY:
        
        MOV BL,STRING[SI]  
        MOV DL,STRING[DI]
        MOV STRING[SI],DL
        MOV STRING[DI],BL
        INC SI
        INC DI
        LOOP COPY
        
        
    POP LEN
    POP DI
    POP SI
    POP DX
    POP BX
    POP CX
    RET       
    
    
    
    
    
STREX ENDP
; THIS PROC WILL MULTIPLY BX WITH LEN
; IN PUT WILL BE BX AND LEN
; OUTPUT WILL BE IN AX
; CALLED INSIDE MAIN

GETSI PROC   
    
    
    PUSH BX
    PUSH CX
    PUSH DX
    MOV AX,BX
    MUL LEN
    POP DX
    POP CX
    POP BX
    RET
   
    
    
    
    
GETSI ENDP 
SORT PROC 
    
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX 
    MOV CX,NUM
    MAIN_LOOP: 
             
             MOV AX,0
             PUSH CX
             DEC CX
             
             INNER_LOOP:
                        
                        
                        CMP AX,CX
                        JE END_INNER_LOOP
                        PUSH AX
                       
                        MOV BX,AX 
                        CALL GETSI
                        MOV SI,AX
                        INC BX
                        CALL GETSI 
                        MOV DI,AX
                        CALL STRCMP
                        
                        
                        CMP AX,2
                        JE SWAP
                        JMP DO_NOTHING
                        SWAP:
                            CALL STREX  
                            
                        DO_NOTHING:
                                   POP AX
                                   INC AX
                                   JMP INNER_LOOP
                                   
              END_INNER_LOOP:
                             POP CX
                             LOOP MAIN_LOOP
                         
                        
                        
                        
      POP DX
      POP CX
      POP BX
      POP AX
      RET
    
SORT ENDP 
OUTPUT_STR PROC     
    
    
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    MOV CX,LEN
    JCXZ EXIT
    CLD
    PRINT_LOOP:
    LODSB
    MOV DL,AL
    MOV AH,2
    INT 21H
    LOOP PRINT_LOOP
    EXIT:
    
    MOV DL,0DH
    MOV AH,2
    INT 21H
    MOV DL,0AH
    INT 21H
    
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
    RET
    
    
    
    
OUTPUT_STR ENDP

 END MAIN
    