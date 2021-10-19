TITLE HW10-3 - Cameron Mathis (HW10-3.asm)
INCLUDE Irvine32.inc
NumPoints = 10

MyCOORD STRUCT
	X SWORD ?                                 ; offset 00
	Y SWORD ?                                 ; offset 02
	Z SWORD ?								  ; offset 04
MyCOORD ENDS

.data
ALIGN WORD
AllPoints MyCOORD NumPoints DUP(<0,0,0>)
WelcomeMSG BYTE "Welcome! This program accepts 10 coordinate inputs from the user, then displays all of the x coordinate values.", 0
promptX BYTE "Please enter an X value: ", 0
promptY BYTE "Please enter a Y value: ", 0
promptZ BYTE "Please enter a Z value: ", 0
displayPrompt BYTE "The X values are: ", 0

.code
main PROC
	mov edx, OFFSET WelcomeMSG
	call WriteString                         ; display welcome message
	call crlf                                ; formatting
	call crlf                                ; formatting

	mov edi, 0                               ; array index
	mov ecx, NumPoints                       ; loop counter
	L1: 
		mov eax, 0                           ; reset eax
		mov edx, OFFSET promptX
		call WriteString                     ; prompt user for x coordinate
		call ReadInt                         ; read input
		mov (MyCOORD PTR AllPoints[edi]).X, ax ; store input in X coordinate value at index edi
		mov edx, OFFSET promptY
		call WriteString                     ; prompt user for y coordinate
		call ReadInt                         ; read input
		mov (MyCOORD PTR AllPoints[edi]).Y, ax ; store input in Y coordinate value at index edi
		mov edx, OFFSET promptZ
		call WriteString                     ; prompt user for z coordinate
		call ReadInt                         ; read input
		mov (MyCOORD PTR AllPoints[edi]).Z, ax ; store input in Z coordinate value at index edi
		add edi, TYPE MyCOORD                  ; set edi to point to next coordinate value
		loop L1                              ; loop

	call crlf                                ; formatting
	call crlf                                ; formatting
	mov edx, OFFSET displayPrompt
	call WriteString                         ; prompt informing user that x values will be displayed
	call crlf                                ; formatting
	mov ebx, OFFSET AllPoints                ; set ebx to first value in coordinate array
	mov esi, 0                               ; offset of X value
	mov eax, 0                               ; reset eax
	mov ecx, NumPoints                       ; loop counter
	L2:
		mov ax, [ebx+esi]                    ; move current displaced value into ax
		call WriteInt                        ; display x value to screen
		call crlf                            ; formatting
		add ebx, SIZEOF MyCOORD              ; set ebx to next value in coordinate array
		loop L2                              ; loop
	exit
main ENDP
END main