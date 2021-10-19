TITLE HW7-3 - Cameron Mathis (HW7-3.asm)
INCLUDE Irvine32.inc

.data

.code
;main PROC
;	mov ecx, 0000000Ch
;	mov ebx, 0000000Bh
;	call fMul
;	mov eax, ebx
;main ENDP

fMul PROC
	Push ecx
	Push ebx
	mov eax, edx

	Pop ebx
	Pop ecx
	ret
fMul ENDP

END Main