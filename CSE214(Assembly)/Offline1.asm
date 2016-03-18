TITLE PGM4_1.5: SAMPLE INPUT
.MODEL SMALL
.STACK 100H
.DATA 
var1 DB ?  
var2 DB ?
var3 DB ?
remainder1_by_2 DB  ?
remainder2_by_1 DB  ? 
msgi1 DB 'Enter the First Number: $'  
msgi2 DB 'Enter the Second Number: $' 
msgo1 DB 'Quotient of the First / Second division is: $'    
msgo2 DB 'Quotient of the Second / First division is: $' 
msgo3 DB 'Remainder of the First / Second division is: $'    
msgo4 DB 'Remainder of the Second / First division is: $'
.CODE
MAIN PROC
     
   MOV AX,@DATA
   MOV DS,AX   
   lea dx,msgi1
   mov ah,9
   int 21h  
    
   mov dh,0
   mov ah,1
   int 21h
   sub al,'0'
   mov bh,al  
   int 21h
   sub al,'0'
   mov bl,al
   int 21h
   sub al,'0'
   mov ch,al     
   
   mov  ah,100  
   mov  al,bh  
   ; 3rd digit
   logic_for_multiplication1: 
   cmp al,0
   ja multiplication1
   jmp end1
   multiplication1:
   dec al
   add dh,ah
   jmp logic_for_multiplication1
   end1:
   mov  ah,10  
   mov  al,bl     
   ;2nd digit  
   logic_for_multiplication2: 
   cmp al,0
   ja multiplication2
   jmp end2
   multiplication2:
   dec al
   add dh,ah
   jmp logic_for_multiplication2 
   end2:
   ;1st digit
   add dh,ch 
   mov dl,0dh
   mov ah,2
   int 21h
   mov dl,0ah
   int 21h
   
   mov dl,dh 
      
      
  
   
   ; 2nd number
   mov bl,dl
   lea dx,msgi2
   mov ah,9
   int 21h
   mov dl,bl
    
   mov dh,0
   mov ah,1
   int 21h
   sub al,'0'
   mov bh,al  
   int 21h
   sub al,'0'
   mov bl,al
   int 21h
   sub al,'0'
   mov ch,al     
   
   mov  ah,100  
   mov  al,bh  
   ; 3rd digit  
   logic_for_multiplication3: 
   cmp al,0
   ja multiplication3
   jmp end3 
   multiplication3:
   dec al
   add dh,ah
   jmp logic_for_multiplication3
   end3:
   mov  ah,10  
   mov  al,bl     
   ;2nd digit   
   logic_for_multiplication4: 
   cmp al,0
   ja multiplication4 
   jmp end4
   multiplication4:
   dec al
   add dh,ah
   jmp logic_for_multiplication4  
   end4:
     
   ;1st digit
   add dh,ch
   
   mov bh,dl 
   mov dl,0dh
   mov ah,2
   int 21h
   mov dl,0ah
   int 21h
   mov dl,bh
    
     
   ;quotient of first and second     
   ;finding quotient 1st by 2nd
   
   mov bh,dl
   mov bl,dh
   mov cx,0
   
   logic_for_division1: 
   
   
   cmp bh,bl
   jae division1
   jmp end5
   
   division1:
   inc cx
   sub bh,bl
   jae logic_for_division1
   
   end5:
   ;done finding quotient 1st by 2nd  
   mov remainder1_by_2,cl
   mov al,cl
   mov bl,10
   
   logic1: 
   
   
   cmp al,bl
   jnb remainder1
   jmp end6
   
   remainder1:
   sub al,bl
   jge logic1  
   end6:
   mov var1,al
   mov var3, cl; division result  to var3 
    
   mov al,var3
   mov bl,10
   mov cl,0
   
   logic2: 
   
   
   cmp al,bl
   jae division_for_2nd
   jmp end7
   
   division_for_2nd:
   inc cl
   sub al,bl
   jae logic2  
   end7: ; for 2nd didgit
   mov al,cl
   mov bl,10
   
   logic3: ; remainder 2
   
   
   cmp al,bl
   jnb remainder2
   jmp end8
   
   remainder2:
   sub al,bl
   jae logic3  
   end8:
   mov var2,al    ; now var1 3rd digit,var2 2 nd digit 
   mov al,var3
   mov bl,100
   mov cl,0
   
   last_logic: 
   
   
   cmp al,bl
   jae last_division
   jmp end9
   
   last_division:
   inc cl
   sub al,bl
   jae last_logic  
   end9: 
   mov var3,dl 
   mov bh,dh
   lea dx,msgo1
   mov ah,9
   int 21h 
   mov dl,cl 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var2 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var1 
   add dl,'0'
   mov ah,2
   int 21h
   mov dl,0dh
   int 21h
   mov dl,0ah
   int 21h 
   mov dl,var3  
   mov dh,bh
   ; done qoutient of 1st by 2nd 
   
   
   
   ;quotient ofsecond and first 
   ;finding quotient 2nd by 1st
   
   mov bh,dh
   mov bl,dl
   mov cx,0
   
   logic_for_division_2nd_by_1st: 
   
   
   cmp bh,bl
   jae division_2nd_by_1st
   jmp end10
   
  division_2nd_by_1st:
   inc cx
   sub bh,bl
   jae logic_for_division_2nd_by_1st
   
   end10:
   ;done finding quotient 2nd by 1st
   mov remainder2_by_1,cl; saving in memory
   mov al,cl
   mov bl,10
   
   logic_remainder1: 
   
   
   cmp al,bl
   jnb remainder_2nd_by_1st1
   jmp end11
   
   remainder_2nd_by_1st1:
   sub al,bl
   jae logic_remainder1 
   end11:
   mov var1,al
   mov var3, cl; division result  to var3 
    
   mov al,var3
   mov bl,10
   mov cl,0
   
   2ndby _1st2: 
   
   
   cmp al,bl
   jae division_for_2ndby_1st2
   jmp end12
   
   division_for_2ndby_1st2:
   inc cl
   sub al,bl
   jae 2ndby_1st2  
   end12: ; for 2nd didgit
   mov al,cl
   mov bl,10
   
   logic_2nd_by_1st_remainder2: ; remainder 2
   
   
   cmp al,bl
   jnb 2nd_by_1st_remainder2
   jmp end13
   
   2nd_by_1st_remainder2:
   sub al,bl
   jae logic_2nd_by_1st_remainder2  
   end13:
   mov var2,al    ; now var1 3rd digit,var2 2 nd digit 
   mov al,var3
   mov bl,100
   mov cl,0
   
   last_logic_for2nd_by_1st: 
   
   
   cmp al,bl
   jae  last_division_for2nd_by_1st
   jmp end14
   
   last_division_for2nd_by_1st:
   inc cl
   sub al,bl
   jae last_logic_for2nd_by_1st  
   end14: 
   mov var3,dl 
   mov bh,dh 
   lea dx,msgo2
   mov ah,9
   int 21h
   
   mov dl,cl 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var2 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var1 
   add dl,'0'
   mov ah,2
   int 21h 
   mov dl,0dh
   int 21h
   mov dl,0ah
   int 21h   
   mov dl,var3
   mov dh,bh
   ; done qoutient of 2nd by 1st 
   ;finding remainder 1st by 2nd 
   mov var1,0    
   logic_for_remainder_1st_by_2nd:
   cmp remainder1_by_2,0 
   ja remainder_1st_by_2nd 
   jmp end15
   remainder_1st_by_2nd:
   dec remainder1_by_2
   add var1,dh
   jmp logic_for_remainder_1st_by_2nd      
   end15:
   mov var2,dl
   sub dl,var1
   mov remainder1_by_2,dl
   mov dl,var2  
   ;found quotient
   ;showing it
   mov al,remainder1_by_2
   mov bl,10
   
   logic_remainder1_by_2_1: 
   
   
   cmp al,bl
   jnb  remainder_finding_1_by_2_1
   jmp end16
   
   remainder_finding_1_by_2_1:
   sub al,bl
   jae logic_remainder1_by_2_1
   end16:
   mov var1,al
  ; mov var3, cl; division result  to var3 
    
   mov al,remainder1_by_2
   mov bl,10
   mov cl,0
   
   remainder_find_division_1stby_2nd2: 
   
   
   cmp al,bl
   jae remainder_find_division_for_1stby_2nd2
   jmp end17
   
   remainder_find_division_for_1stby_2nd2:
   inc cl
   sub al,bl
   jae remainder_find_division_1stby_2nd2: 
   end17: ; for 2nd didgit
   mov al,cl
   mov bl,10
   
   logic_remainder1_by_2_2: ; remainder 2
   
   
   cmp al,bl
   jnb remainder_find_1st_by_2nd_remainder2
   jmp end18
   
   remainder_find_1st_by_2nd_remainder2:
   sub al,bl
   jge logic_remainder1_by_2_2  
   end18:
   mov var2,al    ; now var1 3rd digit,var2 2nd digit 
   mov al,remainder1_by_2
   mov bl,100
   mov cl,0
   
   last_logic_remmainder_find_for1st_by_2nd: 
   
   
   cmp al,bl
   jae  last_division_remainder_find_for1st_by_2nd
   jmp end19
   
   last_division_remainder_find_for1st_by_2nd:
   inc cl
   sub al,bl
   jae last_logic_remmainder_find_for1st_by_2nd:   
   end19: 
   mov var3,dl 
   mov bh,dh 
   lea dx,msgo3
   mov ah,9
   int 21h
   
   mov dl,cl 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var2 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var1 
   add dl,'0'
   mov ah,2
   int 21h 
   mov dl,0dh
   int 21h
   mov dl,0ah
   int 21h 
   mov dh,bh
   mov dl,var3 
   ;done finding remainder 1 by 2
   ;finding remainder 2 by 1 
   mov var1,0     
   logic_for_remainder_2nd_by_1st:
   cmp remainder2_by_1,0 
   ja remainder_2nd_by_1st 
   jmp end20
   remainder_2nd_by_1st :
   dec remainder2_by_1
   add var1,dl
   jmp logic_for_remainder_2nd_by_1st      
   end20:
   mov var2,dh
   sub dh,var1
   mov remainder2_by_1,dh
   mov dh,var2  
   ;found remainder
   ;showing it
   mov al,remainder2_by_1
   mov bl,10
   
   logic_remainder2_by_1_1: 
   
   
   cmp al,bl
   jnb  remainder_finding_2_by_1_1
   jmp end21
   
   remainder_finding_2_by_1_1:
   sub al,bl
   jae logic_remainder2_by_1_1
   end21:
   mov var1,al
   ;mov var3, cl; division result  to var3 
    
   mov al,remainder2_by_1
   mov bl,10
   mov cl,0
   
   remainder_find_division_2ndby_1st2: 
   
   
   cmp al,bl
   jae remainder_find_division_for_2ndby_1st2
   jmp end22
   
   remainder_find_division_for_2ndby_1st2:
   inc cl
   sub al,bl
   jae remainder_find_division_2ndby_1st2: 
   end22: ; for 2nd didgit
   mov al,cl
   mov bl,10
   
   logic_remainder2_by_1_2: ; remainder 2
   
   
   cmp al,bl
   jnb remainder_find_2nd_by_1st_remainder2
   jmp end23
   
   remainder_find_2nd_by_1st_remainder2:
   sub al,bl
   jae logic_remainder2_by_1_2  
   end23:
   mov var2,al    ; now var1 3rd digit,var2 2 nd digit 
   mov al,remainder2_by_1
   mov bl,100
   mov cl,0
   
   last_logic_remmainder_find_for2nd_by_1st: 
   
   
   cmp al,bl
   jae  last_division_remainder_find_for2nd_by_1st
   jmp end24
   
   last_division_remainder_find_for2nd_by_1st:
   inc cl
   sub al,bl
   jae last_logic_remmainder_find_for2nd_by_1st:   
   end24: 
   mov var3,dl 
   mov bh,dh 
   lea dx,msgo4
   mov ah,9
   int 21h
  
   
   mov dl,cl 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var2 
   add dl,'0'
   mov ah,2
   int 21h  
   mov dl,var1 
   add dl,'0'
   mov ah,2
   int 21h 
   mov dl,0dh
   int 21h
   mov dl,0ah
   int 21h
   mov dl,var3 
                 
                 
                 
   
   
   
   
  
   
   
   MOV AH,4CH
   INT 21H
   
   
   
    
   
   
   
MAIN ENDP
    END MAIN