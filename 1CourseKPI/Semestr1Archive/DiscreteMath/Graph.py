import tkinter as tk
import numpy as np
import random



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


def randMass(graph):
    size = (len(graph), len(graph[0]))
    array = np.zeros(size, dtype=int)
    for r in range(0, len(graph)):
        for c in range(0, len(graph[0])):
            if graph[r][c] == 1:
                array[r][c] = random.randint(1, 9)
            elif graph[r][c] == 0:
                array[r][c] = 0
    return array


def dijkstra(graph, start):
    INF = 10 ** 10
    weight = [INF] * len(graph)
    weight[start] = 0
    used = [False] * len(graph)
    for k in range(len(graph)):
        minVertex = -1
        minWeight = INF
        for j in range(len(graph)):
            if not used[j] and weight[j] <= minWeight:
                minWeight = weight[j]
                minVertex = j
        for j in range(len(graph)):
            if weight[minVertex] + graph[minVertex][j] < weight[j] and graph[minVertex][j] > 0:
                weight[j] = weight[minVertex] + graph[minVertex][j]
        used[minVertex] = True

    return weight
    # print(used)


def isOriental(graph):
    isOriented = False
    for row in range(0, len(graph)):
        for column in range(0, len(graph[0])):
            tmp1 = graph[row][column]
            tmp2 = graph[column][row]
            if tmp1 != tmp2:
                return True
        if not isOriented:
            return False


def inDotsFunc(graph):
    inDots = []
    for i in graph:
        inDots.append(0)
    for i in range(len(graph[0])):
        for j in range(len(graph[i])):
            if (graph[i][j] == 1 and i != j):
                inDots[j] = inDots[j] + 1
    return inDots


def outDotsFunc(graph):
    outDots = []
    for i in graph:
        outDots.append(0)
    for i in range(len(graph)):
        for j in range(len(graph[i])):
            if (graph[i][j] == 1 and i != j):
                outDots[i] = outDots[i] + 1
    return outDots


def buildEulerCycle(graph):
    dot = 0
    path = ""
    isEnd = False
    isFind = False
    while (not isEnd):
        for i in range(len(graph[dot])):
            if (graph[dot][i] == 1):
                graph[dot][i] = 0
                path += str(dot) + " > " + str(i) + "\n"
                dot = i
                isFind = True
                break
        if (not isFind):
            for i in graph:
                for j in i:
                    if (j != 0):
                        return False
            if (dot != 0):
                return False
            return path
        isFind = False


def buildEulerPath(graph):
    path = ""
    inDots = inDotsFunc(graph)
    outDots = outDotsFunc(graph)
    onlyIn = False
    onlyOut = False
    startDot = -1
    endDot = -1
    for i in range(len(inDots)):
        if (inDots[i] - outDots[i] == 1 and not onlyIn):
            onlyIn = True
            endDot = i
            continue
        if (outDots[i] - inDots[i] == 1 and not onlyOut):
            onlyOut = True
            startDot = i
            continue
        if (inDots[i] != outDots[i] or inDots[i] == 0 or outDots == 0):
            return False
    if (startDot == -1 and endDot != 0):
        startDot = 0
    elif (startDot == -1):
        startDot = 1

    isEnd = False
    isFind = False
    lastDot = -1
    lastStart = -1
    while (not isEnd):
        for i in range(len(graph[startDot])):
            if (graph[startDot][i] == 1):
                if (i == endDot):
                    lastStart = startDot
                    lastDot = i
                    continue
                graph[startDot][i] = 0
                path += str(startDot) + " > " + str(i) + "\n"
                startDot = i
                isFind = True
                break
        if (not isFind):
            graph[startDot][lastDot] = 0
            startDot = lastDot
            for i in graph:
                for j in i:
                    if (j != 0):
                        return False
            if (startDot != endDot and endDot != -1):
                return False
            if (endDot != -1):
                path += str(lastStart) + " > " + str(endDot) + "\n "
            return path
        isFind = False


def doTask():
    # graph = [[1, 0, 0, 0, 1],
    #          [0, 1, 0, 0, 0],
    #          [1, 0, 1, 0, 0],
    #          [1, 1, 0, 0, 1],
    #          [0, 0, 1, 0, 1]]
    graph = [[0, 1, 1],
             [1, 0, 0],
             [1, 0, 0]]
    massGraph = randMass(graph)
    isOri = isOriental(graph)
    euCycle = buildEulerCycle(graph)
    euPath = buildEulerPath(graph)
    if (euCycle != False):
        lb1.config(text="Цикл Ейлера: \n" + euCycle)
    elif (euPath != False):
        lb1.config(text="Путь Ейлера: \n" + euPath)
    else:
        lb1.config(text="Путь и Цикл Ейлера\nне существуют")
    dj = dijkstra(massGraph, 0)
    ans = ""
    for i in dj:
        if (i == 10000000000):
            ans += "Inf | "
        else:
            ans += str(i) + " | "
    lb3.config(text="Алгоритм Дейкстры:\n" + ans)
    lb4.config(text=massGraph)
    lb2.config(text="Граф ориентированый:\n" + str(isOri))


root = tk.Tk()

root.geometry('590x300')
root.title('Graph')
root.wm_attributes('-alpha', 0.95)
root.config(bg=DARK_TEXTAREA_COLOR)
root.resizable(False, False)

btn = tk.Button(
    root,
    width=20,
    text='Выполнить',
    bg=DARK_BUTTON_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=BUTTON_TEXT_FONT,
    command=doTask,
    relief=tk.FLAT,
    activebackground=ACTIVEBG_COLOR
)

leftFrame = tk.Frame(root, bg=DARK_TOOLBAR_COLOR)
lb1 = tk.Label(
    leftFrame,
    width=16,
    text="",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)

rightFrame = tk.Frame(root, bg=DARK_TEXTAREA_COLOR)
lb2 = tk.Label(
    rightFrame,
    text="",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
centralFrame = tk.Frame(root, bg=DARK_TEXTAREA_COLOR)
lb3 = tk.Label(
    centralFrame,
    text="",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
lb4 = tk.Label(
    centralFrame,
    text="",
    bg=DARK_TEXTAREA_COLOR,
    fg=DARK_TEXT_COLOR_1,
    font=TEXTAREA_TEXT_FONT,
)
btn.pack(side=tk.TOP)
leftFrame.pack(side=tk.LEFT)
centralFrame.pack(side=tk.LEFT)
rightFrame.pack(side=tk.LEFT)
lb1.pack(side=tk.TOP)
lb2.pack(side=tk.TOP)
lb3.pack(side=tk.TOP)
lb4.pack(side=tk.TOP)

root.mainloop()
