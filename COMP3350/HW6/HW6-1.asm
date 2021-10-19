TITLE HW6-1 - Cameron Mathis (HW6-1.asm)
INCLUDE Irvine32.inc

.data
asterisk BYTE 2Ah
newLine BYTE 0Ah

.code
;main PROC
;	mov ecx, 5
;	
;L1:
;	mov eax, DWORD PTR [asterisk]
;	push ecx
;	mov ecx, 5
;	L2:
;		call WriteChar
;	loop L2
;	pop ecx
;	mov eax, DWORD PTR [newLine]
;	call WriteChar
;	loop L1
;
;exit
;main ENDP
END ;main