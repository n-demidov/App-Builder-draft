# Как запустить виртуальный сервер #

1) скачать этот репозиторий;

2) установить Vagrant и VirtualBox;

3) Выполнить команды:

```
vagrant plugin install vagrant-vbguest
vagrant plugin install vagrant-cachier
```

Ссылки на репозитории плагинов:
https://github.com/dotless-de/vagrant-vbguest
https://github.com/fgrehm/vagrant-cachier

4) открыть консоль; перейти в директорию с файлом "Vagrantfile"; ввести команду: "vagrant up". Как результат - создастся виртуальная машина с развернутой ОС, и всеми выполненными скриптами;

Если при установке виртуалки возникает ошибка - включить виртуализацию в BIOS. Опция может иметь различные названия, например, "SVM".
Статейка в тему опций BIOS'а:
http://www.nastrojkabios.ru/protsessor/virtualization-technologiiu-apparatnoy-virtualizatsii-s-foto.html

5) Проверить успешность установки: зайти через программу PuTTY (localhost, port: 2222). Логин и пароль - vagrant. Ввести команду: "sudo rabbitmqctl status" - должны вывестись инфа о RabbitMQ, в том числе версия.


### Команды ###

* sudo rabbitmqctl status

Выводит инфу о RabbitMQ, включая версию.

* sudo rabbitmqctl list_queues name messages_ready messages_unacknowledged

Показывает список очередей. Поля: имя очереди, кол-во сообщений, кол-во неподтверждённых сообщений.

* lsb_release -cs

Returns the name of your Ubuntu distribution, such as xenial.