import random
import time
import uuid
import json
import string
import secrets
import hashlib
import urllib.parse
import urllib.request
from pprint import pprint
from random import randint

username_list = []
students_id = []
teacher_id = []
group_id = []


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
    # pprint(text1)
    # time.sleep(2)
    return text1


def generate_password():
    char_classes = (string.ascii_lowercase, string.ascii_uppercase, string.digits)
    size = lambda: secrets.choice(range(8, 16))
    char = lambda: secrets.choice(secrets.choice(char_classes))
    password = lambda: ''.join([char() for _ in range(size())])
    return password()


def generate_teachers():
    with open("random_teachers.txt", "w+") as teachers, open("conturi_profi.txt", "w+") as conturi:
        global username_list
        cont = ""
        for i in range(0, 20):
            try:
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
                cont = str(uuid.uuid4())
                username_list.append(usr)
                pwd = generate_password()
                query_teacher_account = "INSERT INTO public.conturi( id, password, username) VALUES (\'%s\', \'%s\', \'%s\');" % (
                cont, hashlib.sha256(pwd.encode()).hexdigest(), usr)

                pers_id = str(uuid.uuid4())
                query_teacher_person = "INSERT INTO public.person( id, nume, prenume, id_cont) VALUES (\'%s\', \'%s\', \'%s\', \'%s\');" % (
                pers_id, l_name, f_name, cont)

                th_id = str(uuid.uuid4())
                # teacher_id.append(th_id)
                query_teacher = "INSERT INTO public.profesori( id, email, person_id) VALUES (\'%s\', \'%s\', \'%s\');" % (
                th_id, usr + "@profs.info.uaic.ro", pers_id)

                teachers.write(query_teacher_account + '\n')
                teachers.write(query_teacher_person + '\n')
                teachers.write(query_teacher + '\n\n')
                teacher_id.append(th_id)
                conturi.write(usr + " " + pwd + '\n')
                time.sleep(1)
            except Exception:
                time.sleep(10)


def generate_studs():
    with open("random_students.txt", "w+") as teachers, open("conturi.txt", "w+") as conturi:
        global username_list
        cont = ""
        for j in range(0, 10):
            try:
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

                    pwd = generate_password()
                    query_teacher_account = "INSERT INTO public.conturi( id, password, username) VALUES (\'%s\', \'%s\', \'%s\');" % (cont, hashlib.sha256(pwd.encode()).hexdigest(), usr)

                    pers_id = str(uuid.uuid4())
                    query_teacher_person = "INSERT INTO public.person( id, nume, prenume, id_cont) VALUES (\'%s\', \'%s\', \'%s\', \'%s\');" % (pers_id, l_name, f_name, cont)

                    th_id = str(uuid.uuid4())
                    students_id.append(pers_id)
                    query_teacher = "INSERT INTO public.studenti(id, an, person_id) VALUES (\'%s\', \'%s\', \'%s\');" % (th_id, str(randint(1, 3)), pers_id)

                    teachers.write(query_teacher_account + '\n')
                    teachers.write(query_teacher_person + '\n')
                    teachers.write(query_teacher + '\n\n')

                    conturi.write(usr + " " + pwd + '\n')

                    time.sleep(1)
            except Exception:
                pass
            time.sleep(10)


def generate_groups():
    nume = ['A1', 'A2', 'A3', 'A4', 'A5', 'A6', 'B1', 'B2', 'B3', 'B4', 'B5', 'B6']

    with open("initial_groups.txt", "w+") as data:
        for i in range(0, 12):
            g_id = str(uuid.uuid4())
            group_id.append(g_id)
            querry = "INSERT INTO public.grupuri( id, nume, creator_id) VALUES (\'%s\', \'%s\', \'%s\');" % (g_id, '1' + str(nume[i]), str(random.choice(teacher_id)))
            data.write(querry + '\n')

            g_id = str(uuid.uuid4())
            group_id.append(g_id)
            querry = "INSERT INTO public.grupuri( id, nume, creator_id) VALUES (\'%s\', \'%s\', \'%s\');" % (g_id, '2' + str(nume[i]), str(random.choice(teacher_id)))
            data.write(querry + '\n')

            g_id = str(uuid.uuid4())
            group_id.append(g_id)
            querry = "INSERT INTO public.grupuri( id, nume, creator_id) VALUES (\'%s\', \'%s\', \'%s\');" % (g_id, '3' + str(nume[i]), str(random.choice(teacher_id)))
            data.write(querry + '\n')


def generate_group_member():
    with open("random_member.txt", 'w+') as member:
        for i in students_id:
            querry = "INSERT INTO public.group_memger( group_id, memgru_id) VALUES  (\'%s\', \'%s\');" % (random.choice(group_id), i)
            member.write(querry + '\n')


generate_teachers()
generate_studs()
generate_groups()
generate_group_member()