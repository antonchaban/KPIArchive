import tkinter as tk

BUTTON_TEXT_FONT = ("Comfotraa", 12)
TEXTAREA_TEXT_FONT = ('Comfortaa', 14)
ACTIVEBG_COLOR = '#C0C0C0'

DARK_BUTTON_COLOR = '#686868'
DARK_TEXT_COLOR_1 = '#F0EEB8'
DARK_TEXTAREA_COLOR = '#303030'
DARK_TOOLBAR_COLOR = '#444444'

LIGHT_BUTTON_COLOR = '#ECECEC'
LIGHT_TEXT_COLOR_1 = '#272727'
LIGHT_TEXTAREA_COLOR = '#F2F2EF'
LIGHT_TOOLBAR_COLOR = '#F5F5F5'

def priority(operator):
    if(operator == "*" or operator == "/"):
        return 3
    elif(operator == "+" or operator == "-"):
        return 2
    elif(operator == "("):
        return 1

def normalToAntiPolish(kur):
    kur = list(kur)
    stack = []
    answer = ""
    for ch in kur:
        if (ch == "+" or ch =="-" or ch == "*" or ch =="/" or ch =="("):
            if(len(stack) == 0 or ch == "(" or priority(stack[len(stack)-1]) < priority(ch)):
                stack.append(ch)
            elif(priority(stack[len(stack)-1]) >= priority(ch)):
                while(len(stack) != 0):
                    if(priority(stack[len(stack)-1]) < priority(ch)):
                        break
                    answer += (stack[len(stack)-1])
                    stack.pop()
                stack.append(ch)    
        elif(ch == ")"):
            while(stack[len(stack)-1] != "(" ):
                    answer += (stack[len(stack)-1])
                    stack.pop()
            stack.pop()
        else:
            answer += ch
    for ch in stack:
        answer +=ch        
    return answer 

def polishToTree(kur):
    indent = ""
    tree = ""
    isFirst = True
    while(len(kur) > 0):
        if( kur[0] == "+" or kur[0] == "-" or kur[0] == "/" or kur[0] == "*"):
            if(isFirst):
                tree += " "+ str(kur[0])+"\n"
                tree += indent +" / \\"+ "\n"
                isFirst = False
            else:
                tree +=" "+ str(kur[0])+"\n"
                tree += indent +"    / \\"+ "\n"
                indent += " "        
            kur.pop(0)
        else:
            if(len(kur) == 1):
                tree += " " + str(kur[0])
            else:
                 tree += " "+ indent + str(kur[0]) + " "   
            kur.pop(0)
    return tree      

def polishMatan(kur):
    k = 0
    isNext = False
    answer = ""
    while(len(kur) > 1):
        if(not isNext):
            k = 0
        n1 = kur[len(kur)-1-k]
        n2 = kur[len(kur)-2-k]
        z = kur[len(kur)-3-k]
        if(k == 0):
            s= ""
            answer += s.join(kur) +'\n'
        if(z == "+"):
            a = int(n1)+int(n2)
        elif(z == "-"):
            a = int(n1)-int(n2)
        elif(z == "*"):
            a = int(n1)*int(n2) 
        elif(z == "/"):
            a = int(n1)/int(n2)
        elif(z=="0" or z=="1" or z=="2" or z=="3" or z=="4" or z=="5" or z=="6" or z=="7" or z=="8" or z=="9"):
            k = k + 1
            isNext = True
            continue 
        else:
            return
        isNext = False        
        kur.pop(len(kur)-1-k)
        kur.pop(len(kur)-1-k)
        kur.pop(len(kur)-1-k)
        kur.append(str(int(a)))
    answer += kur[0]    
    return answer

def antiPolishMatan(kur):
    kur = list(kur)
    k = 0
    isNext = False
    answer = ""
    while(len(kur) > 1):
        if(not isNext):
            k = 0
        n1 = kur[0+k]
        n2 = kur[1+k]
        z = kur[2+k]
        if(k == 0):
            s= ""
            answer += s.join(kur) +'\n'
        if(z == "+"):
            a = int(n1)+int(n2)
        elif(z == "-"):
            a = int(n1)-int(n2)
        elif(z == "*"):
            a = int(n1)*int(n2) 
        elif(z == "/"):
            a = int(n1)/int(n2)
        elif(z=="0" or z=="1" or z=="2" or z=="3" or z=="4" or z=="5" or z=="6" or z=="7" or z=="8" or z=="9"):
            k = k + 1
            isNext = True
            continue 
        else:
            return
        isNext = False         
        kur.pop(k)
        kur.pop(k)
        kur.pop(k)
        kur.insert(0,str(int(a)))
    answer += kur[0]   
    return answer

def doTask():
    lb1.config(text="")
    lb2.config(text = "" )
    lb3.config(text = "")
    kur = kurEntry.get()
    antiPolish = normalToAntiPolish(kur)
    resultAntiPol = antiPolishMatan(antiPolish)
    resultPol = list(antiPolish)
    resultPol.reverse()
    tree = resultPol.copy()
    resultPol = polishMatan(resultPol)
    lb1.config(text="Польская: \n"+resultPol)
    lb2.config(text = "Обратная польская: \n" + resultAntiPol)
    tree = polishToTree(tree)
    lb3.config(text = "Дерево: \n"+ tree)

    

root = tk.Tk()

root.geometry('500x350')
root.title('Polish')
root.wm_attributes('-alpha' , 0.95)
root.config(bg = DARK_TEXTAREA_COLOR)
root.resizable(False,False)

kurEntry = tk.Entry(
    root,
    width = 30,
     font = ('Comfortaa', 20),
     bg = DARK_BUTTON_COLOR,
     fg = DARK_TEXT_COLOR_1
)
btn = tk.Button(
    root,
    width = 20,
    text = 'Выполнить',
    bg =  DARK_BUTTON_COLOR,
    fg = DARK_TEXT_COLOR_1,
    font = BUTTON_TEXT_FONT,
    command = doTask,
    relief= tk.FLAT,
    activebackground= ACTIVEBG_COLOR
)

leftFrame = tk.Frame(root,bg = DARK_TOOLBAR_COLOR)
lb1 = tk.Label(
    leftFrame,
    width = 16,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)

rightFrame = tk.Frame(root,bg = DARK_TEXTAREA_COLOR)
lb2 = tk.Label(
    rightFrame,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
centralFrame = tk.Frame(root,bg = DARK_TEXTAREA_COLOR)
lb3 = tk.Label(
    centralFrame,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)

kurEntry.pack(side = tk.TOP)
btn.pack(side = tk.TOP)
leftFrame.pack(side=tk.LEFT)
centralFrame.pack(side=tk.LEFT)
rightFrame.pack(side=tk.LEFT)
lb1.pack(side = tk.TOP)
lb2.pack(side = tk.TOP)
lb3.pack(side = tk.TOP)


root.mainloop()