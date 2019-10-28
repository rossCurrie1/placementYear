# My Python Project


def txt_to_list():  # Function 1: Opening and Reading the File
    with open('Data_Set_CW.txt', 'r') as f:    # Benefit w/ content managers is they close the file for you
        size_to_read = 1000    # this tells the number of characters to read
        f.seek(50)    # this tells the reader where to start i.e. it will ignore the first line
        f_contents = f.readlines(size_to_read)   # f variable stands for 'File' + readlines() will O/P as a list
        print(f_contents)    # O/Ps the contents of the file as a list


def file_length():   # Function 2: Number of records in the file
    with open('Data_Set_CW.txt', 'r') as f:
        for i, l in enumerate(f):   # this loops round the file
            pass    # if data is found then the for loop continues
        print(i)    # prints the value of i
        return i + 1    # adds one to the i so that the same line isn't being counted


txt_to_list()
print('------------------------------------------------')
file_length()

