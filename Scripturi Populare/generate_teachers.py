import time
import uuid
import json
import string
import secrets
import urllib.parse
import urllib.request
from pprint import pprint
from random import randint

username_list = []


def generate_name():
    d = "ĂÂÎȘȚâăîșț"
    l = "AAISTaaist"
    req = urllib.request.Request("https://api.namefake.com/romanian-romania/random/")
    response = urllib.request.urlopen(req)
    text1 = json.loads(response.read().decode())
    text = text1['name']
    table = text.maketrans(d, l)
    the_page = text.translate(table)
    text1['name'] = the_page
    pprint(text1)
    return text1


def generate_password():
    char_classes = (string.ascii_lowercase, string.ascii_uppercase, string.digits)
    size = lambda: secrets.choice(range(8, 16))
    char = lambda: secrets.choice(secrets.choice(char_classes))
    password = lambda: ''.join([char() for _ in range(size())])
    return password()


def generate_teachers():
    with open("random_teachers.txt", "w+") as teachers:
        global username_list
        cont = ""
        for i in range(0, 10):
            teacher = generate_name()
            name = teacher['name'].split(' ')
            f_name = ""
            l_name = ""
            usr = ""
            if len(name) == 3:
                l_name = name[2]
                f_name = name[1]
            elif len(name) == 2:
                l_name = name[1]
                f_name = name[0]
            usr = l_name.lower() + '.' + f_name.lower()
            if usr in username_list:
                usr = usr + str(username_list.count(usr) + 1)
            username_list.append(usr)
            cont = str(uuid.uuid4())
            query_teacher_account = "INSERT INTO public.conturi( id, password, username) VALUES (\'%s\', \'%s\', \'%s\');" % (cont, generate_password(), usr)
            query_teacher = "INSERT INTO public.profesori( id, id_cont, email, nume, prenume) VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\');" % (str(uuid.uuid4()), cont, usr + "@profs.info.uaic.ro", l_name, f_name)
            teachers.write(query_teacher_account + '\n')
            teachers.write(query_teacher + '\n\n')
            time.sleep(1)


def generate_studs():
    with open("random_students.txt", "w+") as teachers:
        global username_list
        cont = ""
        for i in range(0, 100):
            teacher = generate_name()
            name = teacher['name'].split(' ')
            f_name = ""
            l_name = ""
            usr = ""
            if len(name) == 3:
                l_name = name[2]
                f_name = name[1]
            elif len(name) == 2:
                l_name = name[1]
                f_name = name[0]
            usr = l_name.lower() + '.' + f_name.lower()
            if usr in username_list:
                usr = usr + str(username_list.count(usr) + 1)
            username_list.append(usr)
            cont = str(uuid.uuid4())
            query_teacher_account = "INSERT INTO public.conturi( id, password, username) VALUES (\'%s\', \'%s\', \'%s\');" % (cont, generate_password(), usr)
            query_teacher = "INSERT INTO public.studenti(id, id_cont, nume, prenume, an, grupa) VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');" % (str(uuid.uuid4()), cont, l_name, f_name, str(randint(1, 3)), "")
            teachers.write(query_teacher_account + '\n')
            teachers.write(query_teacher + '\n\n')
            time.sleep(1)


generate_teachers()
generate_studs()