TITLE HW8-2a - Cameron Mathis (HW8-2a.asm)
INCLUDE Irvine32.inc

.data
WelcomeMSG BYTE "Welcome! This program accepts a value between -2,147,483,648 and +2,147,483,647 and performs binary multiplication between that number and 28 before writing the result to the screen.", 0
Prompt BYTE "Please input your desired value (an integer between -2,147,483,648 and +2,147,483,647): ", 0
DisplayMSG BYTE "Your input multiplied by 28 is: ", 0
InitialVal DWORD 0

.code
main PROC
	mov edx, OFFSET WelcomeMSG
	call WriteString           ; print welcome message to screen
	call crlf                  ; formatting
	call crlf                  ; formatting
	mov edx, OFFSET Prompt
	call WriteString           ; prompt user for input
	call ReadInt               ; store user input in eax
	mov InitialVal, eax        ; store user input in InitialVal variable

	mov ebx, InitialVal        ; assign InitialVal value to ebx
	mov edx, InitialVal        ; assign InitialVal value to edx
	shl eax, 4                 ; InitialVal * 16
	shl ebx, 3                 ; InitialVal * 8
	shl edx, 2                 ; InitialVal * 4
	add eax, ebx               ; eax = (InitialVal * 16) + (InitialVal * 8) = InitialVal * 24
	add eax, edx               ; eax = (InitialVal * 16) + (InitialVal * 8) + (InitialVal * 4) = InitialVal * 28
	mov edx, OFFSET DisplayMSG
	call crlf                  ; formatting
	call crlf                  ; formatting
	call WriteString           ; display message telling user that final value will be printed
	call WriteInt              ; print final value to screen
	call crlf                  ; formatting
	exit
main ENDP
END main