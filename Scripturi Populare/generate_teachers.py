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
            query_teacher_account = "INSERT INTO public.conturi( id, password, username) VALUES (\'%s\', \'%s\', \'%s\');" % (
                            cont, generate_password(), usr)
            query_teacher = "INSERT INTO public.profesori( id, id_cont, email, nume, prenume) VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\');" % (
                            str(uuid.uuid4()), cont, usr + "@profs.info.uaic.ro", l_name, f_name)
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
            query_teacher_account = "INSERT INTO public.conturi( id, password, username) VALUES (\'%s\', \'%s\', \'%s\');" % (
                            cont, generate_password(), usr)
            query_teacher = "INSERT INTO public.studenti(id, id_cont, nume, prenume, an, grupa) VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');" % (
                            str(uuid.uuid4()), cont, l_name, f_name, str(randint(1, 3)), "")
            teachers.write(query_teacher_account + '\n')
            teachers.write(query_teacher + '\n\n')
            time.sleep(1)


def generate_groups():
    nume = ['A1', 'A2', 'A3', 'A4', 'A5', 'A6', 'B1', 'B2', 'B3', 'B4', 'B5', 'B6']
    creator_id = ['3f742159-144b-4b4e-8632-66f7a3f53edb', 'b2e07fd0-163e-4ee7-b9f1-074761e6908f', '8073c200-5e6f-46e1-9640-f523f21d590b', '1f212a24-8c47-4a2e-96f4-dbe0aac8bbcd', '0c9be54c-3c38-48f8-b213-a4138aa149b4', '770df657-e4d4-49f1-b9b9-fee3eea58978', '8277ccfc-bf9a-488f-b3d0-f6966b967096', 'ff4dd902-eb9e-4c12-aa48-5eea7866776b', 'f12f5cf9-0345-4cd6-b811-3e0565a3ae75', '40c12c42-5929-4c50-be0c-9594174338c3', '9d22852f-afe8-4cfe-9899-3282cf445a5b', 'b76a16c5-c6a6-4d5c-ab0f-ac4336c1724d',]
    with open("initial_groups.txt", "w+") as data:
        for i in range(0, 12):
            querry = "INSERT INTO public.grupuri( id, id_creator, nume) VALUES (\'%s\', \'%s\', \'%s\');" % (str(uuid.uuid4()), str(creator_id[i]), str(nume[i]))
            data.write(querry + '\n')

# generate_teachers()
# generate_studs()
generate_groups()
