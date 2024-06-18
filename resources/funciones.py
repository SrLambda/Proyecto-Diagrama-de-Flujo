import re
import tkinter as tk
from tkinter import simpledialog,messagebox


def entrada():

    root = tk.Tk()
    root.withdraw()

    entrada = simpledialog.askstring("Entrada", "Ingrese un valor:")

    patron_entero   = r"^-?\d+$"
    patron_double   = r"^-?\d+(\.\d+)?$"
    patron_caracter = r"^.$"

    if re.search(patron_entero, entrada):

        return int(entrada)

    elif re.search(patron_double, entrada):

        return float(entrada)

    elif re.search(patron_caracter, entrada):

        return chr(entrada)

    else:

        return entrada



#---------------------------------------------




def salida(val):

    root = tk.Tk()
    root.withdraw()

    messagebox.showinfo("Salida", f"{val}")





#---------------------------------------------



def documento(val):

    root = tk.Tk()
    root.withdraw()

    messagebox.showerror("Documento", f"{val}")
