TITLE HW8-2a - Cameron Mathis (HW8-2a.asm)
INCLUDE Irvine32.inc

.data
WelcomeMSG BYTE "Welcome! This program calculates the sum of areas of shrinking isosceles triangles. The starting triangle has a height of 14 and base of 8", 0
closedFormMSG BYTE "This is the area calculated using the closed form of the series: ", 0
simpleMSG BYTE "This is the area calculated using the first two sums: ", 0
differenceMSG BYTE "The difference in the two areas is: ", 0

.code
main PROC
	mov eax, 0
	mov eax, 14
	shl eax, 3                 ; 14 * 8 = 112
	shr eax, 1                 ; 112 / 2 = 56

	mov ebx, eax

	shr eax, 3                 ; 56 / 8 = 7
	shl eax, 2                 ; 7 * 4 = 28
	shr eax, 1				   ; 28 / 2 = 14
	add eax, ebx			   ; 56 + 14 = 70
	mov ecx, eax

	mov edx, OFFSET simpleMSG
	call crlf                  ; formatting
	call WriteString           ; display message telling user that final value will be printed
	call WriteInt              ; print final value to screen
	call crlf                  ; formatting

	mov eax, 14
	shr eax, 2				   ; 14 / 4
	add eax, 1				   ; (14 / 4) + 1 = 14 / 3 !approximately!
	mov ebx, 56
	shr ebx, 2				   ; 56 / 4
	add ebx, eax			   ; (56 / 4) + (14 / 3) = 56 / 3
	mov eax, 224
	shr eax, 2				   ; 224 / 4
	add eax, ebx			   ; (224 / 4) + (56 / 3) = 224 / 3

	mov edx, OFFSET closedFormMSG
	call crlf                  ; formatting
	call WriteString           ; display message telling user that final value will be printed
	call WriteInt              ; print final value to screen
	call crlf				   ; formatting

	sub eax, ecx

	mov edx, OFFSET differenceMSG
	call crlf                  ; formatting
	call WriteString           ; display message telling user that final value will be printed
	call WriteInt              ; print final value to screen
	call crlf				   ; formatting

	exit
main ENDP
END main