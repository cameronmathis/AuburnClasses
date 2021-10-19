TITLE HW9-1 - Cameron Mathis (HW9-1.asm)
INCLUDE Irvine32.inc

.data
WelcomeMSG BYTE "Welcome! This program displays the first 8 terms of a Geometric Series on the screen.", 0
firstTermValuePrompt BYTE "Please enter the first term: ", 0
ratioValuePrompt BYTE "Please enter the ratio: ", 0
ArraySW SWORD 8 DUP(?)
count = 8

.code
main PROC
	mov edx, OFFSET WelcomeMSG
	call WriteString           ; print welcome message to screen
	call crlf                  ; formatting
	call crlf                  ; formatting
	mov edx, OFFSET firstTermValuePrompt
	call WriteString           ; prompt user for input
	call ReadInt               ; store user input in eax
	push eax
	mov edx, OFFSET ratioValuePrompt
	call WriteString           ; prompt user for input
	call ReadInt               ; store user input in eax
	push eax
	push OFFSET ArraySW

	call GeometricProgression

	mov esi, OFFSET ArraySW
	mov ebx, 0

	mov ecx, [8]
	L1:
		movsx eax, WORD PTR [esi + ebx]
		add ebx, TYPE ArraySW
		call WriteInt
		call crlf
	loop L1

	exit
main ENDP

GeometricProgression PROC
	push ebp
	mov ebp, esp
	mov eax, [ebp + 16]			; first term
	mov esi, [ebp + 8]			; array
	mov ebx, 0
	mov [esi + ebx], eax

	mov ecx, 7
	L2:
		mov eax, [esi + ebx]
		add ebx, TYPE ArraySW
			mul DWORD PTR [ebp + 12]
			shl edx, 4
			add eax, edx
		mov [esi + ebx], eax
	loop L2

	pop ebp
	
	ret 16
GeometricProgression ENDP

END Main