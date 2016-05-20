.model small
draw_row Macro x
    Local l1
; draws a line in row x from col 10 to col 300
    MOV AH, 0CH
    MOV AL, 4
    MOV CX, 0
    MOV DX, x
L1: INT 10h
    INC CX
    CMP CX, 320
    JL L1
    EndM
.stack 100h
.data
height1 dw 51 
width1 dw 65
 v1 dw ?
 v2 dw ?
CUR_POSX_USER  DW 61
CUR_POSY_USER  DW 75
VAR DB ?
POSX1 DW 250
POSY1 DW 141
POSX2 DW 250
POSY2 DW 10
CF DW 0


TDELAY DW ?

.code

drawcar proc

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
       l2:INT 10H
          INC DX
          CMP DX,V2
          JL L2
    POP DX
    CMP CX, V1        
    JL L1
    ;MOV AH,0
    ;int 16H
    
    ret
   
       
    
    RET 
drawcar endp
main proc
MOV AX,@DATA
MOV DS,AX
call drawscreen


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
         call draw2
         
         ;CALL SETCHAR
         
         
         
         jmp INFINITE
         
 OP:
 MOV AH,4CH
INT 21H 
main endp

DELAY PROC
PUSH AX
PUSH CX
PUSH DX
PUSH BX
PUSH DI


  MOV DI, TDELAY
  MOV AH, 0
  INT 1Ah
  MOV BX, DX
  GO1:
  MOV AH, 0
  INT 1Ah
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


drawscreen proc
mov al,13H
mov ah,0h
int 10h
mov ax,10
draw_row 66
draw_row 133

ret
drawscreen endp

DRAW1 PROC
         
         
MOV CX,POSX1
MOV DX,POSY1
MOV AL,0
CALL DRAWCAR         
CMP POSX1,0
JLE GO5         
SUB POSX1,5
MOV CX,POSX1
MOV DX,POSY1 
MOV AL,15
CALL DRAWCAR
MOV TDELAY,2
CALL DELAY
JMP GO6
GO5:
   MOV POSX1,250
GO6:


    
RET    
DRAW1 ENDP
DRAW2 PROC
         
         
MOV CX,POSX2
MOV DX,POSY2
MOV AL,0
CALL DRAWCAR         
CMP POSX2,0
JLE GO3      
SUB POSX2,5
MOV CX,POSX2
MOV DX,POSY2 
MOV AL,15
CALL DRAWCAR
MOV TDELAY,2
CALL DELAY
JMP GO4
GO3:
   MOV POSX2,250
GO4:
    
RET    
DRAW2 ENDP

SETCHAR PROC
PUSH AX
PUSH BX
PUSH CX



MOV DH,250
MOV DL,200
MOV AH,2
MOV BH,0
INT 10H
MOV AL,'A'
MOV CX,1
MOV BH,0
MOV AH,0AH
INT 10H
POP CX
POP BX
POP AX
RET

SETCHAR ENDP


end main