.MODEL SMALL
DRAW_ROW MACRO X
    LOCAL L1
; DRAWS A LINE IN ROW X FROM COL 10 TO COL 300
    MOV AH, 0CH
    MOV AL, 4
    MOV CX, 0
    MOV DX, X
L1: INT 10H
    INC CX
    CMP CX, 320
    JL L1
    ENDM
.STACK 100H
.DATA
HEIGHT1 DW 51 
WIDTH1 DW 65
 V1 DW ?
 V2 DW ?
CUR_POSX_USER  DW 61
CUR_POSY_USER  DW 75
VAR DB ?
VAR1 DW ?
TOTAL DW ?
DIVISOR DW ?
POSX1 DW 250
POSY1 DW 141
POSX2 DW 250
POSY2 DW 10
POSX3 DW 250
POSY3 DW 75
CF DW 0
D1F DW 1
D2F DW 0
D3F DW 0
STRING_COLOR DB 5H
STRINGL  DW  ?
STRINGR  DB  5 
STRINGC  DB 5
TDELAY DW ?
STRING DW 'S','C','O','R','E','-',' ',?,?,?,?
SCORE DW 0

.CODE

DRAWCAR PROC

    PUSH AX
    MOV V1, CX
    MOV AX,WIDTH1
    ADD V1,AX
    MOV V2, DX
    MOV AX,HEIGHT1
    ADD V2,AX
    POP AX
    MOV AH, 0CH
    
    MOV BX,V1
L1: 
    INC CX
    
    PUSH DX 
       L2:INT 10H
          INC DX
          CMP DX,V2
          JL L2
    POP DX
    CMP CX, V1        
    JL L1
    ;MOV AH,0
    ;INT 16H
    
    RET
   
       
    
    RET 
DRAWCAR ENDP
MAIN PROC
MOV AX,@DATA
MOV DS,AX
CALL DRAWSCREEN


INFINITE:
         
         MOV AL,1
         MOV CX,CUR_POSX_USER
         MOV DX,CUR_POSY_USER
         CALL DRAWCAR
         CALL COL
         CMP CF,1
         JE OP 
         CALL DRAW1
         CALL COL
         CMP CF,1
         JE OP 
         CALL LISTEN_KEYBOARD
         CALL COL
         CMP CF,1
         JE OP 
         CALL DRAW2
         CALL LISTEN_KEYBOARD
         CALL DRAW3
         
         
         
         
         JMP INFINITE
         
 OP:
 CALL SETTEXT
MAIN ENDP

DELAY PROC
PUSH AX
PUSH CX
PUSH DX
PUSH BX
PUSH DI


  MOV DI, TDELAY
  MOV AH, 0
  INT 1AH
  MOV BX, DX
  GO1:
  MOV AH, 0
  INT 1AH
  SUB DX, BX
  CMP DI, DX
  JA GO1
       POP DI
       POP BX
       POP DX
       POP CX
       POP AX 
     
     
     RET  



DELAY ENDP

COL PROC
PUSH AX
PUSH BX
PUSH DX

MOV AX,CUR_POSX_USER
MOV V1,AX

MOV V2,AX
MOV AX,WIDTH1
ADD V2,AX



; FOR DRAW1
MOV AX,POSY1
CMP CUR_POSY_USER,AX
JL NM2
MOV BX,POSX1
MOV DX,BX
ADD DX,WIDTH1
CMP V1,BX
JNL NM1
CMP V2,DX
JNL NM1
CMP V2,BX
JNG NM1
MOV CF,1
POP DX
POP BX
POP AX
RET
NM1:
    CMP V1,BX
    JNG NM2
    CMP V2,DX
    JNG NM2
    CMP V1,DX
    JNL NM2
    MOV CF,1
    POP DX
    POP BX
    POP AX
    RET
    
;FOR DRAW2   
NM2:
    MOV AX,POSY2
    CMP CUR_POSY_USER,AX
    JG NM4
    MOV BX,POSX2
    MOV DX,BX
    ADD DX,WIDTH1
    CMP V1,BX
    JNL NM3
    CMP V2,DX
    JNL NM3
    CMP V2,BX
    JNG NM3
    MOV CF,1
    POP DX
    POP BX
    POP AX
    RET
    NM3:
    CMP V1,BX
    JNG NM4
    CMP V2,DX
    JNG NM4
    CMP V1,DX
    JNL NM4
    MOV CF,1
    POP DX
    POP BX
    POP AX
    RET




NM4:  
    ; FOR DRAW3
    MOV AX,POSY3
    CMP CUR_POSY_USER,AX
    JG NM6
    JL NM6
    MOV BX,POSX3
    MOV DX,BX
    ADD DX,WIDTH1
    CMP V1,BX
    JNL NM5
    CMP V2,DX
    JNL NM3
    CMP V2,BX
    JNG NM5
    MOV CF,1
    POP DX
    POP BX
    POP AX
    RET
    NM5:
    CMP V1,BX
    JNG NM6
    CMP V2,DX
    JNG NM6
    CMP V1,DX
    JNL NM6
    MOV CF,1
    POP DX
    POP BX
    POP AX
    RET
NM6:      
MOV CF,0
POP DX
POP BX
POP AX 
RET
COL ENDP
LISTEN_KEYBOARD PROC
  
   PUSH AX
   MOV AH,01H
   INT 16H
   JZ GO
   MOV AH,0H
   INT 16H
   CMP AH,48H
   JE UP
   CMP AH,50H
   JE DOWN
   JMP GO
   UP:
      CMP CUR_POSY_USER,66
      JL GO
      CALL REMOVECAR
      SUB CUR_POSY_USER,66
      PUSH CX
      PUSH DX
      MOV CX,CUR_POSX_USER
      MOV DX,CUR_POSY_USER
      MOV AL,1
      CALL DRAWCAR
      POP DX
      POP CX
      JMP GO
   DOWN:
       CMP CUR_POSY_USER,133
       JG GO
       CALL REMOVECAR
       ADD CUR_POSY_USER,66
       PUSH CX
       PUSH DX
       MOV CX,CUR_POSX_USER
       MOV DX,CUR_POSY_USER
       MOV AL,1
       CALL DRAWCAR
       POP DX
       POP CX
       JMP GO 
    GO:
      
      POP AX
      RET   
          
      

LISTEN_KEYBOARD ENDP
REMOVECAR PROC
PUSH CX
PUSH DX

MOV AH,0CH
MOV DX,CUR_POSY_USER
MOV CX,CUR_POSX_USER
CALL DRAWCAR

POP DX
POP CX
RET




REMOVECAR ENDP


DRAWSCREEN PROC
MOV AL,13H
MOV AH,0H
INT 10H
MOV AX,10
DRAW_ROW 66
DRAW_ROW 133

RET
DRAWSCREEN ENDP

DRAW1 PROC
         

CMP D1F,0
JE GO6         
MOV CX,POSX1
MOV DX,POSY1
MOV AL,0
CALL DRAWCAR         
CMP POSX1,0
JLE GO5  
CMP POSX1,150
JNE T1
MOV D2F,1
T1:       
SUB POSX1,5
MOV CX,POSX1
MOV DX,POSY1 
MOV AL,15
CALL DRAWCAR
MOV TDELAY,2
CALL DELAY
JMP GO6
GO5:
   ADD SCORE,5
   MOV D1F,0
   MOV POSX1,250
GO6:


    
RET    
DRAW1 ENDP
DRAW2 PROC
         
CMP D2F,0
JE GO4         
MOV CX,POSX2
MOV DX,POSY2
MOV AL,0
CALL DRAWCAR         
CMP POSX2,0
JLE GO3 
CMP POSX2,100
JNE T2
MOV D3F,1
T2:     
SUB POSX2,5
MOV CX,POSX2
MOV DX,POSY2 
MOV AL,15
CALL DRAWCAR
MOV TDELAY,2
CALL DELAY
JMP GO4
GO3:
   ADD SCORE,5
   MOV POSX2,250
GO4:
    
RET    
DRAW2 ENDP
DRAW3 PROC
         
CMP D3F,0
JE GO8         
MOV CX,POSX3
MOV DX,POSY3
MOV AL,0
CALL DRAWCAR         
CMP POSX3,0
JLE GO7 
CMP POSX3,50
JNE T3
MOV D1F,1
T3:     
SUB POSX3,5
MOV CX,POSX3
MOV DX,POSY3 
MOV AL,15
CALL DRAWCAR
MOV TDELAY,2
CALL DELAY
JMP GO8
GO7:
   ADD SCORE,5
   MOV POSX3,250
   GO8:
    
RET    
DRAW3 ENDP

GETSCORE PROC

    
    
   PUSH AX
   PUSH BX
   PUSH CX
   PUSH DX
   PUSH TOTAL
   PUSH VAR1
   MOV V1,10
   MOV AX,SCORE 
    
   MOV DX,0             ;CLEARING DX
   MOV DIVISOR,10       ;SETTING DIVISOR
   MOV CX,10000         ;SETTING DIVISOR TO DIVIDE THE OUTPUT
   FIRST_DIVISION:
   DIV CX                ;DIVISION  UNTIL A NUMBER EXCEPT ZERO
   CMP AX,0              ; CHECKING IF THE QUOTIENT IS ZERO
   JNE SECOND_DIVISION
   MOV VAR1,DX           ;SAVING REMAINDER
   MOV DX,0              ;CLEARING DX
   MOV AX,CX             ;MOVING CX TO AX TO DIVIDE
   DIV DIVISOR           ;DIVIDING BY 10
   MOV CX,AX             ;MOVING QUOTIENT TO DIVISOR
   MOV AX,VAR1           ; NOW AX HOLDS THE VALUE OF REMAINDER OF THE  DIVISION OF THE RESULT
   JMP FIRST_DIVISION    ;REPEAT
   SECOND_DIVISION:      ;NOW WE GOT A NONZERO DIGIT
   MOV VAR1,DX           ;STORING REMAINDER TO CONTINUE PROCESS
   MOV DL,AL             ;PRESENTATION PROCEDURE
   ADD DL,'0'
   MOV DH,0
   PUSH BX
   MOV BX,V1
   MOV STRING[BX],DX
   INC V1
   POP BX
   
   MOV DX,0              ;CLEARING DX FOR NEXT DIVISION
   MOV AX,CX             ;MOVING CX TO AX TO DIVIDE BY 10
   DIV DIVISOR           ;DIVIDING
   MOV CX,AX             ;STORING QUOTIENT
   CMP CX,0              ;CHECKING IF ZERO
   JE END_OF_OUTPUT      ;IF NOT CONTINUE
   MOV DX,0              ;CLEARING DX FOR NEXT DIVISION
   MOV AX,VAR1           ;NOW WE HAVE REMANDER OF OUTPUT DIVISION
   DIV CX                ;DIVIDING
   JMP SECOND_DIVISION   ;REPEAT
   END_OF_OUTPUT:        ;END    
   
   PUSH BX
   MOV BX,V1
   MOV STRING[BX],'$'
   INC V1
   POP BX
   DEC V1
   MOV AX,V1
   MOV STRINGL,AX
   
   
   POP VAR1
   POP TOTAL
   POP DX
   POP CX
   POP BX
   POP AX
   
          
   RET 
   
   



GETSCORE ENDP
SETTEXT PROC

CALL GETSCORE
MOV AX,@DATA        ; SET UP DS AS THE SEGMENT FOR DATA
MOV ES,AX       ; PUT THIS IN ES

MOV BP,OFFSET STRING  ; ES:BP POINTS TO MESSAGE
MOV AH,13H      ; FUNCTION 13 - WRITE STRING
MOV AL,01H      ; ATTRIB IN BL,MOVE CURSOR
XOR BH,BH       ; VIDEO PAGE 0
MOV BL,STRING_COLOR        ; ATTRIBUTE - MAGENTA
MOV CX,STRINGL       ; LENGTH OF STRING
MOV DH,STRINGR        ; ROW TO PUT STRING
MOV DL,STRINGC       ; COLUMN TO PUT STRING
INT 10H         ; CALL BIOS SERVICE

MOV AX,4C00H        ; RETURN TO DOS
INT 21H 
SETTEXT ENDP


END MAIN