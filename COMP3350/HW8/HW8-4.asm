TITLE HW8-4 - Cameron Mathis (HW8-4.asm)
INCLUDE Irvine32.inc

.data
WelcomeMSG BYTE "Welcome! This program displays the contents of AX in binary on the screen.", 0
hexValuePrompt BYTE "AX has the HEX value: ", 0
binaryValuePrompt BYTE "AX has the Binary value: ", 0

.code
main PROC
	add eax, 30

	movzx ebx, ax
	mov edx, OFFSET WelcomeMSG
	call WriteString              ; display welcome message
	call crlf                     ; formatting
	call crlf                     ; formatting
	mov edx, OFFSET hexValuePrompt 
	call WriteString           
	movzx eax, ax
	call WriteHex
	call crlf                     ; formatting
	mov edx, OFFSET binaryValuePrompt 
	call WriteString 
	mov edx, 8
	mov eax, 0

	mov ecx, 15
	L1: 
		rcl bx, 1
		jc yes
		jnc no
		back1:
		cmp ecx, 8
		je print
		back2:
		shl eax, 4
	loop L1
	call WriteHex

	exit

	yes:
		add eax, 1
		jmp back1
	no:
		add eax, 0
		jmp back1

	print:
		call WriteHex
		jmp back2
main ENDP
END main