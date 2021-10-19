TITLE HW7-1 - Cameron Mathis (HW7-1.asm)
INCLUDE Irvine32.inc

.data
ArraySW SWORD 8 DUP(?)
welcomeText BYTE "Welcome! This program takes 8 signed integers as input into an array, then finds the first even number and its indice (if it exists).", 0
prompt BYTE "Please enter an integer: ", 0
firstEvenInteger BYTE "The first even integer in the program is: ", 0
evenIndice BYTE ", and it is located at indice ", 0
evenIndicator BYTE "Even integer found!", 0
noEvenIndicator BYTE "No even integer found!", 0
evenVal SWORD 0
evenIndex WORD 0

.code
main PROC

	mov edx, OFFSET welcomeText
	call WriteString                   ; print welcome prompt
	call crlf                          ; formatting
	call crlf                          ; formatting
	mov edx, OFFSET prompt
	mov ebx, 0                         ; clear ebx
	mov ecx, 8                         ; loop counter = 8
	L1:
		call WriteString               ; prompt user for input
		call ReadInt                   ; get input
		mov ArraySW[ebx], ax           ; store input in array
		add ebx, 2                     ; advance to next spot in array
		loop L1                        ; loop

	mov eax, 0                         ; clear eax
	mov ebx, 0                         ; clear ebx
	mov ecx, 8                         ; loop counter = 8
	L2:
		movsx eax, ArraySW[ebx]        ; sign extend array value at position ebx to eax
		test ax, 1                     ; check if even or odd
		jnz J1                         ; jump to J1 if odd
		jz J2                          ; jump to J2 if even
	J1:                            ; start of J1
		add ebx, 2                     ; advance to next position in array
		mov edx, OFFSET noEvenIndicator; set edx to indicate no even value is found
		loop L2                        ; loop

	J2:                                ; start of J2 (breaks out of L2)
	mov ecx, 1                         ; loop counter = 1
	test ax, 1                         ; check if even or odd
	jnz J3                             ; jump to J3 if odd (skip next 8 lines)
	mov evenVal, ax                    ; move current even value (first found) to evenVal
	mov eax, ebx                       ; move ebx (array indice) value to eax
	mov bx, 2                          ; move divisor 2 to bx
	div bl                             ; divide array indice in eax by 2
	mov evenIndex, ax                  ; move quotient into evenIndex
	mov edx, OFFSET evenIndicator      ; set edx to indicate even value is found
	movsx eax, evenVal                 ; reset

	J3:                                ; start of J3 (jump to displaying values)
	call crlf                          ; formatting
	call crlf                          ; formatting
	call WriteString                   ; display whether even value is found or not
	test ax, 1                         ; check if even or odd
	jnz J4                              ; jump to J4 if even (skip next 9 lines)
	call crlf                          ; formatting
	mov edx, OFFSET firstEvenInteger
	call WriteString                   ; text before displaying even value to screen
	movsx eax, evenVal                 ; sign-extend even value to eax
	call WriteInt                      ; display even value to screen
	mov edx, OFFSET evenIndice
	call WriteString                   ; text before displaying even value indice to screen
	movzx eax, evenIndex               ; zero-extend even value indice to eax
	call WriteInt                      ; display even value indice to screen
	J4:                                ; start of J4
	call crlf                          ; formatting

exit
main ENDP
END main