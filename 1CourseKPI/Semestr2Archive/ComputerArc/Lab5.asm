TITLE ЛР_5 
; ЛР  №5
;------------------------------------------------------------------------------
; Архітектура комп'ютера
; ВУЗ:          КНУУ "КПІ"
; Факультет:    ФІОТ
; Курс:         1
; Група:        ІТ-03
;------------------------------------------------------------------------------
; Автори:       Філянін Чабан Хамад
; Команда:      №6
; Дата:         14/04/2021
;------------------------------------------------------------------------------
IDEAL			; Директива - тип Асемблера tasm 
MODEL small		; Директива - тип моделі пам’яті 
STACK 2048		; Директива - розмір стеку 

DATASEG
array2Db db "7358118726277179" ; створення масиву, включаючи дати народження
         db "4627022455147997"
         db "6691608838469303"
         db "6176396115203803"
         db "6299944599303642"
         db "1325025082002837"
         db "2080023112002536"
         db "6299915112003642"
         db "4627025082002997"
         db "6691623112002303"
         db "6176315112003803"
         db "6299925082002642"
         db "6299923112002642"
         db "1325082956458837"
         db "2080054842790953"
         db "6299944599303642"

CODESEG
; В di зберігається останній індекс відсорованої частини масиву
; В al по ходу зберігатиметься обраниий найменший елемент
Sort:                              ; мітка початку сортування
outer:                             ; початок циклу для сортування вибором
	push cx						   ; cx в стек
	mov cx, 255					   ; в cx кількість елементів більшого масиву
	sub cx, di					   ; зменшуємо кількість ітерацій на кількість обраних елементів
	mov al, [array2Db + si]		   ; поміщаємо перший елемент в al
	inner:
		inc si					   ; збільшуємо si на 1
		cmp al, [array2Db+si]	   ; порівнюємо елемент al
		jg swap					   ; якщо елемент більший за al
		loop inner
	jmp endmarker				   ; якщо цикл закінчився
swap:							   ; заміна останнього елементу відсорованої частини масиву
	mov bl, al				       ; bl <- al
	mov al, [array2Db + si]		   ; al <- нове найменше значення
	mov [array2Db + si], bl		   ; перше невідсортоване значення масиву поміщаємо в нову комірку
	mov [array2Db + di], al		   ; в перше невідсортоване значення масиву поміщаємо нове
	loop inner		
endmarker:								
	inc di					; збільшуємо si на 1
	mov si, di				; si <- di
	pop cx					; повертаємо значення зі стеку
	loop outer              ; перевірка cx
    jmp AfterSort           ; повертаємося з Sort 

Start:
    mov ax, @data       ; занесення початку датасегменту до ax
    mov ds, ax          ; ініціалізація датасегменту
    mov es, ax          ; ініціалізація додаткового сегменту
    mov cx, 255         ; в cx кількість елементів масиву
	xor di, di			; зачищаємо di
	xor si, si			; зачищаємо si

    jmp Sort    ; переходимо до Sort
    AfterSort:  ; мітка для повернення

    mov ah,4ch ; завантаження числа 4ch до регістру ah
	           ; (DOS 4ch - функція виходу з програми)
    int 21h ; виклик функції DOS 4ch

end Start