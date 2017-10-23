
echo Developing. . . 1 test version

echo off

cls

echo : build Django---------------------

python manage.py makemigrations
python manage.py migrate

cls

echo : run server

python manage.py runserver 807

pause