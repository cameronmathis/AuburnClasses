TITLE HW6-3 - Cameron Mathis (HW6-3.asm)
INCLUDE Irvine32.inc

.data
WelcomeMsg BYTE "Welcome! This program accepts six integers as input finds the element closest to zero then displays the value and the index of the value", 0
PromptUser  	BYTE "Please input a value: ", 0
spacing 	BYTE ", ",0 
String1	BYTE "The target value is ",0
String2	BYTE "and is located at index: ",0
ArraySW SWORD 6 DUP(?)
Target SWORD ?
TargetIndice WORD ?
temp WORD ?

.code
;main PROC
;	mov eax, 0                    ; initialize eax to 0
;	mov ebx, 0                    ; initialize indice counter to 0
;	mov ecx, 6                    ; loop counter
;	mov edx, OFFSET WelcomeMsg
;	call WriteString              ; displays welcome message
;	call crlf                     ; formatting
;	call crlf                     ; formatting
;	mov edx, OFFSET PromptUser
;
;	L1:
;		call WriteString          ; displays prompt for user input
;		call ReadInt              ; reads user input
;		mov ArraySW[ebx], ax      ; stores user input in available array indice
;		add ebx, 2                ; points to next indice in array
;		loop L1                   ; loops 6 times for each value
;
;	mov ebx, 0                    ; clears ebx for procedure call
;	mov ecx, 6                    ; resets ecx value to 6
;	call crlf                     ; formatting
;	call crlf                     ; formatting
;	call findMinor                ; call findMinor procedure
;	mov edx, OFFSET String1
;	call WriteString              ; print first part of message
;	movsx eax, Target             ; store Target value in ax for next procedure call
;	call WriteInt                 ; write Target value to screen
;	mov edx, OFFSET spacing
;	call WriteString              ; print spacing
;	mov edx, OFFSET String2
;	call WriteString              ; print second part of message
;	movsx eax, TargetIndice       ; store TargetIndice value in ax for next procedure call
;	call WriteInt                 ; write TargetIndice value to screen
;	call crlf                     ; formatting
;	exit
;main ENDP                         ; end main procedure

findMinor PROC USES ebx
	movsx eax, ArraySW[ebx]
	mov temp, ax
	L1:
		movsx eax, ArraySW[ebx]
		cmp ax, 0
		jge J1                     ; jumps to J1 if ax >= 0
		neg eax
		jmp J1
	J1:
		mov edx, eax
		cmp ax, temp
		jle J2
		add ebx, 2
		loop L1
		jmp J3
	J2:
		mov TargetIndice, bx
		mov temp, ax
		add ebx, 2  
		loop L1
		jmp J3
	J3:
	    mov bx, TargetIndice
		mov ax, bx
		mov bx, ArraySW[bx]     
mov Target, bx
		mov bx, 2
		div bl
		mov TargetIndice, ax
		ret                            
findMinor ENDP                     ; end findMinor procedure

END ;main                           ; end program