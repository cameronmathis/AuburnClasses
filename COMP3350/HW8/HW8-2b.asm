TITLE HW8-2b - Cameron Mathis (HW8-2b.asm)
INCLUDE Irvine32.inc

.data
WelcomeMSG BYTE "Welcome! This program takes the value 1234ABCDh and stores the first 2 decimal numbers in dh, and also stores the last 2 decimal numbers in dl.", 0
dhValuePrompt BYTE "DH has the decimal value: ", 0
dlValuePrompt BYTE "DL has the decimal value: ", 0
dlValue WORD ?

.code
main PROC
	mov edx, OFFSET WelcomeMSG
	call WriteString              ; display welcome message
	call crlf                     ; formatting
	call crlf                     ; formatting
	mov eax, 1234ABCDh            ; initial value (eax = 0001 0010 0011 0100 1010 1011 1100 1101)
	mov edx, 0                    ; clear edx
	ror eax, 16
	mov dx, ax
	movzx ebx, dx


	mov edx, OFFSET dhValuePrompt 
	call WriteString              ; prompt user that dh value will be displayed
	movzx eax, bh                 ; zero-extend dh to eax for display
	call WriteHex                 ; display dh value
	call crlf                     ; formatting
	mov edx, OFFSET dlValuePrompt
	call WriteString              ; prompt user that dl value will be displayed
	movzx eax, bl                 ; zero-extend dlValue to eax for display
	call WriteHex                 ; display dl value
	call crlf                     ; formatting
	mov edx, ebx

	exit
main ENDP
END main