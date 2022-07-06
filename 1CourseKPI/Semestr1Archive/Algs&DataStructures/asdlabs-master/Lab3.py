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

def showTable(table):   
    tableLabel.config(text = "")      
    for r in table:
        for c in r:
            txt = tableLabel.cget("text") + str(c) +'|'
            tableLabel.config(text = txt)
        txt = tableLabel.cget("text") +'\n'
        tableLabel.config(text = txt)    


def task2(table):
    isConst0 = True
    isConst1 = True
    isSelfDouble = True
    for r in table:
        if (r[0] == r[1] == r[2] == 0 and r[3] == 1):
            isConst0 = False
            break
    for r in table:
        if (r[0] == r[1] == r[2] == 1 and r[3] == 0):
            isConst1 = False
            break 
    for row in table:
        isSelfDouble = False
        inv= []
        for c in row:
            if(c==0):
                inv.append(1)
            elif(c == 1):
                inv.append(0)  
        for r in table:
            if(inv == r):
                isSelfDouble = True
                break
        if(not isSelfDouble):
            isSelfDouble = False
            break
    lb1.config(text = "Линейная: " + str(isLineal(table)))    
    lb2.config(text = "Константа 0: "+str(isConst0))
    lb3.config(text = "Константа 1: "+ str(isConst1))
    lb4.config(text = "Самодвоистая: "+ str(isSelfDouble))

def mod(x,y):
    if(x == y):
        return 0
    else:
        return 1 

def isLineal(table):
    a0 = table[1][3]
    a3 = mod(table[2][3],a0)
    a2 = mod(table[3][3],a0)
    a23 = mod(mod(mod(a0,a2),a3),table[4][3])
    a1 = mod(table[5][3],a0)
    a13 = mod(mod(mod(a0,a1),a3),table[6][3])
    a12 = mod(mod(mod(a0,a1),a2),table[7][3])
    a123 = mod(mod(mod(mod(mod(mod(mod(a0,a1),a2),a3),a12),a13),a23),table[8][3])
    if(a13==0 and a12 ==0 and a23 == 0 and a123 == 0):
        return True
    else:
        return False    


def task3(trTable):
    DDNF = ""
    DKNF = ""
    for row in trTable:
        if row[3] == 1:
            if(DDNF != ""):
                DDNF += "+"
            for i in range(3):
                if (row[i] == 1):
                    DDNF += trTable[0][i]
                else:
                    DDNF +=  trTable[0][i] +"'"

        elif row[3] == 0:
            DKNF +="("
            for i in range(3):
                if(i != 0):
                    DKNF += "+" 
                if (row[i] == 1):
                    DKNF += trTable[0][i]+"'"
                else:
                    DKNF +=  trTable[0][i]
            DKNF +=")"
    lb5.config(text = "ДДНФ: \n"+ DDNF)
    lb6.config(text = "ДКНФ: \n" + DKNF)

def doTask():
    F = []
    if (vectorEntry.get() == "" or len(vectorEntry.get()) != 8):
        tableLabel.config(text = "ERROR")
        lb1.config(text = "")
        lb2.config(text = "")
        lb3.config(text = "")
        lb4.config(text = "")
        lb5.config(text = "")
        lb6.config(text = "")
        return
    vec = list(vectorEntry.get())
    #print(vec) 
    for i in vec:
        if(int(i) != 0 and int(i) != 1):
            tableLabel.config(text = "ERROR")
            lb1.config(text = "")
            lb2.config(text = "")
            lb3.config(text = "")
            lb4.config(text = "")
            lb5.config(text = "")
            lb6.config(text = "")
            return
        F.append(int(i))

    table = [['x', 'y', 'z', 'F'],
            [0, 0, 0, F[0]],
            [0, 0, 1, F[1]],
            [0, 1, 0, F[2]], 
            [0, 1, 1, F[3]], 
            [1, 0, 0, F[4]],
            [1, 0, 1, F[5]], 
            [1, 1, 0, F[6]], 
            [1, 1, 1, F[7]]]
    showTable(table)
    task2(table)
    task3(table)     


root = tk.Tk()

root.geometry('600x400')
root.title('Lab3')
root.wm_attributes('-alpha' , 0.95)
root.config(bg = DARK_TEXTAREA_COLOR)
root.resizable(False,False)

vectorEntry = tk.Entry(
    root,
    width = 8,
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

tableLabel = tk.Label(
    root,
    width = 16,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
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
lb2 = tk.Label(
    leftFrame,
    width = 16,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
lb3 = tk.Label(
    leftFrame,
    width = 16,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
lb4 = tk.Label(
    leftFrame,
    width = 16,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
rightFrame = tk.Frame(root,bg = DARK_TEXTAREA_COLOR)
lb5 = tk.Label(
    rightFrame,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
lb6 = tk.Label(
    rightFrame,
    text = "",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)

vectorEntry.pack(side = tk.TOP)
btn.pack(side = tk.TOP)
tableLabel.pack(side = tk.TOP)
leftFrame.pack(side = tk.LEFT)
rightFrame.pack(side = tk.RIGHT)
lb1.pack(side = tk.TOP)
lb2.pack(side = tk.TOP)
lb3.pack(side = tk.TOP)
lb4.pack(side = tk.TOP)
lb5.pack(side = tk.TOP)
lb6.pack(side = tk.TOP)


root.mainloop()
