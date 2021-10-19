TITLE HW9-2 - Cameron Mathis (HW9-2.asm)
INCLUDE Irvine32.inc

.data
myIDPrompt BYTE "MyAuburnID: ", 0
newIDPrompt BYTE "MyAuburnIDRev: ", 0
resultPrompt BYTE "Result: ", 0
myAuburnID WORD 09h,03h,82h,86h,85h
myAuburnIDRev WORD 5 DUP(?)
count = 5
Result SWORD 5 DUP(?)

.code
main PROC
	mov edx, OFFSET myIDPrompt
	call WriteString           ; print message to screen
	call crlf                  ; formatting
	mov esi, OFFSET myAuburnID
	mov ebx, 0
	mov ecx, 5
	L1:
		movsx eax, WORD PTR [esi + ebx]
		add ebx, TYPE myAuburnID
		call WriteHex
		call crlf
	loop L1
	call crlf

	mov ecx, 5
	mov ebx, 4
	mov edx, 0
	L2:
		mov ax, WORD PTR myAuburnID[ebx*2]
		SHRD  myAuburnIDRev[edx*2], ax, 16
		dec ebx
		inc edx
	loop L2

	mov edx, OFFSET newIDPrompt
	call WriteString           ; print message to screen
	call crlf                  ; formatting
	mov esi, OFFSET myAuburnIDRev
	mov ebx, 0
	mov ecx, 5
	L3:
		movsx eax, WORD PTR [esi + ebx]
		add ebx, TYPE myAuburnID
		call WriteHex
		call crlf
	loop L3
	call crlf

	mov ecx, 5
	mov ebx, 4
	L4:
		mov ax, WORD PTR myAuburnID[ebx*2]
		sub ax, myAuburnIDRev[ebx*2]
		das
		mov Result[ebx*2], ax
		dec ebx
	loop L4

	mov edx, OFFSET resultPrompt
	call WriteString           ; print message to screen
	call crlf                  ; formatting
	mov esi, OFFSET Result
	mov ebx, 0
	mov ecx, 5
	L5:
		movsx eax, WORD PTR [esi + ebx]
		add ebx, TYPE myAuburnID
		call WriteHex
		call crlf
	loop L5
	call crlf

	exit
main ENDP
END Main