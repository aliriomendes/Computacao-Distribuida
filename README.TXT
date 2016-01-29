Em ambiente Linux Debian/Ubuntu:


--- INSTALAR COMPONENTES ---

1 - Instalar packages 
$ sudo apt-get -y install curl git

2 - Executar scripts de instalacao de software
$ ./install-openjdk8.sh 
$ ./install-ant.sh
$ ./install-maven.sh

3 - Obter o ficheiro restprj.tar.gz a partir do moodle e 
descompactar para a pasta home do utilizador
$ tar -zxvf restprj.tar.gz -C $HOME


--- DESENVOLVIMENTOS E TESTE ---

4 - Mudar para a pasta node-0 e executar o script sync-nodes
$ cd $HOME/node-0

4.1 Executar o "reset" da configuração utilizando o comando:
$ ./sync-nodes 0

5 - Compilar codigo (e executar Jetty)
$ ./compile.sh war

5.1 - Abrir novo terminal linux

5.2 - Mudar para a pasta node-0
$ cd $HOME/node-0

6 - Testar cliente rest XML Java
$ ./runwar.sh XMLClient

7 - Testar web service rest XML
$ curl http://node-0:8080/rest/xmlServices/student/James

8 - Testar cliente rest XML Java
$ ./runwar.sh JsonClient
 
9 - Testar web service rest JSON
$ curl http://node-0:8080/rest/employee/getEmployee

10 - Testar cliente browser (Firefox..etc) utilizando o URL:
http://node-0:8080/createanimal.html

11 - Criar pasta upload em /tmp:
$ mkdir /tmp/upload

13 - Testar upload no cliente browser (Firefox..etc) utilizando o URL:
http://node-0:8080/fileUpload.html
(seleccionar o ficheiro resources/Sample.pdf do projeto)

14 - Verificar upload
$ ls -all /tmp/upload
drwxrwxr-x  2 aluno aluno   60  Nov 27 19:05 .
drwxrwxrwt 16 aluno aluno   540 Nov 27 20:28 ..
-rw-rw-r--  1 aluno aluno  7696 Nov 27 20:28 Sample.pdf

15 - Copiar ficheiro Sample.pdf para a pasta /tmp
$ cp /tmp/upload/Sample.pdf /tmp

16 - Eliminar documento Sample.pdf da pasta upload
$ rm /tmp/upload/Sample.pdf

17 - Testar cliente rest upload
$ ./runwar.sh UploadFileTest

18 - Verificar upload
$ ls -all /tmp/upload
drwxrwxr-x  2 aluno aluno   60  Nov 27 19:05 .
drwxrwxrwt 16 aluno aluno   540 Nov 27 20:28 ..
-rw-rw-r--  1 aluno aluno  7696 Nov 27 20:28 Sample.pdf

18 - Testar download no cliente browser (Firefox..etc) utilizando o URL:
http://node-0:8080/rest/download/pdf
 

--- VERSAO FINAL (PRODUCAO) ---

11 - Terminar o processo iniciada no ponto 5
$ Ctrl+c

12 - Criar/Sincronizar node-1 e node-2 com a pasta $HOME
$ ./sync-nodes 2 

13 - Iniciar o servidor Jetty em cada node utilizando diferentes terminais:

Terminal do node-0
$ cd $HOME/node-0/
$ ./jetty-server start

Terminal do node-1
$ cd $HOME/node-1/
$ ./jetty-server start

Terminal do node-2
$ cd $HOME/node-2/
$ ./jetty-server start

14 - Para visualizar consola do servidor Jetty (opcional)
$ ./jetty-server console
( Ctrl+c para terminar a visualizacao)

15 - Para verificar se o servidor Jetty se encontra ativo (opcional)
$ ./jetty-server status

16 - Para reiniciar o servidor Jetty (opcional)
$ ./jetty-server restart

17 - Para terminar servidor Jetty (opcional)
$ ./jetty-server stop


NOTA:
O código encontra-se nas pastas
'src/main/java/com/rest/test'
'src/main/java/com/rest/crud'
'src/main/java/com/rest/howtodoinjava'
É utilizado o porto 8080


